package specialCard;

import model.Card;
import model.Grid;
import model.SpecialCard;
import model.Swappable;

public class matrixSwap extends SpecialCard implements Swappable<Card>{
	public matrixSwap(){
		super();
	}

	@Override
	public void Swap(Grid grid) {
		int rowLength = grid.getRows();
		int i = 0;
		int j = grid.getColumns();
		Grid newGrid = new Grid(grid.getRows(),grid.getColumns());
		for (Card card : grid){
			if (j < 0){
				break;
			}
			if (i < rowLength) {
				newGrid.setGridCell(card, i, j);
				i++;
				continue;
			}
			if (i > rowLength){
				i = 0;
				j--;
				newGrid.setGridCell(card, i, j);
				continue;
			}
		}
		grid.updateGrid(newGrid);
	}
}