package unsw.dungeon.tests;

import unsw.dungeon.*;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class MovementAwayTest {

	@Test
	void test() {
		Movement movement = new MovementAway();
		List<String> directions = new ArrayList<String>();
		
		movement.calculateMove(directions, 3, 3);
		
		assertEquals(directions.get(0), "left");
		assertEquals(directions.get(1), "down");
		assertEquals(directions.get(2), "up");
		assertEquals(directions.get(3), "right");
	}
	
	@Test
	void test2() {
		Movement movement = new MovementAway();
		List<String> directions = new ArrayList<String>();
		
		movement.calculateMove(directions, 2, -7);
		
		assertEquals(directions.get(0), "up");
		assertEquals(directions.get(1), "left");
		assertEquals(directions.get(2), "right");
		assertEquals(directions.get(3), "down");
	}
	
	@Test
	void test3() {
		Movement movement = new MovementAway();
		List<String> directions = new ArrayList<String>();
		
		movement.calculateMove(directions, -8, 0);
		
		assertEquals(directions.get(0), "right");
		assertEquals(directions.get(1), "down");
		assertEquals(directions.get(2), "up");
		assertEquals(directions.get(3), "left");
	}
	
	@Test
	void test4() {
		Movement movement = new MovementAway();
		List<String> directions = new ArrayList<String>();
		
		movement.calculateMove(directions, 0, 4);
		
		assertEquals(directions.get(0), "down");
		assertEquals(directions.get(1), "left");
		assertEquals(directions.get(2), "right");
		assertEquals(directions.get(3), "up");
	}

}
