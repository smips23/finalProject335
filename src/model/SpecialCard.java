package model;
import java.util.Random;

public class SpecialCard extends Card{
   
	private int abilityNum;
	
    public SpecialCard(){
        super();
        abilityNum = 0;
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
    
    
    // Flip and activate the special card's ability
    @Override
    public void flip(Grid grid) {
    	if (!(isLocked())) {
    		if (isFlipped()){
    			flipped = false;
    			System.out.println("Special card was flipped to back");
    			Random random = new Random();
    	    	abilityNum = random.nextInt(5);
    	    	doAbility(grid);
    		}else{
    			flipped = true;
    			System.out.println("Special Card was flipped to face");
    		}
    	}
    	else{
    		if (!(isFlipped())){
    			System.out.println("Special card is locked and cannot be flipped");
    		}
    	}
    }
    
    
    // choose and call the correct ability
    private void doAbility(Grid grid) {
    	if(abilityNum == 0) {
    		freeze(grid);
    	}else if (abilityNum == 1) {
    		swapTwo(grid);
    	}else if (abilityNum == 2) {
    		flipMany(grid);
    	}else if (abilityNum == 3) {
    		highlightPotentialPairs(grid);
    	}else if (abilityNum == 4) {
    		becomeRandomThirdPair(grid);
    	}
    }
    
    
    // freeze a number of cards for a turn
    private void freeze(Grid grid) {
    	
    }
    
    
    // swap two random cards
    private void swapTwo(Grid grid) {
    	
    }
    
    
    // flip a random number of cards for a turn
    private void flipMany(Grid grid) {
    	
    }
    
    
    // highlights two potential pairs plus some random cards to throw off
    private void highlightPotentialPairs(Grid grid) {
    	
    }
    
    
    // specialCard becomes the same value as another pair. Now must match three cards. Implement last.
    private void becomeRandomThirdPair(Grid grid) {
    	
    }
    
    
}

