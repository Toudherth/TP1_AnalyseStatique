package com.evolution.analyse_statique;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.eclipse.jdt.core.dom.CompilationUnit;

import com.evolution.entity.ClassAtributeCount;
import com.evolution.entity.ClassMethodCount;
import com.evolution.visitors.GraphVisitor;
import com.evolution.visitors.StateVisitor;

import guru.nidi.graphviz.attribute.Color;
import guru.nidi.graphviz.attribute.Style;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.MutableGraph;
import guru.nidi.graphviz.parse.Parser;




import static java.util.Comparator.*;

public class Processor {
	public static ParserAST parse;
	public static final String path = "C:\\Users\\DELL\\OneDrive\\Desktop\\projet\\Tp1AnalyseStatique";
	public static final String projectSourcePath = path + "\\src";
	public static final String jrePath = "C:\\Program Files\\Common Files\\Oracle\\Java\\javapath";
	final static File folder = new File(projectSourcePath);
	public static ArrayList<File> javaFiles = parse.listJavaFilesForFolder(folder);
	public static List<List<Map.Entry<String, Integer>>> methodLinesMap = new ArrayList<>();
	public List<String> uniquePackages = new ArrayList();
	private ParserAST parser;
	private static StateVisitor stateVisitor;
	private GraphVisitor graphVisitor;
	public static int getNbClasses=0;
	public static int getNbMethods=0;
	public static int getNbPackages=0;
	public static int getNbLinesOfCodes=0;
	public static int getNbAttributs=0;
	public static int top10Method=0;
	public static int top10Attributs=0;
	public static int getNbArgMax;
	
	public Processor() {
		super();
		parser = new ParserAST();
		stateVisitor= new StateVisitor();
		graphVisitor = new GraphVisitor();
	}
	
	ArrayList<ClassMethodCount> classMethodCounts = new ArrayList<>();
	ArrayList<ClassAtributeCount> classAttributesCounts = new ArrayList<>();

	ArrayList<String> classMethodX = new ArrayList<>();



	public void display() throws  IOException {
		for (File file : javaFiles) {
			String content = FileUtils.readFileToString(file);
	        CompilationUnit ast = parser.getCompilationUnit(content.toCharArray());
	        // class visitor 1
	        stateVisitor = new StateVisitor();
			ast.accept(stateVisitor);

			// gat all data
		    getNbClasses+= stateVisitor.getClassSize();
		    getNbLinesOfCodes+=stateVisitor.getLineCount();
		    getNbMethods+= stateVisitor.getNbrMethod();
	        getNbAttributs+=stateVisitor.getNbrVariables();
			//Packages
			getPackageWithoutRepetition();

            // Q 10/11 for Percentage : 
            classMethodCounts.add(new ClassMethodCount(file.getName(), stateVisitor.getNbrMethod()));
            classAttributesCounts.add(new ClassAtributeCount(file.getName(),stateVisitor.getNbrVariables() ));
            // For question 12 : Top 10 of LOC of methods
            List<Map.Entry<String, Integer>> methodLinesMapFromCurrentFile = new ArrayList<>(stateVisitor.getMethodLinesMap().entrySet());

	        for (Map.Entry<String, Integer> entry : methodLinesMapFromCurrentFile) {
	            String methodName = entry.getKey();
	            int lineCount = entry.getValue();
	            String uniqueKey = file.getName() + "." + methodName;
	            List<Map.Entry<String, Integer>> methodEntry = new ArrayList<>();
	            methodEntry.add(new AbstractMap.SimpleEntry<>(uniqueKey, lineCount));
	            methodLinesMap.add(methodEntry);
	        }
	        // Q 13 for nombre of attributs in methods
			getNbArgMax+= stateVisitor.getNbArgsMax();

           
            
		}
		// Q1
		System.out.println("Q1 - Nombre de classes de l'application : " + getNbClasses);
		// Q2
		System.out.println("Q2 - Nombre de lignes de code de l'application : " + getNbLinesOfCodes);
		// Q3
		System.out.println("Q3 - Nombre total de méthodes de l'application : " + getNbMethods);
		// Q4
		System.out.println("Q4 - Nombre total de packages de l'application : " + getNbPackages);
		// Q5
		System.out.println("Q5 - Nombre moyen de méthodes par classe : " + (getNbMethods / getNbClasses));
		// Q6
		System.out.println("Q6 - Nombre moyen de lignes de code par méthode : "+ (getNbLinesOfCodes / getNbMethods));
		// Q7
		System.out.println("Q7 - Nombre moyen d'attributs par classe : " + (getNbAttributs / getNbClasses));
		// Q8
		System.out.println("Q8 - Les 10% des classes qui possèdent le plus grand nombre de méthodes : ");
		getPercentageMethodForClass();
		
		// Q9
		System.out.println("Q9 - Les 10% des classes qui possèdent le plus grand nombre d'attributs : ");
		getPercentageAttributForClass();
		
		// Q10
		//Question 10 :
		System.out.println("Q10 - Les classes qui font partie en même temps des deux catégories précédentes : ");
	    ClassWithToCategories(top10Method, top10Attributs);
	    
	    // 11
 		System.out.println("Q11 - Les classes qui possèdent plus de X méthodes : \n*Veuillez insérer la valeur de X****");
 		@SuppressWarnings("resource")
 		Scanner sc = new Scanner(System.in);
 		int x = sc.nextInt();
 		getClassWithXMethods(x);
 		
 		// Q12
 		System.out.println("Q12 - Les 10% des méthodes qui possèdent le plus grand nombre de lignes de code :");
 		displayMethodCodeLines();
 		// Q13 
 		System.out
		.println("Q13 - Le nombre maximal de paramètres par rapport à toutes les méthodes de l'application est de : "
				+ getNbArgMax);
 		System.out.println("");
				
	}
	
	public Object getPercentageMethodForClass() {

        classMethodCounts.sort(comparingInt(ClassMethodCount::getMethodCount).reversed());
        // Calculate the number of classes that make up 10% of the total.
        top10Method = (int) Math.ceil(0.10 * classMethodCounts.size());
        ClassMethodCount classMethodCount = null;
        for (int i = 0; i < top10Method; i++) {
            classMethodCount = classMethodCounts.get(i);
            System.out.println("	"+classMethodCount.getClassName() + " - Nombre de méthodes : " + classMethodCount.getMethodCount());
        }
        return classMethodCount;
    }
	
	public Object getPercentageAttributForClass() {
        classAttributesCounts.sort(comparingInt(ClassAtributeCount::getMethodCount).reversed());
        top10Attributs = (int) Math.ceil(0.10 * classAttributesCounts.size());
        ClassAtributeCount classAttributCount = null;
        for (int i = 0; i < top10Attributs; i++) {
            classAttributCount = classAttributesCounts.get(i);
            System.out.println("	"+classAttributCount.getClassName() + " - Nombre d'attributs : " + classAttributCount.getMethodCount());
        }
        return classAttributCount;
    }
	
	
	//*********
	// **************************************
	public void displayMethodCodeLines() {

	        List<Map.Entry<String, Integer>> top10PercentMethods = stateVisitor.getTop10PercentMethods(methodLinesMap);

	        // Affichez les résultats
	        for (Map.Entry<String, Integer> entry : top10PercentMethods) {
	            String methodName = entry.getKey();
	            int lineCount = entry.getValue();
	            System.out.println("	Méthode : " + methodName + " - Nombre de lignes : " + lineCount);
	        }
	}

	public Object ClassWithToCategories(int m, int a) {
		List<String> classesInBothCategories = new ArrayList<>();
		for (int i = 0; i < top10Method; i++) {
	        ClassMethodCount methodCount = classMethodCounts.get(i);
	        for (int j = 0; j < top10Attributs; j++) {
	            ClassAtributeCount attributCount = classAttributesCounts.get(j);
	            if (methodCount.getClassName().equals(attributCount.getClassName())) {
	                classesInBothCategories.add(methodCount.getClassName());
	               System.out.println("		La classe "+methodCount.getClassName() +" a "+ methodCount.getMethodCount()+" de methodes et "+ attributCount.getMethodCount() +" nombre d'attributs ");
	                break; 	                
	            }
	        }
		}
		return null;
	}
	
	
	// X methods 
	public void getClassWithXMethods(int x) {
		
		for(ClassMethodCount c: classMethodCounts) {
			if(c.getMethodCount() >=x) {
				classMethodX.add(c.getClassName());
				System.out.println("	La classe " + c.getClassName() + " a au moins " + c.getMethodCount() + " méthodes.");
				}
		}
		
	}

	// accept visitors 
	
	public static void process() throws FileNotFoundException, IOException {

		for (File file : javaFiles) {
			String content = FileUtils.readFileToString(file);
	        CompilationUnit ast = ParserAST.getCompilationUnit(content.toCharArray());
	        stateVisitor = new StateVisitor();
			ast.accept(stateVisitor);
		}
	}
	
	

	public void displaySet(Set<String> set) {
		if (set.isEmpty()) {
			System.out.println("Aucun élément à afficher");
		} else {
			for (String elt : set) {
				System.out.println(Main.indentationFormat + "" + elt);
			}
		}
	}
	
	
	// Graphe 
	
	public void processGraph() throws FileNotFoundException, IOException {
		
		
		for (File file : javaFiles) {
			String content = FileUtils.readFileToString(file);
	        CompilationUnit ast = ParserAST.getCompilationUnit(content.toCharArray());
	        graphVisitor.setCu(ast);
			ast.accept(graphVisitor);
			graphVisitor.calculateGraph();
		}


	}
	
	public String getGraph() {
		return graphVisitor.getGraph();
	}
	
	public void saveGraph() {
		try {
			FileWriter fw = new FileWriter("graph.dot", false);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter out = new PrintWriter(bw);
			out.println(graphVisitor.getGraphAsDot());
			out.close();
			bw.close();
			fw.close();
		} catch (IOException e) {
			System.out.println("Exception ecriture fichier");
			e.printStackTrace();
		}
	}
	
	public void saveGraphAsPNG() throws IOException {

		MutableGraph g = new Parser().read(graphVisitor.getGraphAsDot());
		Graphviz.fromGraph(g).width(700).render(Format.PNG).toFile(new File("graph.png"));

		g.graphAttrs().add(Color.WHITE.gradient(Color.rgb("888888")).background().angle(90)).nodeAttrs()
				.add(Color.WHITE.fill()).nodes()
				.forEach(node -> node.add(Color.named(node.name().toString()), Style.lineWidth(4), Style.FILLED));
		Graphviz.fromGraph(g).width(700).render(Format.PNG).toFile(new File("graph-2.png"));

	}

	// cette methode est utiliser pour avoir les packages qui existe dans le porjet sans répétitions
	public Object getPackageWithoutRepetition(){
		List<String> packagesInFile = stateVisitor.getPackages();
		for (String packageName : packagesInFile) {
			if (!uniquePackages.contains(packageName)) {
				uniquePackages.add(packageName);
				getNbPackages++;
			}
		}
		return getNbPackages;
	}
	/*-------------------------- Les interfaces graphiques ------------------------ */



	public String exercice1() throws  IOException{
		metodeExecution();
		return String.valueOf(stateVisitor.getClassSize()-1);
	}
	public String exercice2() throws  IOException{
		metodeExecution();
		return String.valueOf(stateVisitor.getLineCount()-1);
	}
	public String exercice3() throws  IOException{
		metodeExecution();
		return String.valueOf(stateVisitor.getNbrMethod());
	}
	public String exercice4() throws  IOException{
		metodeExecution();
		return String.valueOf(this.getPackageWithoutRepetition());
	}
	public String exercice5() throws  IOException{
		metodeExecution();
		return String.valueOf((stateVisitor.getNbrMethod()/stateVisitor.getClassSize()));
	}
	public String exercice6() throws  IOException{
		metodeExecution();
		return String.valueOf((stateVisitor.getLineCount() / stateVisitor.getNbrMethod()));
	}
	public String exercice7() throws  IOException{
		metodeExecution();
		return String.valueOf((stateVisitor.getNbrVariables() / stateVisitor.getClassSize()));
	}
	public String exercice8() throws  IOException{
		metodeExecution();
		return String.valueOf(getPercentageMethodForClass());
	}
	public String exercice9() throws  IOException{
		metodeExecution();
		return String.valueOf(getPercentageAttributForClass());
	}
	public String exercice10() throws  IOException{
		metodeExecution();
		return String.valueOf(ClassWithToCategories(top10Method, top10Attributs));
	}
	public String exercice11(int valInt) throws  IOException{
		metodeExecution();
		return String.valueOf(stateVisitor.getNbrMethod());
	}
	public String exercice12() throws  IOException{
		metodeExecution();
		return String.valueOf(stateVisitor.getNbrVariables());
	}
	public String exercice13() throws  IOException{
		metodeExecution();
		return String.valueOf(stateVisitor.getNbrVariables());
	}

	public void metodeExecution() throws IOException{
		for (File file : javaFiles) {
			String content = FileUtils.readFileToString(file);
			CompilationUnit ast = parser.getCompilationUnit(content.toCharArray());
			ast.accept(stateVisitor);

		}
	}

}
