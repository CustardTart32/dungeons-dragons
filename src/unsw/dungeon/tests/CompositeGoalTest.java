package unsw.dungeon.tests;



import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unsw.dungeon.*;

class CompositeGoalTest {
	IndividualGoal g1 = new IndividualGoal("enemies");
	IndividualGoal g2 = new IndividualGoal("exit");
	IndividualGoal g3 = new IndividualGoal("treasure");
	
	Observer obs1 = new GoalObserver();
	Subject enemy1 = new Enemy(null,2,5);
	Subject enemy2 = new Enemy(null,4,4);
	
	Observer obs2 = new GoalObserver();
	Subject exit = new Exit(5,7);
	
	Observer obs3 = new GoalObserver();
	Subject treasure1 = new Treasure(3,3);
	Subject treasure2 = new Treasure(4,4);
	
	@BeforeEach
	void init() {
		obs1.addSubject(enemy1);
		obs1.addSubject(enemy2);
		g1.setObs(obs1);
		
		obs2.addSubject(exit);
		g2.setObs(obs2);
		
		obs3.addSubject(treasure1);
		obs3.addSubject(treasure2);
		g3.setObs(obs3);
	}
	
	@Test
	void TestOR_A() {
		CompositeGoal comp1 = new CompositeGoal("OR");
		comp1.setGoalA(g1);
		comp1.setGoalB(g2);
		
		assertEquals(comp1.completed(), false);
		
		obs1.update(enemy1);
		
		assertEquals(comp1.completed(), false);
		
		obs1.update(enemy2);
		
		assertEquals(comp1.completed(), true);
	}
	
	@Test
	void TestOR_B() {
		CompositeGoal comp1 = new CompositeGoal("OR");
		comp1.setGoalA(g1);
		comp1.setGoalB(g2);
		
		assertEquals(comp1.completed(), false);
		
		obs2.update(exit);
		
		assertEquals(comp1.completed(), true);
	}
	
	@Test
	void TestAND() {
		CompositeGoal comp1 = new CompositeGoal("AND");
		comp1.setGoalA(g1);
		comp1.setGoalB(g2);
		
		assertEquals(comp1.completed(), false);
		
		obs1.update(enemy1);
		
		assertEquals(comp1.completed(), false);
		
		obs1.update(enemy2);
		
		assertEquals(comp1.completed(), false);
		
		obs2.update(exit);
		
		assertEquals(comp1.completed(), true);
	}
	
	@Test
	void TestWithComposite() {
		CompositeGoal comp1 = new CompositeGoal("AND");
		CompositeGoal comp2 = new CompositeGoal("OR");
		
		comp1.setGoalA(g1);
		comp1.setGoalB(comp2);
		comp2.setGoalA(g2);
		comp2.setGoalB(g3);
		
		assertEquals(comp1.completed(), false);
		
		
		obs1.update(enemy1);
		obs1.update(enemy2);
		
		assertEquals(comp1.completed(),false);
		
		obs2.update(exit);
		
		assertEquals(comp1.completed(),true);
	}
}
