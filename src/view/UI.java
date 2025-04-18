package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.Controller;

public class UI extends JFrame {
	private Controller controller;
	private MainLabel mainLabel;
	
	public UI() {
		this.controller = new Controller();		
		
		this.setTitle("Matching Game (Main menu)");
		this.setSize(500, 150);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setUp();
	}
	
	public void setUp() {
		JLabel mainLabel = new JLabel("Welcome to our card matching game! Please choose a game mode!", JLabel.CENTER);
		mainLabel.setSize(250, 100);
		
		JPanel mainPanel = new JPanel();
		mainPanel.add(mainLabel);
		this.add(mainPanel, BorderLayout.CENTER);
		
		JButton standardButton = new JButton("Standard Mode");
		standardButton.setActionCommand("standard");
		standardButton.addActionListener(controller);
		mainPanel.add(standardButton);
		
		JButton timedButton = new JButton("Timed Mode");
		timedButton.setActionCommand("timed");
		timedButton.addActionListener(controller);
		mainPanel.add(timedButton);
		
		JButton livesButton = new JButton("Limited Life Mode");
		livesButton.setActionCommand("lives");
		livesButton.addActionListener(controller);
		mainPanel.add(livesButton);
		
		JButton specialButton = new JButton("Special Card Mode");
		specialButton.setActionCommand("timed");
		specialButton.addActionListener(controller);
		mainPanel.add(specialButton);
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});
		
		this.setVisible(true);
	}

	public static void main(String[] args) {
		UI gui = new UI();
	}
}

