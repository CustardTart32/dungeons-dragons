package unsw.dungeon.tests;

import unsw.dungeon.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SwordTest {
	Dungeon dungeon = new Dungeon(10,10);
	
	@Test
	void testSword() {
		Sword sword = new Sword(5,5,dungeon);
		
		assertEquals(sword.use(), 4, "Sword durability should now be 4");
		assertEquals(sword.use(), 3, "Sword durability should now be 3");
		sword.onPickUp();
		
		assertNotEquals(sword.getX(), 5, "Sword should not be at x position 5");
		assertNotEquals(sword.getY(), 5, "Sword should not be at y position 5");
	}

}
