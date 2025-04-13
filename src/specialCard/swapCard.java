package specialCard;
import model.Card;
import model.Grid;
import model.SpecialCard;
import model.Swappable;
import java.util.Random;

public class swapCard extends SpecialCard implements Swappable<Card>{
	public swapCard(){
		super();
	}

	@Override
	public void Swap(Grid grid) {
		Random random = new Random();
		int randomX1 = random.nextInt(grid.getRows());
		int randomY1 = random.nextInt(grid.getColumns());
		int randomX2 = random.nextInt(grid.getRows());
		int randomY2 = random.nextInt(grid.getColumns());
		Card card1 = grid.getCard(randomX1, randomY1);
		Card card2 = grid.getCard(randomX2, randomY2);
		grid.setGridCell(card1, randomX1, randomY1);
		grid.setGridCell(card2, randomX2, randomY2);
		
	}
}
