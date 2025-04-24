package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import model.Grid;
import model.Card;
import view.GameView;
import java.util.ArrayList;
import model.SpecialCard;

public class Game implements ActionListener {

    private Grid grid;
    private GameView gameView;
    private Card firstCard;
    private Card secondCard;
    private Timer flipBackTimer;
    private int secondsRemaining;
    private int lives;
    private ArrayList<Card> seenCardValues;
    private boolean alreadySeen;
    
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
        lives = 3;
        gameView.updateLife("Lives: " + lives);
        alreadySeen = false;
        seenCardValues = new ArrayList<Card>();
    }

    
    private void initTimer() {
        secondsRemaining = 60; 
        gameView.updateTime(formatTime(secondsRemaining));

        flipBackTimer = new Timer(1000, e -> { 
            secondsRemaining--;
            if (secondsRemaining >= 0) {
                gameView.updateTime(formatTime(secondsRemaining));
            }
            if (secondsRemaining <= 0) {
                flipBackTimer.stop(); 
                resetCards(); 
                updateGameView();
                gameView.updateStatus("Time's up! Game over.");
            }
        });
        flipBackTimer.setRepeats(true);
    }

    // Helper method to format seconds into "MM:SS"
    private String formatTime(int seconds) {
        int minutes = seconds / 60;
        int secs = seconds % 60;
        return String.format("%d:%02d", minutes, secs); // "1:00", "0:45"
    }
    
    // Start or reset timer
    private void startTimer() {
        if (flipBackTimer != null && !flipBackTimer.isRunning()) {
            secondsRemaining = 60; // Reset to 60 seconds
            gameView.updateTime(formatTime(secondsRemaining)); // Update initial label
            flipBackTimer.start();
        }
    }
    
    /**
     * Handle button clicks
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        
        if ("back_to_menu".equals(command)) {
            gameView.dispose();
            flipBackTimer.stop();
        } else if (command.contains(",")) {
            handleCardSelection(command);
        }
    }
    
    private void handleCardSelection(String command) {
        String[] coords = command.split(",");
        int row = Integer.parseInt(coords[0]);
        int col = Integer.parseInt(coords[1]);
        Card card = grid.getCard(row, col);

        
        if (card == null || card.isLocked()) {
            return;
        }
       
        
        if (firstCard == null) {
            selectFirstCard(card);
            if (!flipBackTimer.isRunning()) {
                startTimer(); // Start timer on first card selection
            }
        } else if (secondCard != null){
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
    	// handle previously picked cards
    	if (firstCard != null) {
    		firstCard.flip(grid);
    		firstCard.unhighlight();
    		if (secondCard != null) {
    			secondCard.flip(grid);
    			secondCard.unhighlight();
    			secondCard = null;
    		}
    	}
        // set new first card
        firstCard = card;
        firstCard.flip(grid);
        firstCard.highlight();
        gameView.updateStatus("Select a second card");
        
        checkForSpecial(firstCard);
        
        // set label to already seen notif if card has been seen
        if (!addSeenCardValue(firstCard)) {
        	gameView.updateLife("Lives: " + lives + " !SEEN!");
        	alreadySeen = true;
        }
    }
    
    /**
     * second card
     */
    private void selectSecondCard(Card card) {
    	secondCard = card;
    	secondCard.highlight();
    	secondCard.flip(grid);
    	addSeenCardValue(secondCard);
    	checkForSpecial(firstCard);
        if (firstCard.getValue() == secondCard.getValue() && firstCard != secondCard) {
            handleMatch();
        } else {
            handleMismatch();
        }
    }
    
    private void handleMatch() {
        firstCard.lock();
        secondCard.lock();
        gameView.updateStatus("Match found!");
        if(alreadySeen) {
        	gameView.updateLife("Lives: " + lives);
        	alreadySeen = false;
        }
        checkGameOver();
    }
    
    private void handleMismatch() {
        gameView.updateStatus("Not a match. Try again.");
        if(alreadySeen) {
        	lives--;
        	gameView.updateLife("Lives: " + lives);
        	alreadySeen = false;
        	checkGameOver();
        }
    }
 
    private void resetCards() {
        for (int r = 0; r < grid.getRows(); r++) {
            for (int c = 0; c < grid.getColumns(); c++) {
                Card card = grid.getCard(r, c);
                if (card != null && card.isFlipped()) {
                	card.unlock();
                    card.flip(grid);
                    card.unhighlight();
                }
            }
        }
        firstCard = null;
        secondCard = null;
    }
    
    private void updateGameView() {
        gameView.updateGrid(grid);
    }
    
    private void checkGameOver() {
    	// check for life loss
    	if (lives == 0) {
    		flipBackTimer.stop();
    		gameView.updateStatus("You lose! All lives gone!");
    		resetCards();
    	}
    	
    	// check for match win
        boolean allLocked = true;
        for (int r = 0; r < grid.getRows(); r++) {
            for (int c = 0; c < grid.getColumns(); c++) {
                Card card = grid.getCard(r, c);
                if (card != null && !card.isLocked()) {
                    allLocked = false;
                    break;
                }
            }
        }
        if (allLocked) {
            flipBackTimer.stop();
            gameView.updateStatus("Congratulations! You won!");
        }
    }
    
    // returns true if successfully added non duplicate val, returns false if already exists
    private boolean addSeenCardValue(Card card) {
    	if (!seenCardValues.contains(card)) {
    		seenCardValues.add(card);
    	}
    	for (int i = 0; i < seenCardValues.size(); i++) {
    		if (seenCardValues.get(i).getValue() == card.getValue() && seenCardValues.get(i) != card) {
    			return false;
    		}
    	}
    	
    	return true;
    }
    
    private void checkForSpecial(Card card) {
    	if (card instanceof SpecialCard) {
    		gameView.updateStatus(((SpecialCard)card).getAbilityMessage());
    	}
    }
    
}