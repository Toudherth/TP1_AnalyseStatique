package com.evolution.gui;

import java.awt.event.ActionListener;
import java.awt.Image;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.evolution.analyse_statique.Processor;

public class Window extends JFrame implements ActionListener{

	public Processor processeur;

	private JPanel pan = new JPanel();
	private JButton bouton1 = new JButton("Nombre de classe");
	private JButton bouton2 = new JButton("Nombre de lignes de code de l'application");
	private JButton bouton3 = new JButton("Nombre total de methodes de l'application");
	private JButton bouton4 = new JButton("Nombre total de packages de l'application");
	private JButton bouton5 = new JButton("Nombre moyen de methodes par classe");
	private JButton bouton6 = new JButton("Nombre moyen de lignes de code par methode");
	private JButton bouton7 = new JButton("Nombre moyen d'attributs par classe");
	private JButton bouton8 = new JButton("Les 10% des classes qui possedent le plus grand nombre de methodes");
	private JButton bouton9 = new JButton("Les 10% des classes qui possedent le plus grand nombre d'attributs");
	private JButton bouton10 = new JButton("Les classes qui font partie en meme temps des deux categories precedentes");
	private JButton bouton11 = new JButton("Les classes qui possedent plus de X methodes");
	private JButton bouton12 = new JButton(
			"Les 10% des methodes qui possedent le plus grand nombre delignes de code (par classe)");
	private JButton bouton13 = new JButton(
			"Le nombre maximal de parametres par rapport a toutes les methodes de l'application.");

	public Window(Processor processeur) {
		this.processeur = processeur;
		this.setTitle("Processeur");
		this.setSize(800, 500);
		this.setLocationRelativeTo(null);
		this.setVisible(true);

		pan.add(bouton1);
		this.bouton1.addActionListener(this);
		pan.add(bouton2);
		this.bouton2.addActionListener(this);
		pan.add(bouton3);
		this.bouton3.addActionListener(this);
		pan.add(bouton4);
		this.bouton4.addActionListener(this);
		pan.add(bouton5);
		this.bouton5.addActionListener(this);
		pan.add(bouton6);
		this.bouton6.addActionListener(this);
		pan.add(bouton7);
		this.bouton7.addActionListener(this);
		pan.add(bouton8);
		this.bouton8.addActionListener(this);
		pan.add(bouton9);
		this.bouton9.addActionListener(this);
		pan.add(bouton10);
		this.bouton10.addActionListener(this);
		pan.add(bouton11);
		this.bouton11.addActionListener(this);
		pan.add(bouton12);
		this.bouton12.addActionListener(this);
		pan.add(bouton13);
		this.bouton13.addActionListener(this);

		this.setContentPane(pan);
		this.setVisible(true);

		this.setLayout(new GridLayout(5, 5));
		this.setVisible(true);
	}

	public Window() {
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String res;

		if (arg0.getSource().equals(bouton1)) {
			//res = processeur.exercice1();
			//JOptionPane.showMessageDialog(pan, res, "Resultat", JOptionPane.CLOSED_OPTION);
		} else if (arg0.getSource().equals(bouton2)) {
			//res = processeur.exercice2();
			//JOptionPane.showMessageDialog(pan, res, "Resultat", JOptionPane.CLOSED_OPTION);
		} else if (arg0.getSource().equals(bouton3)) {
			//res = processeur.exercice3();
			//JOptionPane.showMessageDialog(pan, res, "Resultat", JOptionPane.CLOSED_OPTION);
		} else if (arg0.getSource().equals(bouton4)) {
			//res = processeur.exercice4();
			//JOptionPane.showMessageDialog(pan, res, "Resultat", JOptionPane.CLOSED_OPTION);
		} else if (arg0.getSource().equals(bouton5)) {
			//res = processeur.exercice5();
			//JOptionPane.showMessageDialog(pan, res, "Resultat", JOptionPane.CLOSED_OPTION);
		} else if (arg0.getSource().equals(bouton6)) {
			//res = processeur.exercice6();
			//JOptionPane.showMessageDialog(pan, res, "Resultat", JOptionPane.CLOSED_OPTION);
		} else if (arg0.getSource().equals(bouton7)) {
			//res = processeur.exercice7();
			//JOptionPane.showMessageDialog(pan, res, "Resultat", JOptionPane.CLOSED_OPTION);
		} else if (arg0.getSource().equals(bouton8)) {
			//res = processeur.exercice8();
			//JOptionPane.showMessageDialog(pan, res, "Resultat", JOptionPane.CLOSED_OPTION);
		} else if (arg0.getSource().equals(bouton9)) {
			//res = processeur.exercice9();
			//JOptionPane.showMessageDialog(pan, res, "Resultat", JOptionPane.CLOSED_OPTION);
		} else if (arg0.getSource().equals(bouton10)) {
			//res = processeur.exercice10();
			//JOptionPane.showMessageDialog(pan, res, "Resultat", JOptionPane.CLOSED_OPTION);
		} else if (arg0.getSource().equals(bouton11)) {
			String val = JOptionPane.showInputDialog("Nombre de methodes ?");
			int valInt = Integer.parseInt(val);
			//res = processeur.exercice11(valInt);
			//JOptionPane.showMessageDialog(pan, res, "Resultat", JOptionPane.CLOSED_OPTION);
		} else if (arg0.getSource().equals(bouton12)) {
			//res = processeur.exercice12();
			//JOptionPane.showMessageDialog(pan, res, "Resultat", JOptionPane.CLOSED_OPTION);
		} else if (arg0.getSource().equals(bouton13)) {
			//res = processeur.exercice13();
			//JOptionPane.showMessageDialog(pan, res, "Resultat", JOptionPane.CLOSED_OPTION);
		}

	}

	public void showGraph() {
		JFrame frame = new JFrame();
		ImageIcon icon = new ImageIcon("graph.png");
		JLabel label = new JLabel(icon);
		JScrollPane scrollPane = new JScrollPane(label);
		frame.add(scrollPane);
		frame.pack();
		frame.setVisible(true);
	}

}


