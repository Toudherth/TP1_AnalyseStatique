package com.evolution.gui;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;

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
	JButton bouton1 = createStyledButton("Exercice 1");
	JButton bouton2 = createStyledButton("Exercice 2");
	JButton bouton3 = createStyledButton("Exercice 3");
	JButton bouton4 = createStyledButton("Exercice 4");
	JButton bouton5 = createStyledButton("Exercice 5");
	JButton bouton6 = createStyledButton("Exercice 6");
	JButton bouton7 = createStyledButton("Exercice 7");
	JButton bouton8 = createStyledButton("Exercice 8");
	JButton bouton9 = createStyledButton("Exercice 9");
	JButton bouton10 = createStyledButton("Exercice 10");
	JButton bouton11 = createStyledButton("Exercice 11");
	JButton bouton12 = createStyledButton("Exercice 12");
	JButton bouton13 = createStyledButton("Exercice 13");


	public Window(Processor processeur) {
		this.processeur = processeur;
		this.setTitle("Processeur");
		this.setSize(600, 400);
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

	private JButton createStyledButton(String text) {
		JButton button = new JButton(text);
		button.setBackground(Color.WHITE);
		button.setForeground(Color.BLUE);
		button.setFont(new Font("Arial", Font.BOLD, 18));
		// Définissez la taille préférée du bouton (largeur x hauteur)
		button.setPreferredSize(new Dimension(100, 20));
		button.addActionListener(this);
		return button;
	}
	private static JButton createColoredButton(String text, Color color) {
		JButton button = new JButton(text);
		button.setBackground(color); // Définir la couleur d'arrière-plan du bouton
		button.setOpaque(true); // Assurez-vous que le bouton est opaque pour voir la couleur
		return button;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String res;

		if (arg0.getSource().equals(bouton1)) {
			try {
				res = processeur.exercice1();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			JOptionPane.showMessageDialog(pan, res, "Resultat", JOptionPane.CLOSED_OPTION);
		} else if (arg0.getSource().equals(bouton2)) {
			try {
				res = processeur.exercice2();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			JOptionPane.showMessageDialog(pan, res, "Resultat", JOptionPane.CLOSED_OPTION);
		} else if (arg0.getSource().equals(bouton3)) {
			try {
				res = processeur.exercice3();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			JOptionPane.showMessageDialog(pan, res, "Resultat", JOptionPane.CLOSED_OPTION);
		} else if (arg0.getSource().equals(bouton4)) {
			try {
				res = processeur.exercice4();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			JOptionPane.showMessageDialog(pan, res, "Resultat", JOptionPane.CLOSED_OPTION);
		} else if (arg0.getSource().equals(bouton5)) {
			try {
				res = processeur.exercice5();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			JOptionPane.showMessageDialog(pan, res, "Resultat", JOptionPane.CLOSED_OPTION);
		} else if (arg0.getSource().equals(bouton6)) {
			try {
				res = processeur.exercice6();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			JOptionPane.showMessageDialog(pan, res, "Resultat", JOptionPane.CLOSED_OPTION);
		} else if (arg0.getSource().equals(bouton7)) {
			try {
				res = processeur.exercice7();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			JOptionPane.showMessageDialog(pan, res, "Resultat", JOptionPane.CLOSED_OPTION);
		} else if (arg0.getSource().equals(bouton8)) {
			try {
				res = processeur.exercice8();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			JOptionPane.showMessageDialog(pan, res, "Resultat", JOptionPane.CLOSED_OPTION);
		} else if (arg0.getSource().equals(bouton9)) {
			try {
				res = processeur.exercice9();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			JOptionPane.showMessageDialog(pan, res, "Resultat", JOptionPane.CLOSED_OPTION);
		} else if (arg0.getSource().equals(bouton10)) {
			try {
				res = processeur.exercice10();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			JOptionPane.showMessageDialog(pan, res, "Resultat", JOptionPane.CLOSED_OPTION);
		} else if (arg0.getSource().equals(bouton11)) {
			String val = JOptionPane.showInputDialog("Nombre de methodes ?");
			int valInt = Integer.parseInt(val);
			try {
				res = processeur.exercice11(valInt);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			JOptionPane.showMessageDialog(pan, res, "Resultat", JOptionPane.CLOSED_OPTION);
		} else if (arg0.getSource().equals(bouton12)) {
			try {
				res = processeur.exercice12();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			JOptionPane.showMessageDialog(pan, res, "Resultat", JOptionPane.CLOSED_OPTION);
		} else if (arg0.getSource().equals(bouton13)) {
			try {
				res = processeur.exercice13();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			JOptionPane.showMessageDialog(pan, res, "Resultat", JOptionPane.CLOSED_OPTION);
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


