package model;

import java.util.Iterator;
import java.util.List;

public class Grid implements Iterable<Card>{

    private Card[][] cards;
    private int rows;
    private int columns;
    private int difficulty;

    public static final int EASY = 1;
    public static final int MEDIUM = 2;
    public static final int HARD = 3;

    public Grid(int rows, int columns){
        this.rows = rows;
        this.columns = columns;
        this.cards = new Card[rows][columns];
   
    }
    // getters for all instance variables

    public int getRows(){
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public boolean isValidPosition(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < columns;
    }
    
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
        
        List<Card> cardPairs = generateCardPairs();
        // Place cards on grid
        //populateGrid(cardPairs);  
        return true;
    }

    private void populateGrid() {
        // TODO Auto-generated method stub
        //return null;
    }

    private List<Card> generateCardPairs() {
        // TODO Auto-generated method stub
        return null;
    }
    
    public void updateGrid(Grid grid){
    	this.cards = grid.cards;
    }
    
    public void setGridCell(Card card, int row, int column){
    	if (row > this.rows || column > this.columns){
    	}
    	else{
    		cards[row][column] = card;
    	}
    }
    
    public Grid copy(){
    	Grid grid = new Grid(this.rows, this.columns);
    	grid.cards = this.cards.clone();
    	return grid;
    }
    
	@Override
	public Iterator<Card> iterator() {
		return new Iterator<Card>(){
			private int columnIndex = 0;
			private int rowIndex = 0;
			@Override
			public boolean hasNext() {
				return cards[rowIndex].length > columnIndex + 1 || cards.length > rowIndex;
			}
			@Override
			public Card next() {
				if (cards[rowIndex].length > columnIndex + 1){
					return cards[rowIndex][columnIndex += 1];
					}
				else {
					return cards[rowIndex+1][columnIndex = 0];
				}
			}
		};
	}
}
