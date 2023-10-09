package com.evolution.analyse_statique;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;
import org.eclipse.jdt.core.dom.CompilationUnit;

import com.evolution.gui.Window;
import com.evolution.visitors.StateVisitor;


public class Main {
	
	
	// main execution 
	
	public static final String path = "C:\\Users\\DELL\\OneDrive\\Desktop\\src";
	public static final String projectSourcePath = path + "\\src";
	public static final String jrePath = "C:\\Program Files\\Common Files\\Oracle\\Java\\javapath";


	public static ParserAST parse;
	
	
	public static StateVisitor stateVisitor;
	//static String path= projectPath;
	public final static String indentationFormat = "\s|\s-\t";

	final static File folder = new File(projectSourcePath);
	public static ArrayList<File> javaFiles = parse.listJavaFilesForFolder(folder);
	
    // main 
	public static void main(String[] args) throws IOException {	   
	        if(!(folder.getName().length() == 0)) {
	        	Main.Menu();
	        }  else {
	        	System.out.println("Veuillez insérer le chemin de dossier à analyser en argument");
	        }	    
	  
	}
	
	
	
	public static void Menu() throws FileNotFoundException, IOException {
		Scanner sc = new Scanner(System.in);
		String choice = "";
		Processor processor;
		Window window;
		processor = new Processor();
		processor.process();
		processor.processGraph();
		processor.saveGraph();
		processor.saveGraphAsPNG();
		while (true) {
			System.out.println("-----------------------------------------------------------------------------------");
			System.out.println("***		BIENVENUE dans application de l'analyse statique d'un programme		***");
			System.out.println("-----------------------------------------------------------------------------------");
			System.out.println("Pour obtenir les informations de la question 1.1 du projet : tapez 1");
			System.out.println("Pour la version graphique de l'exercice 1.2 : tapez 2");
			System.out.println("Pour le graphe d'appels 2.1 : tapez 3");
			System.out.println("Pour la version graphique de l'exercice 2.2 : tapez 4");
			System.out.println("Pour quitter tapez 0");
			choice = sc.nextLine();
			switch (choice) {
			case "1": {

				processor.display();
				System.out.println(" ");
				break;
			}
			case "2": {
				System.out.println("La fenetre va s'ouvrir !");
				window = new Window(processor);
				break;
			}
			case "3": {
				System.out.println("Le graphe d'appels est :");
				System.out.println(processor.getGraph());
				break;
			}
			case "4": {
				System.out.println("La fenetre va s'ouvrir !");
				window = new Window();
				window.showGraph();
				;
				break;
			}
			case "0": {
				System.out.println("Au revoir ! ");
				sc.close();
				System.exit(0);
				break;
			}
			default:
				System.out.println("Votre choix ne figure pas dans noter proposition !!!! ( 1, 2, 3, 4, 0) ");
				break;
			}

		}

	}
	
	

	

}
