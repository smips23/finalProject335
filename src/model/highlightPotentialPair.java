package model;

import java.util.Random;

public class highlightPotentialPair extends SpecialCard{
	public highlightPotentialPair(){
		super();
	}
	
	@Override
	public void specialEffect(Grid grid){
		int pair[][] = this.findRandomPair(grid);
		int highlightLength = 4 - grid.getDifficulty();
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
		//pair1.highlight(highlightLength);
		//pair2.highlight(highlightLength);
		//card1.highlight(highlightLength);
		//card2.highlight(highlightLength);
	}
}
