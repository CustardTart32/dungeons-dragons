package unsw.dungeon.tests;

import unsw.dungeon.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PotionTest {
	
	Dungeon dungeon = new Dungeon(10,10);
	
	@Test
	void test() {
		Potion potion = new Potion(2,5,dungeon);
		
		assertEquals(potion.wearOff(), 4, "Potion timer should now be 4");
		
		//potion.onPickUp();
		
//		assertNotEquals(potion.getX(), 5, "Potion should not be at x position 5");
//		assertNotEquals(potion.getY(), 5, "Potion should not be at y position 5");
	}
}
