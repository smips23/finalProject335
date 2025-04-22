package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import model.Grid;
import model.Card;
import view.GameView;

public class Game implements ActionListener {

    private Grid grid;
    private GameView gameView;
    private Card firstCard;
    private Timer flipBackTimer;
    
    /**
     * Create a new game with mode and difficulty
     */
    public Game(String mode, String difficulty) {
        initGrid(difficulty);
        initGameView();
        initTimer();
    }
    
    private void initGrid(String difficulty) {
        int difficultyLevel = Grid.EASY;
        
        if ("normal".equals(difficulty)) {
            difficultyLevel = Grid.MEDIUM;
        } else if ("hard".equals(difficulty)) {
            difficultyLevel = Grid.HARD;
        }
        
        grid = new Grid(0, 0);
        grid.createGrid(difficultyLevel);
    }

    private void initGameView() {
        gameView = new GameView(grid.getRows(), grid.getColumns(), this);
        gameView.updateGrid(grid);
        gameView.setVisible(true);
    }

    private void initTimer() {
        flipBackTimer = new Timer(1000, e -> {
            resetCards();
            updateGameView();
        });
        flipBackTimer.setRepeats(false);
    }
    
    /**
     * Handle button clicks
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        
        if ("back_to_menu".equals(command)) {
            gameView.dispose();
        } else if (command.contains(",")) {
            handleCardSelection(command);
        }
    }
    
    private void handleCardSelection(String command) {
        String[] coords = command.split(",");
        int row = Integer.parseInt(coords[0]);
        int col = Integer.parseInt(coords[1]);
        Card card = grid.getCard(row, col);

        if (card == null || card.isFlipped() || card.isLocked()) {
            return;
        }
        card.flip();
        
        if (firstCard == null) {
            selectFirstCard(card);
        } else {
            selectSecondCard(card);
        }
        
        updateGameView();
    }
    
    /**
     * first card
     */
    private void selectFirstCard(Card card) {
        firstCard = card;
        card.highlight();
        gameView.updateStatus("Select a second card");
    }
    
    /**
     * second card
     */
    private void selectSecondCard(Card card) {
        if (firstCard.getValue() == card.getValue()) {
            handleMatch(card);
        } else {
            handleMismatch(card);
        }
        
        firstCard.unhighlight();
        firstCard = null;
    }
    
    private void handleMatch(Card card) {
        firstCard.lock();
        card.lock();
        gameView.updateStatus("Match found!");
    }
    
    private void handleMismatch(Card card) {
        gameView.updateStatus("Not a match. Try again.");
        flipBackTimer.restart();
    }
 
    private void resetCards() {
        for (int r = 0; r < grid.getRows(); r++) {
            for (int c = 0; c < grid.getColumns(); c++) {
                Card card = grid.getCard(r, c);
                if (card != null && card.isFlipped() && !card.isLocked()) {
                    card.flip();
                }
            }
        }
    }
    
    private void updateGameView() {
        gameView.updateGrid(grid);
    }
}