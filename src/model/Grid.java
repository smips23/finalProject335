package finalProject335.src.model;

import java.util.List;

public class Grid {

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


}
