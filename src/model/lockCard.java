package model;

import java.util.Random;

public class lockCard extends SpecialCard{
	public lockCard(){
		super();
	}
	
	@Override
	public void specialEffect(Grid grid){
		Random random = new Random();
		int lockLength = grid.getDifficulty();
		int randomX1 = random.nextInt(grid.getRows());
		int randomY1 = random.nextInt(grid.getColumns());
		Card card = grid.getCard(randomX1, randomY1);
		while (true) {
			if (!card.isFlipped()){
				//card.lock(lockLength);
				break;
			}
			randomX1 = random.nextInt(grid.getRows());
			randomY1 = random.nextInt(grid.getColumns());
			card = grid.getCard(randomX1, randomY1);
		}
	}
}
