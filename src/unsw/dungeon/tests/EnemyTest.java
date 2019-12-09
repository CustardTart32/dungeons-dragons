package unsw.dungeon.tests;

import unsw.dungeon.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class EnemyTest {

	@Test
	void testEnemy() {
		Enemy enemy = new Enemy(null,5,7);
		
		assertEquals(enemy.getX(),5);
		assertEquals(enemy.getY(),7);
	}
	
	@Test
	void testEnemyDeath() {
		Enemy enemy = new Enemy(null,5,7);
		
		enemy.die();
		
		assertNotEquals(enemy.getX(), 5);
		assertNotEquals(enemy.getY(), 7);
	}

}
