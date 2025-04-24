package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.Controller;
import model.Model;

public class UI extends JFrame {
	private Controller controller;
	private JLabel modeLabel;
	private JLabel difficultyLabel;
	private CardLayout layout;
	private JPanel cardPanel;
	
	public UI() {
		this.controller = new Controller(this, new Model());	
		
		this.setTitle("Matching Game (Main menu)");
		this.setSize(500, 150);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setUp();
	}
	
	public void setUp() {
		layout = new CardLayout();
		cardPanel = new JPanel(layout);
		
		modeLabel = new JLabel("Welcome to our card matching game! Please choose a game mode!", JLabel.CENTER);
		modeLabel.setSize(250, 100);
		
		JPanel modePanel = new JPanel();
		modePanel.add(modeLabel);
		
		JButton standardButton = new JButton("Standard Mode");
		standardButton.setActionCommand("standard");
		standardButton.addActionListener(controller);
		modePanel.add(standardButton);
		
		JButton timedButton = new JButton("Timed Mode");
		timedButton.setActionCommand("timed");
		timedButton.addActionListener(controller);
		modePanel.add(timedButton);
		
		JButton livesButton = new JButton("Limited Life Mode");
		livesButton.setActionCommand("lives");
		livesButton.addActionListener(controller);
		modePanel.add(livesButton);
		
		JButton specialButton = new JButton("Crazy Mode");
		specialButton.setActionCommand("crazy");
		specialButton.addActionListener(controller);
		modePanel.add(specialButton);
		
		difficultyLabel = new JLabel("Please choose a difficulty level", JLabel.CENTER);
		modeLabel.setSize(250, 100);
		
		JPanel difficultyPanel = new JPanel();
		difficultyPanel.add(difficultyLabel);
		
		JButton easyButton = new JButton("Easy");
		easyButton.setActionCommand("easy");
		easyButton.addActionListener(controller);
		difficultyPanel.add(easyButton);
		
		JButton normalButton = new JButton("Normal");
		normalButton.setActionCommand("normal");
		normalButton.addActionListener(controller);
		difficultyPanel.add(normalButton);
		
		JButton hardButton = new JButton("Hard");
		hardButton.setActionCommand("hard");
		hardButton.addActionListener(controller);
		difficultyPanel.add(hardButton);
		
		JButton backButton = new JButton("Go Back");
		backButton.setActionCommand("back");
		backButton.addActionListener(controller);
		difficultyPanel.add(backButton);
		
		cardPanel.add(modePanel, "mode");
		cardPanel.add(difficultyPanel, "difficulty");
		
		this.add(cardPanel);
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});
		
		this.setVisible(true);
	}

	public void switchCard(String str) {
		layout.show(cardPanel, str);
	}
	
	/**
	public static void main(String[] args) {
		UI ui = new UI();
	}
	**/
}

