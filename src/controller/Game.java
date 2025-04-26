package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import model.Grid;
import model.Card;
import view.GameView;
import java.util.ArrayList;
import model.Observer;

import model.SpecialCard;

public class Game implements ActionListener, Observer {

	private ArrayList<Observer> observers;
    private Grid grid;
    private GameView gameView;
    private Card firstCard;
    private Card secondCard;
    private Timer flipBackTimer;
    private int secondsRemaining;
    private int lives;
    private boolean manualWin;
    private ArrayList<Card> seenCardValues;
    private boolean alreadySeen;
    private boolean isTimedMode;
    private boolean isLivesMode;
    
    /**
     * Create a new game with mode and difficulty
     */
    public Game(String mode, String difficulty) {
        isTimedMode = "timed".equals(mode);
        isLivesMode = "lives".equals(mode);
        if(mode.equals("crazy")) {
        	isLivesMode = true;
        	isTimedMode = true;
        }
        initGrid(difficulty);
        initGameView();
        if(isTimedMode) {
        	initTimer();
        }
    }
    
    // create a grid with size based on difficulty
    private void initGrid(String difficulty) {
        int difficultyLevel = Grid.EASY;
        
        if ("normal".equals(difficulty)) {
            difficultyLevel = Grid.MEDIUM;
        } else if ("hard".equals(difficulty)) {
            difficultyLevel = Grid.HARD;
        }
        
        grid = new Grid(0, 0);
        grid.createGrid(difficultyLevel);
        grid.registerObserver(this);
    }

    // Update/create the view with the created grid and based on mode
    private void initGameView() {
        gameView = new GameView(grid.getRows(), grid.getColumns(), this);
        gameView.updateGrid(grid);
        gameView.setVisible(true);
        lives = 3;
        if(isLivesMode) {
        	gameView.updateLife("Lives: " + lives);
        }
        alreadySeen = false;
        seenCardValues = new ArrayList<Card>();
    }

    // creates the Timer starting at 60 seconds and updates the gameView
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
                freezeAll();
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
            if(isTimedMode) {
            	flipBackTimer.stop();
            }
        } else if (command.contains(",")) {
            handleCardSelection(command);
        }
    }
    
    // When a card is selected this will be called and carry out card effects
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
            if(isTimedMode) {
	            if (!flipBackTimer.isRunning()) {
	                startTimer(); // Start timer on first card selection
	            }
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
        	resetCardStatus();
    	}
        // set new first card
        firstCard = card;
        firstCard.flip(grid);
        firstCard.highlight();
        grid.addRecentCards(card);
        gameView.updateStatus("Select a second card");
        
        checkForSpecial(firstCard);
        
        // set label to already seen notif if card has been seen
        if (isLivesMode) {
	        if (!addSeenCardValue(firstCard)) {
	        	gameView.updateLife("Lives: " + lives + " !SEEN!");
	        	alreadySeen = true;
	        }
        }
    }
    
    /**
     * second card
     */
    private void selectSecondCard(Card card) {
    	secondCard = card;
    	secondCard.highlight();
    	secondCard.flip(grid);
    	grid.addRecentCards(card);
    	addSeenCardValue(secondCard);
        if (firstCard.getValue() == secondCard.getValue() && firstCard != secondCard) {
            handleMatch();
        } else {
            handleMismatch();
        }
    }
    
    // reset the card's status on certain cases
    private void resetCardStatus(){
    	for (Card card : grid){
    		if (card.isFrozen()){
    			card.unfreeze();
    		}
    		if (!card.isLocked()){
    			if (card.isHighlighted()){
    				card.unhighlight();
    				}
    			if (card.isFlipped()){
    				card.setFlip(false);
    			}
    		}
    	}
    }
    
    // handles what happens when both selected cards are pairs
    private void handleMatch() {
        firstCard.lock();
        secondCard.lock();
        gameView.updateStatus("Match found!");
        if(alreadySeen && isLivesMode) {
        	gameView.updateLife("Lives: " + lives);
        	alreadySeen = false;
        }
        checkForSpecial(secondCard);
        checkGameOver();
    }
    
    // handles what happens when both selected cards are not pairs
    private void handleMismatch() {
        gameView.updateStatus("Not a match. Try again.");
        checkForSpecial(secondCard);
        if(alreadySeen && isLivesMode) {
        	lives--;
        	gameView.updateLife("Lives: " + lives);
        	alreadySeen = false;
        	checkGameOver();
        }
    }
 
    // reset all of the cards in the grid to end the game
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
    
    // freeze cards to end the game on a loss
    private void freezeAll() {
    	for (int r = 0; r < grid.getRows(); r++) {
            for (int c = 0; c < grid.getColumns(); c++) {
                Card card = grid.getCard(r, c);
                card.freeze();
            }
        }
    	firstCard = null;
    	secondCard = null;
    }
    
    private void updateGameView() {
        gameView.updateGrid(grid);
    }
    
    // checks for all cards matched or time/lives are gone
    private void checkGameOver() {
    	// check for life loss
    	if (lives == 0 && isLivesMode) {
    		if (isTimedMode) {
    			flipBackTimer.stop();
    		}
    		gameView.updateStatus("You lose! All lives gone!");
    		resetCards();
    		freezeAll();
    		updateGameView();
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
        if (allLocked || manualWin) {
        	if (isTimedMode) {
        		flipBackTimer.stop();
        	}
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
    
    // if selected Card is a SpecialCard update the gameView
    private void checkForSpecial(Card card) {
    	if (card instanceof SpecialCard) {
    		gameView.updateStatus(((SpecialCard)card).getAbilityMessage());
    	}
    }
    
    // add an observer
    public void registerObserver(Observer o) {
    	this.observers.add(o);
    }
    
    // remove an observer
    public void deregisterObserver(Observer o) {
    	this.observers.remove(o);
    }
    
    // notify observers
    private void notifyObservers(String event, Object obj) {
    	for (Observer o : observers) {
    		o.update(event, obj);
    	}
    }

    // update console when event happens
	@Override
	public void update(String event, Object obj) {
		if (event.equals("card_flipped")) {
			System.out.println("Card was flipped to face");
		}
		else if (event.equals("pair_found")){
			System.out.println("Pair found");
		}
	}
	
	// used for testing
    public boolean getTimedMode() {
    	return this.isTimedMode;
    }
    
    // used for testing
    public boolean getLivesMode() {
    	return this.isLivesMode;
    }
    
    // used for testing
    public void setSeen() {
    	alreadySeen = true;
    }
    
    // used for testing
    public void forceSpecialCard(SpecialCard c) {
    	this.checkForSpecial(c);
    }
    
    // used for testing
    public void endLives() {
    	this.lives = 0;
    }
    
    // used for testing
    public void setWin() {
    	manualWin = true;
    }
    
    // used for testing
    public void forceGameOver() {
    	this.checkGameOver();
    }
    
}