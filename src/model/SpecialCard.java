package model;
import java.util.Random;

public class SpecialCard extends Card{
   
    public SpecialCard(){
        super();
    }
    
    private int[][] findRandomPair(Grid grid){
    	int[][] result = new int[2][2];
    	int i = 0;
    	if (grid.getRemainingPairs() > 0) {
    		Random random = new Random();
			int randomX = random.nextInt(grid.getRows());
			int randomY = random.nextInt(grid.getColumns());
			Card card = grid.getCard(randomX, randomY);
			result[0][0] = randomX;
			result[0][1] = randomY;
			while (card.isFlipped()){
				randomX = random.nextInt(grid.getRows());
				randomY = random.nextInt(grid.getColumns());
				card = grid.getCard(randomX, randomY);
			}
			for (Card otherCard: grid){
				if (card == otherCard){
					result[1][0] = i / grid.getRows();
					result[1][1] = i % grid.getRows();
					return result;
				}
				i++;
			}
    	}
		return result;
    }
    
    //@Override
    public void flip(Grid grid){
    	Random random = new Random();
    	int randomNumber = random.nextInt(0, 6);
    	if (randomNumber == 0){
    		addLife();
    	}
    	if (randomNumber == 1){
    		flipRandom(grid);
    	}
    	if (randomNumber == 2){
    		swapCards(grid);
    	}
    	if (randomNumber == 3){
    		lockCard(grid);
    	}
    	if (randomNumber == 4){
    		highlightFlipped(grid);
    	}
    	if (randomNumber == 5){
    		highlightPotentialPair(grid);
    	}
    	if (randomNumber == 6){
    		matrixSwap(grid);
    	}
		this.locked = true;
    }
    
    private void addLife(){
    	//lives++;
    }
    
    private void flipRandom(Grid grid){
		Random random = new Random();
		int randomX1 = random.nextInt(grid.getRows());
		int randomY1 = random.nextInt(grid.getColumns());
		int randomX2 = random.nextInt(grid.getRows());
		int randomY2 = random.nextInt(grid.getColumns());
		Card card1 = grid.getCard(randomX1, randomY1);
		Card card2 = grid.getCard(randomX2, randomY2);
		card1.flip();
		card2.flip();
	}
    
    private void swapCards(Grid grid) {
		if (grid.getRemainingPairs() == 0){
			return;
		}
		Random random = new Random();
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
			if (!card1.isLocked() && !card2.isFlipped()){
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
	}

    private void lockCard(Grid grid){
		Random random = new Random();
		int randomX1 = random.nextInt(grid.getRows());
		int randomY1 = random.nextInt(grid.getColumns());
		Card card = grid.getCard(randomX1, randomY1);
		while (true) {
			//switch to freeze or smth
			/*if (!card.isLocked()){
				card.lock();
				break;
				*/
			//}
			randomX1 = random.nextInt(grid.getRows());
			randomY1 = random.nextInt(grid.getColumns());
			card = grid.getCard(randomX1, randomY1);
			break;
    	}
	}
    
    private void highlightFlipped(Grid grid){
		for (Card card : grid){
			if (card.isFlipped()){
				//card.highlight(2);
			}
		}
	}
    
    private void highlightPotentialPair(Grid grid){
		int pair[][] = this.findRandomPair(grid);
		if (pair[0][0] == 0 && pair[0][1] == 0 && pair[1][0] == 0 && pair[1][1] == 0){
			return;
		}
		Card pair1 = grid.getCard(pair[0][0], pair[0][1]);
		Card pair2 = grid.getCard(pair[1][0], pair[1][1]);
		Random random = new Random();
		int randomX1 = random.nextInt(grid.getRows());
		int randomY1 = random.nextInt(grid.getColumns());
		Card card1 = grid.getCard(randomX1, randomY1);
		int randomX2 = random.nextInt(grid.getRows());
		int randomY2 = random.nextInt(grid.getColumns());
		Card card2 = grid.getCard(randomX2, randomY2);
		pair1.highlight();
		pair2.highlight();
		card1.highlight();
		card2.highlight();
	}
    
    private void matrixSwap(Grid grid) {
		int rowLength = grid.getRows();
		int i = 0;
		int j = grid.getColumns();
		Grid newGrid = new Grid(grid.getRows(),grid.getColumns());
		for (Card card : grid){
			if (j < 0){
				break;
			}
			if (i < rowLength) {
				newGrid.setGridCell(card, i, j);
				i++;
				continue;
			}
			if (i > rowLength){
				i = 0;
				j--;
				newGrid.setGridCell(card, i, j);
				continue;
			}
		}
		grid.updateGrid(newGrid);
	}
}

