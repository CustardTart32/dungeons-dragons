package unsw.dungeon.tests;

import unsw.dungeon.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class IndividualGoalTest {
	Observer obs = new GoalObserver();
	
	Subject t1 = new Enemy(null,5,2);
	Subject t2 = new Enemy(null,4,4);
	Subject t3 = new Enemy(null,2,1);
	
	@BeforeEach
	public void init() {
		obs.addSubject(t1);
		obs.addSubject(t2);
		obs.addSubject(t3);
	}
	
	
	@Test
	void InvGoalTest() {
		IndividualGoal goal = new IndividualGoal("enemies");
		goal.setObs(obs);
		
		assertEquals(goal.getType(), "enemies");
		assertEquals(goal.completed(), false, "Goal should not have been completed");
		
		obs.update(t1);
		obs.update(t2);
		
		assertEquals(goal.completed(), false, "Goal should not have been completed");
		
		obs.update(t3);
		assertEquals(goal.completed(), true, "Goal should now be completed");
	}
	
	@Test
	void GoalTestReverse() {
		IndividualGoal goal = new IndividualGoal("enemies");
		goal.setObs(obs);
		
		obs.update(t1);
		obs.update(t2);
		obs.update(t3);
		
		assertEquals(goal.completed(), true, "Goal should now be completed");
		
		obs.update(t1);
		
		assertEquals(goal.completed(), false, "Goal not be completed");
	}
}
