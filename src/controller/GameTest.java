package controller;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.event.ActionEvent;

import org.junit.jupiter.api.Test;

import model.SpecialCard;

class GameTest {
	
	@Test
	void testGame() {
		Game g1 = new Game("standard", "easy");
		Game g2 = new Game("timed", "hard");
		Game g3 = new Game("lives", "normal");
		Game g4 = new Game("crazy", "easy");
		
		assertFalse(g1.getTimedMode());
		assertFalse(g1.getLivesMode());
		
		assertTrue(g2.getTimedMode());
		assertFalse(g2.getLivesMode());
		
		assertFalse(g3.getTimedMode());
		assertTrue(g3.getLivesMode());
		
		assertTrue(g4.getTimedMode());
		assertTrue(g4.getLivesMode());
	}
	
	@Test
	void testActionEvents() {
		Game g1 = new Game("standard", "easy");
		Game g2 = new Game("timed", "hard");
		Game g3 = new Game("lives", "normal");
		Game g4 = new Game("crazy", "easy");
		
		ActionEvent e1 = new ActionEvent(g1, 1, "back_to_menu");
		ActionEvent e18 = new ActionEvent(g4, 1, "back_to_menu");
		ActionEvent e2 = new ActionEvent(g4, 2, "0,0");
		ActionEvent e3 = new ActionEvent(g4, 3, "0,1");
		ActionEvent e4 = new ActionEvent(g4, 4, "0,2");
		ActionEvent e5 = new ActionEvent(g4, 5, "0,3");
		ActionEvent e6 = new ActionEvent(g4, 6, "1,0");
		ActionEvent e7 = new ActionEvent(g4, 7, "1,1");
		ActionEvent e8 = new ActionEvent(g4, 8, "1,2");
		ActionEvent e9 = new ActionEvent(g4, 9, "1,3");
		ActionEvent e10 = new ActionEvent(g4, 10, "2,0");
		ActionEvent e11 = new ActionEvent(g4, 11, "2,1");
		ActionEvent e12 = new ActionEvent(g4, 12, "2,2");
		ActionEvent e13 = new ActionEvent(g4, 13, "2,3");
		ActionEvent e14 = new ActionEvent(g4, 14, "3,0");
		ActionEvent e15 = new ActionEvent(g4, 15, "3,1");
		ActionEvent e16 = new ActionEvent(g4, 16, "3,2");
		ActionEvent e17 = new ActionEvent(g4, 17, "3,3");
		
		g2.actionPerformed(e1);
		
		g4.actionPerformed(e2);
		g4.actionPerformed(e3);
		g4.actionPerformed(e2);
		g4.actionPerformed(e4);
		g4.actionPerformed(e2);
		g4.actionPerformed(e5);
		g4.actionPerformed(e2);
		g4.actionPerformed(e6);
		g4.actionPerformed(e2);
		g4.actionPerformed(e7);
		g4.actionPerformed(e2);
		g4.actionPerformed(e8);
		g4.actionPerformed(e2);
		g4.actionPerformed(e9);
		g4.actionPerformed(e2);
		g4.actionPerformed(e10);
		g4.actionPerformed(e2);
		g4.actionPerformed(e11);
		g4.actionPerformed(e2);
		g4.actionPerformed(e12);
		g4.actionPerformed(e2);
		g4.actionPerformed(e13);
		g4.actionPerformed(e2);
		g4.actionPerformed(e14);
		g4.actionPerformed(e2);
		g4.actionPerformed(e15);
		g4.actionPerformed(e2);
		g4.actionPerformed(e16);
		g4.actionPerformed(e2);
		g4.actionPerformed(e17);
	}
	
	@Test
	void testActionEvents2() {
		Game g1 = new Game("standard", "easy");
		Game g2 = new Game("timed", "hard");
		Game g3 = new Game("lives", "normal");
		Game g4 = new Game("crazy", "easy");
		
		ActionEvent e1 = new ActionEvent(g1, 1, "back_to_menu");
		ActionEvent e18 = new ActionEvent(g3, 1, "back_to_menu");
		ActionEvent e2 = new ActionEvent(g3, 2, "0,0");
		ActionEvent e3 = new ActionEvent(g3, 3, "0,1");
		ActionEvent e4 = new ActionEvent(g3, 4, "0,2");
		ActionEvent e5 = new ActionEvent(g3, 5, "0,3");
		ActionEvent e6 = new ActionEvent(g3, 6, "1,0");
		ActionEvent e7 = new ActionEvent(g3, 7, "1,1");
		ActionEvent e8 = new ActionEvent(g3, 8, "1,2");
		ActionEvent e9 = new ActionEvent(g3, 9, "1,3");
		ActionEvent e10 = new ActionEvent(g3, 10, "2,0");
		ActionEvent e11 = new ActionEvent(g3, 11, "2,1");
		ActionEvent e12 = new ActionEvent(g3, 12, "2,2");
		ActionEvent e13 = new ActionEvent(g3, 13, "2,3");
		ActionEvent e14 = new ActionEvent(g3, 14, "3,0");
		ActionEvent e15 = new ActionEvent(g3, 15, "3,1");
		ActionEvent e16 = new ActionEvent(g3, 16, "3,2");
		ActionEvent e17 = new ActionEvent(g3, 17, "3,3");
		
		g2.actionPerformed(e1);
		
		g3.setSeen();
		g3.actionPerformed(e2);
		g3.actionPerformed(e3);
		g3.actionPerformed(e2);
		g3.actionPerformed(e4);
		g3.actionPerformed(e2);
		g3.actionPerformed(e5);
		g3.actionPerformed(e2);
		g3.actionPerformed(e6);
		g3.actionPerformed(e2);
		g3.actionPerformed(e7);
		g3.actionPerformed(e2);
		g3.actionPerformed(e8);
		g3.actionPerformed(e2);
		g3.actionPerformed(e9);
		g3.actionPerformed(e2);
		g3.actionPerformed(e10);
		g3.actionPerformed(e2);
		g3.actionPerformed(e11);
		g3.actionPerformed(e2);
		g3.actionPerformed(e12);
		g3.actionPerformed(e2);
		g3.actionPerformed(e13);
		g3.actionPerformed(e2);
		g3.actionPerformed(e14);
		g3.actionPerformed(e2);
		g3.actionPerformed(e15);
		g3.actionPerformed(e2);
		g3.actionPerformed(e16);
		g3.actionPerformed(e2);
		g3.actionPerformed(e17);
	}
	
	@Test
	void testLosses() {
		Game g1 = new Game("standard", "easy");
		Game g2 = new Game("timed", "hard");
		Game g3 = new Game("lives", "normal");
		Game g4 = new Game("crazy", "easy");
		
		ActionEvent e2 = new ActionEvent(g2, 2, "0,0");
		ActionEvent e3 = new ActionEvent(g2, 3, "0,1");
		
		g4.actionPerformed(e2);
		g4.actionPerformed(e3);
		
		g4.endLives();	
		g4.setSeen();
		
		g4.actionPerformed(e2);
		g4.actionPerformed(e3);	
	}
	
	@Test
	void testLosses2() {
		Game g1 = new Game("standard", "easy");
		Game g2 = new Game("timed", "hard");
		Game g3 = new Game("lives", "normal");
		Game g4 = new Game("crazy", "easy");
		
		ActionEvent e2 = new ActionEvent(g3, 2, "0,0");
		ActionEvent e3 = new ActionEvent(g3, 3, "0,1");
		
		g3.setSeen();
		
		g3.actionPerformed(e2);
		g3.actionPerformed(e3);
		
		g3.endLives();
		g3.forceGameOver();
	}
	
	@Test
	void testWins() {
		Game g1 = new Game("standard", "easy");
		Game g2 = new Game("timed", "hard");
		Game g3 = new Game("lives", "normal");
		Game g4 = new Game("crazy", "easy");
		
		g1.setWin();
		g1.forceGameOver();
		g4.setWin();
		g4.forceGameOver();
	}
	
	@Test
	void testSpecialCard() {
		Game g1 = new Game("standard", "easy");
		Game g2 = new Game("timed", "hard");
		Game g3 = new Game("lives", "normal");
		Game g4 = new Game("crazy", "easy");
		
		SpecialCard c = new SpecialCard();
		g4.forceSpecialCard(c);
	}
	
	@Test
	void testObservers() {
		Game g1 = new Game("standard", "easy");
		Game g2 = new Game("timed", "hard");
		Game g3 = new Game("lives", "normal");
		Game g4 = new Game("crazy", "easy");
		
		g4.update("card_flipped", g3);
		g4.update("pair_found", g3);
		g4.update("bad_update", g3);
	}
}
