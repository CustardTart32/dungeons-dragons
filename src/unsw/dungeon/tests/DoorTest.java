package unsw.dungeon.tests;

import unsw.dungeon.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DoorTest {

	@Test
	void checkDoor() {
		Door door = new Door(5,2,1);
		
		assertEquals(door.getState(), false, "Door state should be false initially");
		assertEquals(door.getId(), 1, "Door id should be 1");
		assertEquals(door.getX(), 5, "Door x position should be 5");
		assertEquals(door.getY(), 2, "Door position should be 2");
	}
	
	@Test
	void checkDoorUnlock() {
		Door door = new Door(5,2,1);
		
		assertEquals(door.canMove(new Entity(2,6), 2, 6),false,"Entity should not be able to move through door");
		door.unlockDoor();
		assertEquals(door.getState(), true, "Door state should now be true");
		assertEquals(door.canMove(new Entity(2,6), 2, 6),true,"Entity should now be able to move through door");
	}
}
