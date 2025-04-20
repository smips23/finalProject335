package model;
import java.util.Random;

public class swapCard extends SpecialCard{
	public swapCard(){
		super();
	}

	@Override
	public void specialEffect(Grid grid) {
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
			if (card1.isFlipped()) {
				card1 = grid.getCard(randomX1, randomY1);
			}
			if (card2.isFlipped()) {
				card2 = grid.getCard(randomX2, randomY2);
			}
			if (!card1.isFlipped() && !card2.isFlipped()){
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
}
