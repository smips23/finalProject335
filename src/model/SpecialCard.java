package model;
import java.util.Random;

public class SpecialCard extends Card{
   
    public SpecialCard(){
        super();
    }
    
    public int[][] findRandomPair(Grid grid){
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
    
    public void specialEffect(Grid grid){}
}

