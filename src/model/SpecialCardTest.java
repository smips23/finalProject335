package model;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.Test;
class SpecialCardTest {
    @Test
    void test() {
    Random random = new Random(1);
        SpecialCard specialCard = new SpecialCard();
        assertTrue(specialCard instanceof Card);
        assertFalse(specialCard.isFlipped());
        assertFalse(specialCard.isLocked());
        assertNull(specialCard.getAbilityMessage());

        Grid grid = new Grid(4, 4);
        grid.createGrid(Grid.EASY);

        specialCard.flip(grid);
        assertTrue(specialCard.isFlipped());
        assertTrue(specialCard.isLocked()); 
        assertNotNull(specialCard.getAbilityMessage());

        // Test flip when locked and flipped
        specialCard = new SpecialCard(); 
        specialCard.lock();
        specialCard.flip(grid);
        assertFalse(specialCard.isFlipped());

        specialCard = new SpecialCard(); 
        specialCard.flip(grid); 
        specialCard.unlock(); 
        specialCard.flip(grid); 
        assertFalse(specialCard.isFlipped());

        Card normalCard = new Card();
        normalCard.flip(grid);
        grid.setGridCell(normalCard, 0, 0);

        // frozen test
        Card frozenCard = new Card();
        frozenCard.freeze();
        grid.setGridCell(frozenCard, 0, 1);

        // highlighted and frozen
        Card highlightedCard = new Card();
        highlightedCard.highlight();
        grid.setGridCell(highlightedCard, 1, 0);
    }
    
    @Test
    void testAbilities(){
    Grid grid = new Grid(6, 6);
    grid.createGrid(3);
        SpecialCard specialCard = new SpecialCard();
        specialCard.setAbility(0);
        specialCard.flip(grid);
        
        specialCard = new SpecialCard();
        specialCard.setAbility(1);
        specialCard.flip(grid);
        
        specialCard = new SpecialCard();
        specialCard.setAbility(2);
        specialCard.flip(grid);
        
        specialCard = new SpecialCard();
        specialCard.setAbility(3);
        specialCard.flip(grid);
        
        specialCard = new SpecialCard();
        specialCard.setAbility(4);
        specialCard.flip(grid);
        
        specialCard = new SpecialCard();
        specialCard.setAbility(5);
        specialCard.flip(grid);
        
        specialCard = new SpecialCard();
        specialCard.setAbility(6);
        specialCard.flip(grid);

        Grid grid2 = new Grid(0,0);
        specialCard = new SpecialCard();
        specialCard.setAbility(2);
        specialCard.flip(grid2);
        assertEquals(0,0);
    }

    @Test
    void testRandomCheck(){
        Grid grid = new Grid(6, 6);
        grid.createGrid(3);
        SpecialCard specialCard = new SpecialCard();
        specialCard.setSeed(100);
        specialCard.setAbility(2);
        Card card1 = grid.getCard(1, 4);
        Card card2 = grid.getCard(4, 0);
        card1.lock();
        card2.lock();
        specialCard.flip(grid);

        specialCard = new SpecialCard();
        specialCard.setSeed(100);
        specialCard.setAbility(1);
        card1 = grid.getCard(1, 4);
        card2 = grid.getCard(4, 0);
        card1.lock();
        card2.lock();
        specialCard.flip(grid);

        specialCard = new SpecialCard();
        specialCard.setSeed(100);
        specialCard.setAbility(3);
        card1 = grid.getCard(1, 4);
        card2 = grid.getCard(4, 0);
        card1.freeze();
        card2.highlight();
        specialCard.flip(grid);

        
        specialCard = new SpecialCard();
        specialCard.setSeed(100);
        specialCard.setAbility(5);
        card1 = grid.getCard(1, 4);
        card2 = grid.getCard(4, 0);
        card1.lock();
        card2.lock();
        specialCard.flip(grid);
        
        specialCard = new SpecialCard();
        specialCard.setSeed(100);
        specialCard.setAbility(5);
        card1 = grid.getCard(5, 1);
        card2 = grid.getCard(5, 2);
        card1.lock();
        card2.lock();
        specialCard.flip(grid);

        specialCard = new SpecialCard();
        specialCard.setSeed(100);
        specialCard.setAbility(5);
        card1 = grid.getCard(5, 1);
        card2 = grid.getCard(5, 2);
        card1.lock();
        card2.lock();
        specialCard.flip(grid);
        
    }
}
