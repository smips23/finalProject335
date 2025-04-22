package model;

import java.util.Random;

public class flipRandom extends SpecialCard{
	public flipRandom(){
		super();
	}
	
	@Override
	public void specialEffect(Grid grid){
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
}
