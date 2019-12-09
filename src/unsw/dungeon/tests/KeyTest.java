package unsw.dungeon.tests;

import unsw.dungeon.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class KeyTest {

	
	
	@Test
	void keyTest() {
		Key key = new Key(2,5,1,null);
		
		assertEquals(key.getX(),2);
		assertEquals(key.getY(),5);
		assertEquals(key.getId(),1);
		
//		key.onPickUp();
//		
//		assertNotEquals(key.getX(), 5, "Key should not be at x position 2");
//		assertNotEquals(key.getY(), 5, "Key should not be at y position 5");
	}

}
