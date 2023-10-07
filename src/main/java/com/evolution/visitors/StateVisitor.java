package com.evolution.visitors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.PackageDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;


public class StateVisitor extends ASTVisitor{
	
	// Declarations : 
	private Map<String, List<String>> mapClassesMethods = new HashMap();

	
	/* ========== Packages ========== */
	private int packageCount = 0;
	private int packagesget=0;
	private List<String> visitedPackages = new ArrayList();
	
	
	@Override
	public boolean visit(PackageDeclaration node) {
		String packageName = node.getName().getFullyQualifiedName();
	    if (!visitedPackages.contains(packageName)) {
	    	packageCount++;
	        visitedPackages.add(packageName);
        }
	    return super.visit(node);
	}

    public List<String> getPackages() {
    	return visitedPackages;
    }
  
    List<String> uniquePackages = new ArrayList();
    // show all packages without repetition
    public int allPackagesWithoutRepetition() {
        
        for (String packageName : getPackages()) {
            if (uniquePackages.contains(packageName)) {
                uniquePackages.add(packageName);
              System.out.println("uryuititur "+packageName+ "  "+ uniquePackages.get(0));  packagesget++;
            }
        }

        return packagesget;//return packagesget;
    }
    

	
	/* ========== Class ========== */
    private List<TypeDeclaration> classDeclarations = new ArrayList();
    int totalAttributes = 0;
    @Override
    public boolean visit(TypeDeclaration node) {
    	classDeclarations.add(node);
        return super.visit(node);
    }
   
    public List<TypeDeclaration> getClassDeclarations() {
        return classDeclarations;
    }
   // Q1
    public int getClassSize() {
	   return classDeclarations.size();
    }
	
   /* ========== Methods ========== */
    List<MethodDeclaration> methods = new ArrayList<MethodDeclaration>();
//    private Map<String, Integer> methodLineCounts = new HashMap<>();
    private Map<String, List<String>> methodCodeLines = new HashMap<>();

    private Map<String, Integer> methodLinesMap = new HashMap<>();
 
    @Override
    public boolean visit(MethodDeclaration node) {
        CompilationUnit compilationUnit = (CompilationUnit) node.getRoot();
        String methodName = node.getName().getIdentifier();
        int startLine = compilationUnit.getLineNumber(node.getStartPosition());
        int endLine = compilationUnit.getLineNumber(node.getStartPosition() + node.getLength() - 1);
        int methodLineCount = endLine - startLine + 1;
        methods.add(node);
        methodLinesMap.put(methodName, methodLineCount);
        return super.visit(node);
    }

    
	public List<MethodDeclaration> getMethods() {
		return methods;
	}
	
	//nbr methode 
	public int getNbrMethod() {
			return methods.size();
	}
	
	// chaque classe retourn les methodes de la classe 
	public Map<String, List<String>> getMapClassesMethods() {
			return mapClassesMethods;
	}
	public Map<String, List<String>> getMethodCodeLines() {
	    return methodCodeLines;
	}
	
	public Map<String, Integer> getMethodLinesMap() {
        return methodLinesMap;
    }
  
	public List<Map.Entry<String, Integer>> getTop10PercentMethods(List<List<Map.Entry<String, Integer>>> inputMethodLinesMaps) {
	    List<Map.Entry<String, Integer>> combinedEntryList = new ArrayList<>();
	
	    for (List<Map.Entry<String, Integer>> methodLinesMap : inputMethodLinesMaps) {
	        combinedEntryList.addAll(methodLinesMap);
	    }
		combinedEntryList.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));
		
		int totalEntries = combinedEntryList.size();
		int numEntriesToSelect = (int) Math.ceil(totalEntries * 0.10);
		
	    return combinedEntryList.subList(0, numEntriesToSelect);
	}
		
	
	// for question 12 
	
   
		
	/* ========== Variables ========== */
	private List<VariableDeclarationFragment> variables = new ArrayList<VariableDeclarationFragment>();
	@Override
	public boolean visit(VariableDeclarationFragment node) {
		variables.add(node);
		return super.visit(node);
	}
	
	public List<VariableDeclarationFragment> getVariables() {
		return variables;
	}
	
	public int getNbrVariables() {
		return variables.size();
	}
	
	public double getMoyenAttributeClass() {
		
		return 0;
	}

		
		
	/* ========== Lines ========== */
		
	private int lineCount = 0;
    private int totalMethods=0;
    private int totalLines = 0;
    private int methodCount = 0;

    @Override
    public boolean visit(CompilationUnit node) {
        // Réinitialisez les compteurs pour chaque fichier
        lineCount = 0;
        methodCount = 0;
        for (Object obj : node.types()) {
            if (obj instanceof TypeDeclaration) {
                TypeDeclaration typeDeclaration = (TypeDeclaration) obj;

                int startLine = node.getLineNumber(typeDeclaration.getStartPosition());
                int endLine = node.getLineNumber(typeDeclaration.getStartPosition() + typeDeclaration.getLength() - 1);
                lineCount += endLine - startLine + 1;

                MethodDeclaration[] methods = typeDeclaration.getMethods();
                methodCount += methods.length;
            }
        }
        totalLines += lineCount;
        totalMethods += methodCount;

        return super.visit(node);
    }

    public int getLineCount() {
        return totalLines;
    }
    	

	
	
	
}
