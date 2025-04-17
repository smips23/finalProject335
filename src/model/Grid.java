package finalProject335.src.model;

import java.util.ArrayList;
import java.util.Collections;
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
        
        ArrayList<Card> cardPairs = generateCardPairs();
        populateGrid(cardPairs);  
        return true;
    }

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
}
