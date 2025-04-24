package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.UI;
import model.Model;

public class Controller implements ActionListener {
	private UI gui;
	private Model model;
	private String mode;
	private String difficulty;
	
	public Controller(UI gui, Model model) {
		this.gui = gui;
		this.model = model;
		this.mode = null;
		this.difficulty = null;
	}	

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if (command.equals("standard")) {
			mode = "standard";
			gui.switchCard("difficulty");
		}
		if (command.equals("timed")) {
			mode = "timed";
			gui.switchCard("difficulty");
		}
		if (command.equals("lives")) {
			mode = "lives";
			gui.switchCard("difficulty");
		}
		if (command.equals("crazy")) {
			mode = "crazy";
			gui.switchCard("difficulty");
		}	
		if (command.equals("easy")) {
			difficulty = "easy";
		}
		if (command.equals("normal")) {
			difficulty = "normal";
		}
		if (command.equals("hard")) {
			difficulty = "hard";
		}
		if (command.equals("back")) {
			mode = null;
			difficulty = null;
			gui.switchCard("mode");
		}
		if (mode != null && difficulty != null) {
			Game game = new Game(mode, difficulty);
		}
	}
}

