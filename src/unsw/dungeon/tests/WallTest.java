package unsw.dungeon.tests;

import unsw.dungeon.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class WallTest {

	@Test
	void testWall() {
		Wall wall = new Wall(2,5);
		
		assertEquals(wall.canMove(new Entity(2,5), 1, 1), false, "Walls should not be able to be moved into");
	}

}
