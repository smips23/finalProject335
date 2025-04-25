package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CardTest {

	@Test
	void test() {
		Card card = new Card();
		assertFalse(card.isFlipped());
		assertFalse(card.isHighlighted());
		assertFalse(card.isLocked());
		assertFalse(card.isFrozen());
		assertFalse(card.thirdPair());
		assertEquals(-1, card.getValue());
		
		card.setValue(5);
		assertEquals(5, card.getValue());
		
		// Test flip when not locked
		card.flip(null);
		assertTrue(card.isFlipped());
		card.flip(null);
		assertFalse(card.isFlipped());
		
		// highlighting
		
		card.highlight();
		assertTrue(card.isHighlighted());
		card.unhighlight();
		assertFalse(card.isHighlighted());
		
		// lock
		
		card.lock();
		assertTrue(card.isLocked());
		
		// flip
		
		card.flip(null);
		assertFalse(card.isFlipped()); 
		
		
		card.unlock();
		card.flip(null); 
		assertTrue(card.isFlipped());
		card.lock();
		card.flip(null); 
		assertTrue(card.isFlipped());
		
		// freeze
		
		card.freeze();
		assertTrue(card.isFrozen());
		card.unfreeze();
		assertFalse(card.isFrozen());
	}

}
