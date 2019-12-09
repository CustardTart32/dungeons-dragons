package unsw.dungeon.tests;

import unsw.dungeon.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ExitTest {

	@Test
	void testExit() {
		Exit exit = new Exit(2,4);
		
		assertEquals(exit.getEntered(), false, "The exit is initially set to false");
		assertEquals(exit.getX(),2);
		assertEquals(exit.getY(),4);
	}
	
	@Test
	void testExitWithoutObs() {
		Exit exit = new Exit(2,4);
		
		exit.hasLeft();
		
		assertEquals(exit.getEntered(), false, "The exit is initially set to false");
		
		exit.hasEntered();
		assertEquals(exit.getEntered(), false, "The exit should now be set to false");
		
		exit.hasEntered();
		assertEquals(exit.getEntered(), false, "The exit should now be set to false");
		
		exit.hasLeft();
		assertEquals(exit.getEntered(), false, "The exit should now be set to false");		
	}

	@Test
	void testExitWithObs() {
		Exit exit = new Exit(2,4);
		Observer obs = new GoalObserver();
		
		exit.setObserver(obs);
		obs.addSubject(exit);
		
		exit.hasLeft();
		
		assertEquals(exit.getEntered(), false, "The exit is initially set to false");
		
		exit.hasEntered();
		assertEquals(exit.getEntered(), true, "The exit should now be set to false");
		
		exit.hasEntered();
		assertEquals(exit.getEntered(), true, "The exit should now be set to false");
		
		exit.hasLeft();
		assertEquals(exit.getEntered(), false, "The exit should now be set to false");		
	}
}
