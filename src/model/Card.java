package model;

public class Card {
    
    private boolean flipped;
    private boolean highlighted;
    private boolean locked;
    private int value;
    private int highlightLength;
    private int lockedLength;

    // constructor
    public Card(){
        flipped = false;
        highlighted = false;
        locked = false;
        value = -1;
        highlightLength = 0;
        lockedLength = 0;
    }

    // return if card is flipped
    public boolean isFlipped(){
        return flipped == true;
    }

    // alternate flipped orientation
    public void flip(){
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

    //returns highlight length
    public int getHighlightLength(){
    	return highlightLength;
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

    // returns lock length
    public int getLockedLength(){
    	return lockedLength;
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