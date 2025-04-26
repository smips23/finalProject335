package model;

public class Card {
    
    protected boolean flipped;
    private boolean highlighted;
    protected boolean locked;
    protected boolean freeze;
    protected boolean thirdPair;
    private int value;

    // constructor
    public Card(){
        flipped = false;
        highlighted = false;
        locked = false;
        freeze = false; 
        thirdPair = false;
        value = -1;
    }

    // return if card is flipped
    public boolean isFlipped(){
        return flipped == true;
    }

    public void setFlip(boolean flip){
    	flipped = flip;
    }
    
    // alternate flipped orientation
    public void flip(Grid grid){
    	if (!locked) {
    		if (isFlipped()){
    			flipped = false;
    			System.out.println("card was flipped to back");
    		}else{
    			flipped = true;
    			System.out.println("card was flipped to face");
    		}
    	}
    	else{
    		if (!isFlipped()){
    			System.out.println("card is locked and cannot be flipped");
    		}
    	}
    }

    // returns if card is highlighted
    public boolean isHighlighted(){
        return highlighted;
    }

    // highlight card
    public void highlight(){
    	highlighted = true;
    	System.out.println("card was highlighted");
    }

    // unhighlight card
    public void unhighlight(){
    	highlighted = false;
    }
    
    // returns if card is locked
    public boolean isLocked(){
        return locked;
    }

    // lock card 
    public void lock(){
    	locked = true;
        System.out.println("card was locked");
    }

    // unlock card
    public void unlock(){
    	locked = false;
    }
    
 // returns if card is frozen
    public boolean isFrozen(){
        return freeze;
    }

    // lock card 
    public void freeze(){
    	freeze = true;
        System.out.println("card was frozen");
    }

    // unlock card
    public void unfreeze(){
    	freeze = false;
    }
    
    public boolean thirdPair(){
    	return thirdPair;
    }
    
    public void addThirdPair(){
    	thirdPair = true;
    }
    
    // sets the card's value
    public void setValue(int val){
        value = val;
    }

    // gets card's value
    public int getValue(){
        return value;
    }
}