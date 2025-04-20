package model;

public class highlightFlipped extends SpecialCard{
	public highlightFlipped(){
		super();
	}
	
	@Override
	public void specialEffect(Grid grid){
		for (Card card : grid){
			if (card.isFlipped()){
				card.highlight(2);
			}
		}
	}
}
