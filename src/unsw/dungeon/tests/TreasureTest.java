package unsw.dungeon.tests;

import unsw.dungeon.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TreasureTest {

	@Test
	void test() {
		Treasure t1 = new Treasure(2,3);
		
		assertEquals(t1.getX(),2);
		assertEquals(t1.getY(),3);
		
		t1.onPickUp();
		
		assertNotEquals(t1.getX(),2);
		assertNotEquals(t1.getY(),3);
	}
	
	@Test
	void testSubject() {
		Treasure t1 = new Treasure(2,3);
		
		GoalObserver obs = new GoalObserver();
		
		obs.addSubject(t1);
		
		t1.onPickUp();
		
		assertEquals(obs.is_complete(),false, "Should be false");
		
		t1.setObserver(obs);
		t1.onPickUp();
		
		assertEquals(obs.is_complete(), true, "Should be true");
	}

}
