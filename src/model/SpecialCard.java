package model;
import java.util.Random;

public class SpecialCard extends Card{
   
	private int abilityNum;
	Random random;
	private String abilityMessage;
	
    public SpecialCard(){
        super();
    	random = new Random();
    	abilityNum = random.nextInt(1,6);
    }
    
    public void setAbility(int num){
    	if (0 <= num && num <= 6){
    		abilityNum = num;
    	}
    }
    
    // returns ability message
    public String getAbilityMessage() {
    	return abilityMessage;
    }
    
    //finds a random pair that is not yet flipped
    private int[][] findRandomPair(Grid grid){
    	int[][] result = new int[2][2];
    	result[0][0] = -1;
    	result[0][1] = -1;
    	result[1][0] = -1;
    	result[1][1] = -1;
    	int i = 0;
    	if (grid.getRemainingPairs() > 0) {
    		Random random = new Random();
			int randomX = random.nextInt(grid.getRows());
			int randomY = random.nextInt(grid.getColumns());
			Card card = grid.getCard(randomX, randomY);
			result[0][0] = randomX;
			result[0][1] = randomY;
			while (card.isLocked()){
				randomX = random.nextInt(grid.getRows());
				randomY = random.nextInt(grid.getColumns());
				card = grid.getCard(randomX, randomY);
			}
			for (Card otherCard: grid){
				if (card.getValue() == otherCard.getValue()){
					result[1][0] = i / grid.getRows();
					result[1][1] = i % grid.getRows();
					if (result[1][0] == result[0][0] && result[1][1] == result[0][1]){
						i++;
						continue;
					}
					return result;
				}
				i++;
			}
    	}
		return result;
    }
    
 // Flip and activate the special card's ability
    @Override
    public void flip(Grid grid) {
    	if (!(isLocked())) {
    		if (isFlipped()){
    			flipped = false;
    			System.out.println("Special card was flipped to back");
    		}else{
    			flipped = true;
    	    	doAbility(grid);
    			System.out.println("Special Card was flipped to face");
    		}
    	}
    	else{
    		if (!(isFlipped())){
    			System.out.println("Special card is locked and cannot be flipped");
    		}
    	}
    }
    
    // does an ability based on the abilityNum
    private void doAbility(Grid grid){
    	abilityNum = 5;
    	if (abilityNum == 0){
    		addLife();
    		System.out.println("one extra life has been granted");
    		abilityMessage = "*SPECIAL CARD* one extra life has been granted";
    	}
    	if (abilityNum == 1){
    		flipRandom(grid);
    		System.out.println("random two cards have been flipped");
    		abilityMessage = "*SPECIAL CARD* two random cards have been flipped";
    	}
    	if (abilityNum == 2){
    		swapCards(grid);
    		System.out.println("2 cards have been swapped");
    		abilityMessage = "*SPECIAL CARD* 2 cards hafve been swapped";
    	}
    	if (abilityNum == 3){
    		freeze(grid);
    		System.out.println("one card has been frozen");
    		abilityMessage = "*SPECIAL CARD* one card has been frozen";
    	}
    	if (abilityNum == 4){
    		highlightFlipped(grid);
    		System.out.println("highlight flipped cards");
    		abilityMessage = "*SPECIAL CARD* highlight flipped cards";
    	}
    	if (abilityNum == 5){
    		highlightPotentialPair(grid);
    		System.out.println("potential pairs highlighted");
    		abilityMessage = "*SPECIAL CARD* potential pairs highlighted";
    	}
    	if (abilityNum == 6){
    		matrixSwap(grid);
    		System.out.println("grid has been rotated");
    		abilityMessage = "*SPECIAL CARD* grid has been rotated";
    	}
    	this.locked = true;
    	this.setFlip(true);
    }
    
    private void addLife(){
    	//lives++;
    }
    
    //flips 2 random cards to show the player
    private void flipRandom(Grid grid){
		int randomX1 = random.nextInt(grid.getRows());
		int randomY1 = random.nextInt(grid.getColumns());
		int randomX2 = random.nextInt(grid.getRows());
		int randomY2 = random.nextInt(grid.getColumns());
		Card card1 = grid.getCard(randomX1, randomY1);
		Card card2 = grid.getCard(randomX2, randomY2);
		while (true) {
			if (card1.isLocked()) {
				card1 = grid.getCard(randomX1, randomY1);
			}
			if (card2.isLocked()) {
				card2 = grid.getCard(randomX2, randomY2);
			}
			if (!card1.isLocked() && !card2.isLocked()){
				if (card1 != card2) {
					break;
				}
			}
			randomX1 = random.nextInt(grid.getRows());
			randomY1 = random.nextInt(grid.getColumns());
			randomX2 = random.nextInt(grid.getRows());
			randomY2 = random.nextInt(grid.getColumns());
		}
		card1.flip(grid);
		card2.flip(grid);
	}
    
    //swaps 2 random unflipped cards
    private void swapCards(Grid grid) {
		if (grid.getRemainingPairs() == 0){
			return;
		}
		int randomX1 = random.nextInt(grid.getRows());
		int randomY1 = random.nextInt(grid.getColumns());
		int randomX2 = random.nextInt(grid.getRows());
		int randomY2 = random.nextInt(grid.getColumns());
		Card card1 = grid.getCard(randomX1, randomY1);
		Card card2 = grid.getCard(randomX2, randomY2);
		while (true) {
			if (card1.isLocked()) {
				card1 = grid.getCard(randomX1, randomY1);
			}
			if (card2.isLocked()) {
				card2 = grid.getCard(randomX2, randomY2);
			}
			if (!card1.isLocked() && !card2.isLocked() || (card1 == null || card2 == null)){
				if (card1 != card2) {
					break;
				}
			}
			randomX1 = random.nextInt(grid.getRows());
			randomY1 = random.nextInt(grid.getColumns());
			randomX2 = random.nextInt(grid.getRows());
			randomY2 = random.nextInt(grid.getColumns());
		}
		grid.setGridCell(card2, randomX1, randomY1);
		grid.setGridCell(card1, randomX2, randomY2);
		card1.highlight();
		card2.highlight();
	}

    //freezes the card, making it non-interactable
    private void freeze(Grid grid){
		int randomX1 = random.nextInt(grid.getRows());
		int randomY1 = random.nextInt(grid.getColumns());
		Card card = grid.getCard(randomX1, randomY1);
		while (true) {
			if (!card.isFrozen()){
				if (!card.isHighlighted()){
					card.highlight();
				}
				card.freeze();
				break;
			}
			randomX1 = random.nextInt(grid.getRows());
			randomY1 = random.nextInt(grid.getColumns());
			card = grid.getCard(randomX1, randomY1);
    	}
	}
    
    //highlight cards that were previously flipped
    private void highlightFlipped(Grid grid){
		for (Card card : grid.recentCards){
			if (!card.isLocked() && !card.isHighlighted()) {
				card.highlight();
			}
		}
	}
    
    //highlights a random pair and 2 random cards that are unflipped
    private void highlightPotentialPair(Grid grid){
		int pair[][] = this.findRandomPair(grid);
		if ((pair[0][0] == -1 && pair[0][1] == -1) && (pair[1][0] == -1 && pair[1][1] == -1)){
			return;
		}
		Card pair1 = grid.getCard(pair[0][0], pair[0][1]);
		Card pair2 = grid.getCard(pair[1][0], pair[1][1]);
		int randomX1 = random.nextInt(grid.getRows());
		int randomY1 = random.nextInt(grid.getColumns());
		Card card1 = grid.getCard(randomX1, randomY1);
		int randomX2 = random.nextInt(grid.getRows());
		int randomY2 = random.nextInt(grid.getColumns());
		Card card2 = grid.getCard(randomX2, randomY2);
		while (true) {
			if (card1.isLocked()) {
				card1 = grid.getCard(randomX1, randomY1);
			}
			if (card2.isLocked()) {
				card2 = grid.getCard(randomX2, randomY2);
			}
			if (!card1.isLocked() && !card2.isLocked()){
				if (card1 != card2) {
					break;
				}
			}
			randomX1 = random.nextInt(grid.getRows());
			randomY1 = random.nextInt(grid.getColumns());
			randomX2 = random.nextInt(grid.getRows());
			randomY2 = random.nextInt(grid.getColumns());
		}
		pair1.highlight();
		pair2.highlight();
		card1.highlight();
		card2.highlight();
	}
    
    //rotates the matrix clockwise 90 degrees once 
    private void matrixSwap(Grid grid) {
		int rowLength = grid.getRows() - 1;
		int i = 0;
		int j = grid.getColumns() - 1;
		Grid newGrid = new Grid(grid.getRows(),grid.getColumns());
		for (Card card : grid){
			System.out.println(i + " " + j);
			if (j < 0){
				break;
			}
			if (i <= rowLength) {
				newGrid.setGridCell(card, i, j);
				i++;
				continue;
			}
			if (i > rowLength){
				i = 0;
				j--;
				newGrid.setGridCell(card, i, j);
				i++;
				continue;
			}
		}
		grid.updateGrid(newGrid);
	}
}

