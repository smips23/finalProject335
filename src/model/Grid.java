package model;


import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.Observer;

public class Grid implements Iterable<Card>{

	private ArrayList<Observer> observers;
    protected ArrayList<Card> recentCards;
    private Card[][] cards;
    private int rows;
    private int columns;
    private int difficulty;
    private int remainingPairs;

    public static final int EASY = 1;
    public static final int MEDIUM = 2;
    public static final int HARD = 3;

    public Grid(int rows, int columns){
    	this.observers = new ArrayList<Observer>();
        this.rows = rows;
        this.columns = columns;
        this.cards = new Card[rows][columns];
        this.recentCards = new ArrayList<Card>();
   
    }
    // getters for all instance variables

    // return grid rows
    public int getRows(){
        return rows;
    }

    // return grid columns
    public int getColumns() {
        return columns;
    }

    // return difficulty
    public int getDifficulty() {
        return difficulty;
    }

    // return remainingPairs
    public int getRemainingPairs(){
    	return remainingPairs;
    }
    
    // if pair is found decrease pairsFound by one
    public void pairFound(){
    	remainingPairs--;
    }
    
    // check whether row and col selection is valid for grid
    public boolean isValidPosition(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < columns;
    }
    
    // return Card at valid position
    public Card getCard(int row, int col) {
        if (!isValidPosition(row, col)) {
            return null;
        }
        return cards[row][col];
    }
    
    // create grid based on difficulty
    public boolean createGrid(int difficulty) {
        this.difficulty = difficulty;
      
        switch (difficulty) {
            case EASY:
                rows = 4;
                columns = 4;
                break;
            case MEDIUM:
                rows = 5;
                columns = 5;
                break;
            case HARD:
                rows = 6;
                columns = 6;
                break;
            default:
                return false; 
        }
        cards = new Card[rows][columns];
        
        ArrayList<Card> cardPairs = generateCardPairs();
        populateGrid(cardPairs);  
        return true;
    }

    // add cards into cards[][] to populate grid
    private void populateGrid(ArrayList<Card> cardPairs) {
        int index = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                if (index < cardPairs.size()) {
                    cards[r][c] = cardPairs.get(index);
                    index++;
                }
            }
        }
    }

    // create number of card pairs based on the difficulty
    private ArrayList<Card> generateCardPairs() {
        ArrayList<Card> cardPairs = new ArrayList<>();
        
        int totalCells = rows * columns;
        int numPairs;
        int numSpecialCards = 0;
        
        // number of specials
        if (difficulty == MEDIUM) {
            numSpecialCards = 1;
        } else if (difficulty == HARD) {
            numSpecialCards = 4; 
        }
        // Calculate number of pairs accounting for special cards
        numPairs = (totalCells - numSpecialCards) / 2;
        remainingPairs = numPairs;
        
        // Create pairs of cards with matching values
        for (int i = 0; i < numPairs; i++) {
            Card card1 = new Card();
            card1.setValue(i + 1);
            
            Card card2 = new Card();
            card2.setValue(i + 1);
            
            cardPairs.add(card1);
            cardPairs.add(card2);
        }
        // Add special cards if needed
        for (int i = 0; i < numSpecialCards; i++) {
            SpecialCard specialCard = new SpecialCard();
            specialCard.setValue(-1 - i); 
            cardPairs.add(specialCard);
        }
        Collections.shuffle(cardPairs);
        return cardPairs;
    }
    
    // update grid with current cards info
    public void updateGrid(Grid grid){
    	this.cards = grid.cards;
    }
    
    // flip a Card in grid position
    public void flipCard(int row, int col) {
    	Card c = this.getCard(row, col);
    	if (!c.isFlipped()) {
    		c.flip(this);
    		notifyObservers("card_flipped", c);
    	}
    }

    // manually change a certain Card at a grid position
    public void setGridCell(Card card, int row, int column){
    	if (row > this.rows || column > this.columns){
    	}
    	else{
    		cards[row][column] = card;
    	}
    }
    
    // copy and return this grid
    public Grid copy(){
    	Grid grid = new Grid(this.rows, this.columns);
    	grid.cards = this.cards.clone();
    	return grid;
    }
    
    // iterator for Grid
	@Override
	public Iterator<Card> iterator() {
		return new Iterator<Card>(){
			private int columnIndex = 0;
			private int rowIndex = 0;
			@Override
			public boolean hasNext() {
				while (rowIndex < cards.length && columnIndex >= cards[rowIndex].length) {
	                rowIndex++;
	                columnIndex = 0;
	            }
	            return rowIndex < cards.length;
	        }
			
			@Override
			public Card next() {
				if (hasNext()) {
					return cards[rowIndex][columnIndex++];
	            }
				else{
					return null;
				}
			}
		};
	}
	
	//adds a card to the recentCards ArrayList
	public void addRecentCards(Card card){
		if (card instanceof SpecialCard){
			return;
		}
		if (this.recentCards.size() > 2) {
			this.recentCards.remove(0);
			this.recentCards.add(card);
		}
		else {
			this.recentCards.add(card);
		}
	}
	
	// add observer into observer list
	public void registerObserver(Observer o) {
		this.observers.add(o);
	}
	
	// remove an observer from observer list
	public void deregisterObserver(Observer o) {
		this.observers.remove(o);
	}
	
	// update each opbserver 
	private void notifyObservers(String event, Object obj) {
		for (Observer o : observers) {
			o.update(event, obj);
		}
	}
}
