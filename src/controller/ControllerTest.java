package controller;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import view.UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ControllerTest {

	@Test
	void test() {
		UI gui = new UI();
		Controller c = new Controller(gui);
		
		ActionEvent e1 = new ActionEvent(c, 1, "standard");
		ActionEvent e2 = new ActionEvent(c, 2, "timed");
		ActionEvent e3 = new ActionEvent(c, 3, "lives");
		ActionEvent e4 = new ActionEvent(c, 4, "special");
		ActionEvent e5 = new ActionEvent(c, 5, "easy");
		ActionEvent e6 = new ActionEvent(c, 6, "normal");
		ActionEvent e7 = new ActionEvent(c, 7, "hard");
		ActionEvent e8 = new ActionEvent(c, 8, "back");
		
		c.actionPerformed(e1);
		assertEquals("standard", c.getMode());
		c.actionPerformed(e2);
		assertEquals("timed", c.getMode());
		c.actionPerformed(e3);
		assertEquals("lives", c.getMode());
		c.actionPerformed(e4);
		assertEquals("special", c.getMode());
		c.actionPerformed(e5);
		assertEquals("easy", c.getDifficulty());
		c.actionPerformed(e6);
		assertEquals("normal", c.getDifficulty());
		c.actionPerformed(e7);
		assertEquals("hard", c.getDifficulty());
		c.actionPerformed(e8);
		assertEquals(null, c.getMode());
		assertEquals(null, c.getDifficulty());
	}
}
