package unsw.dungeon.tests;

import unsw.dungeon.*;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class MovementToTest {

	@Test
	void testMovement1() {
		MovementTo movement = new MovementTo();
		List<String> directions = new ArrayList<String> ();
		
		movement.calculateMove(directions, 5, 3);
		
		assertEquals(directions.get(0),"right");
		assertEquals(directions.get(1),"up");
		assertEquals(directions.get(2),"down");
		assertEquals(directions.get(3),"left");
	}
	
	@Test
	void testMovement2() {
		MovementTo movement = new MovementTo();
		List<String> directions = new ArrayList<String> ();
		
		movement.calculateMove(directions, -5, -7);
		
		assertEquals(directions.get(0),"down");
		assertEquals(directions.get(1),"left");
		assertEquals(directions.get(2),"right");
		assertEquals(directions.get(3),"up");
	}

	@Test
	void testMovement3() {
		MovementTo movement = new MovementTo();
		List<String> directions = new ArrayList<String> ();
		
		movement.calculateMove(directions, 3, -3);
		
		assertEquals(directions.get(0),"right");
		assertEquals(directions.get(1),"down");
		assertEquals(directions.get(2),"up");
		assertEquals(directions.get(3),"left");
	}
	
	@Test
	void testMovement4() {
		MovementTo movement = new MovementTo();
		List<String> directions = new ArrayList<String> ();
		
		movement.calculateMove(directions, -2, 5);
		
		assertEquals(directions.get(0),"up");
		assertEquals(directions.get(1),"left");
		assertEquals(directions.get(2),"right");
		assertEquals(directions.get(3),"down");
	}
	
	@Test
	void testMovement5() {
		MovementTo movement = new MovementTo();
		List<String> directions = new ArrayList<String> ();
		
		movement.calculateMove(directions, -8, 0);
		
		assertEquals(directions.get(0),"left");
		assertEquals(directions.get(1),"up");
		assertEquals(directions.get(2),"down");
		assertEquals(directions.get(3),"right");
	}
}
