package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GridTest {

	@Test
	void test() {
		Grid grid = new Grid(4, 4);
        assertEquals(4, grid.getRows());
        assertEquals(4, grid.getColumns());
        
        assertTrue(grid.isValidPosition(0, 0));
        assertTrue(grid.isValidPosition(3, 3));
        assertFalse(grid.isValidPosition(-1, 0));
        assertFalse(grid.isValidPosition(4, 0));
            
        // grid with different difficuties
        
        assertTrue(grid.createGrid(Grid.EASY));
        assertEquals(4, grid.getRows());
        assertEquals(4, grid.getColumns());
        
        Grid mediumGrid = new Grid(0, 0);
        assertTrue(mediumGrid.createGrid(Grid.MEDIUM));
        assertEquals(5, mediumGrid.getRows());
        assertEquals(5, mediumGrid.getColumns());
        
        Grid hardGrid = new Grid(0, 0); 
        assertTrue(hardGrid.createGrid(Grid.HARD));
        assertEquals(6, hardGrid.getRows());
        assertEquals(6, hardGrid.getColumns());
           
        
        Grid original = new Grid(3, 3);
        original.createGrid(Grid.EASY);
        Grid copy = original.copy();
        assertEquals(original.getRows(), copy.getRows());
        assertEquals(original.getColumns(), copy.getColumns());
        
        Card testCard = new Card();
        testCard.setValue(99); 
        grid.setGridCell(testCard, 2, 2);
        assertEquals(testCard, grid.getCard(2, 2));
        
        // Test iterator
        int count = 0;
        for (Card card : grid) {
        	assertNotNull(card);
            count++;
        }
        assertTrue(count > 0);
        
        
	}  

}
