package unsw.dungeon.tests;

import unsw.dungeon.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GoalObserverTest {

	@Test
	void testGoalObserver() {
		GoalObserver obs = new GoalObserver();
		
		obs.addSubject(new Treasure(5,2));
		obs.addSubject(new Treasure(4,4));
		obs.addSubject(new Treasure(2,1));
		
		assertEquals(obs.getNumber(), 3, "Three subjects should have been added");
		assertEquals(obs.getProgress(), 0, "None should be completed");
	}
	
	@Test
	void testUpdate() {
		GoalObserver obs = new GoalObserver();
		
		Subject t1 = new Treasure(5,2);
		Subject t2 = new Treasure(4,4);
		Subject t3 = new Treasure(2,1);
		
		obs.addSubject(t1);
		obs.addSubject(t2);
		obs.addSubject(t3);
		
		obs.update(t1);
		obs.update(t2);
		obs.update(t3);
		
		assertEquals(obs.getProgress(), 3, "All 3 subjects should now be true");
		assertEquals(obs.is_complete(), true, "Goal should now be completed");
	}

}
