package unsw.dungeon.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;

import unsw.dungeon.Boulder;
import unsw.dungeon.Door;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Enemy;
import unsw.dungeon.Exit;
import unsw.dungeon.GoalObserver;
import unsw.dungeon.Key;
import unsw.dungeon.Observer;
import unsw.dungeon.Player;
import unsw.dungeon.Portal;
import unsw.dungeon.Potion;
import unsw.dungeon.Sword;
import unsw.dungeon.Treasure;
import unsw.dungeon.Wall;

public class DungeonTest {
	Dungeon dungeon;
	
	@Before
	public void initializeDungeon() {
		dungeon = new Dungeon(10, 10);
	}
	
	// Tests for unlockDoor
	@Test
	public void testUnlockDoor() {
		Door door1 = new Door(3, 3, 1);
		Player player = new Player(dungeon, 3, 4);
		Key key  = new Key(10, 10, 1, dungeon);
		player.equipKey(key);
		
		dungeon.addEntity(door1);
		dungeon.setPlayer(player);
		
		player.unlockDoor();

		
		assertEquals(true, door1.getState());
	}
	
	@Test
	public void testUnlockDoorWrongKey() {
		Door door1 = new Door(3, 3, 1);
		Player player = new Player(dungeon, 3, 4);
		Key key  = new Key(10, 10, 1, dungeon);
		Door door2 = new Door(5,5,2);
		player.equipKey(key);
		
		dungeon.addEntity(door1);
		dungeon.addEntity(door2);
		dungeon.setPlayer(player);
	
		player.setX(5);
		player.setY(6);
		player.unlockDoor();
		assertEquals(false, door2.getState());
	}
	
	@Test
	public void testUnlockDoorWrongPosition() {
		Door door1 = new Door(3, 3, 1);
		Player player = new Player(dungeon, 3, 4);
		Key key  = new Key(10, 10, 1, dungeon);
		Door door2 = new Door(5,5,2);
		player.equipKey(key);
		
		dungeon.addEntity(door1);
		dungeon.addEntity(door2);
		dungeon.setPlayer(player);
		
		player.setX(5);
		player.setY(6);
		player.unlockDoor();
		assertEquals(false, door1.getState());
	}
	
	// Tests for checkBlockable
	@Test
	public void testCheckBlockableTrue() {
		Door door1 = new Door(3,3,1);
		dungeon.addEntity(door1);
		
		assertEquals(true, dungeon.checkBlockable(3, 3));
	}
	
	@Test
	public void testCheckBlockableFalse() {
		assertEquals(false, dungeon.checkBlockable(6, 6));
	}
	
	// Tests for callPickUp
	@Test
	public void testPickUpSword() {
		Player player = new Player(dungeon, 3, 4);
		Sword sword = new Sword(1, 2,dungeon);
		
		dungeon.setPlayer(player);
		dungeon.addEntity(sword);
		
		player.setX(1);
		player.setY(1);
		player.moveDown();
		assertEquals(sword, player.getSword());
	}
	
	@Test
	public void testPickUpPotion() {
		Player player = new Player(dungeon, 3, 4);
		dungeon.setPlayer(player);
		
		Potion potion = new Potion(2, 3,dungeon);
		dungeon.addEntity(potion);
		
		player.setX(2);
		player.setY(2);
		player.moveDown();
		assertEquals(potion, player.getPotion());
	}
	
	@Test
	public void testPickUpKey() {
		Player player = new Player(dungeon, 3, 4);
		dungeon.setPlayer(player);
		
		Key key = new Key(9, 9, 2, dungeon);
		dungeon.addEntity(key);
		
		Door door2 = new Door(5, 5, 2);
		dungeon.addEntity(door2);
		
		player.setX(9);
		player.setY(8);
		player.moveDown();
		player.setX(5);
		player.setY(4);
		player.unlockDoor();
		assertEquals(true, door2.getState());
	}
	
	@Test
	public void checkBlockableWall() {
		Wall wall = new Wall(2,3);
		Enemy enemy = new Enemy(dungeon,2,2);
		
		dungeon.addEntity(wall);
		dungeon.addEntity(enemy);
		
		assertEquals(dungeon.canMove(enemy, 2, 3), false);
	}
	
	@Test
	public void checkBlockableLockedDoor() {
		Door door = new Door(2,3,1);
		
		//System.out.print(door.getState());
		
		Enemy enemy = new Enemy(dungeon,2,2);
		
		dungeon.addEntity(door);
		dungeon.addEntity(enemy);
		
		assertEquals(dungeon.canMove(enemy, 2, 3), false);
	}
	
	@Test
	public void checkBlockableUnlockedDoor() {
		Door door = new Door(2,3,1);
		
		//System.out.print(door.getState());
		
		Enemy enemy = new Enemy(dungeon,2,2);
		
		dungeon.addEntity(door);
		door.unlockDoor();
		dungeon.addEntity(enemy);
		
		assertEquals(dungeon.canMove(enemy, 2, 3), true);
	}
	
	@Test
	public void checkBlockableNothing() {
		Treasure treasure = new Treasure(2,3);
				
		Enemy enemy = new Enemy(dungeon,2,2);
		
		dungeon.addEntity(treasure);
		dungeon.addEntity(enemy);
		
		assertEquals(dungeon.canMove(enemy, 2, 3), true);
	}
	
	@Test
	public void testEnemyMovementEmptyBoard() {
		Enemy enemy = new Enemy(dungeon,3,3);
		Player player = new Player(dungeon,5,3);
		
		dungeon.setPlayer(player);
		dungeon.addEntity(enemy);
		
		dungeon.moveEnemies();
		
		assertEquals(enemy.getX(),4);
		assertEquals(enemy.getY(),3);
	}
	
	@Test
	public void testEnemyMovementEmptyBoardDiagona() {
		Enemy enemy = new Enemy(dungeon,3,3);
		Player player = new Player(dungeon,5,5);
		
		dungeon.setPlayer(player);
		dungeon.addEntity(enemy);
		
		dungeon.moveEnemies();
		
		assertEquals(enemy.getX(),4);
		assertEquals(enemy.getY(),3);
	}
	
	@Test
	public void testEnemyMovementObstacle() {
		Enemy enemy = new Enemy(dungeon,3,3);
		Player player = new Player(dungeon,5,5);
		Boulder boulder = new Boulder(dungeon,4,3);
		
		dungeon.setPlayer(player);
		dungeon.addEntity(enemy);
		dungeon.addEntity(boulder);
		
		dungeon.moveEnemies();
		
		assertEquals(enemy.getX(),3);
		assertEquals(enemy.getY(),4);
	}
	
	@Test
	public void testEnemyMovementMultipleObstacle() {
		Enemy enemy = new Enemy(dungeon,3,3);
		Player player = new Player(dungeon,5,5);
		Boulder boulder = new Boulder(dungeon,3,4);
		Door door = new Door(4,3,1);
		Wall wall = new Wall(2,3);
		
		dungeon.setPlayer(player);
		dungeon.addEntity(enemy);
		dungeon.addEntity(boulder);
		dungeon.addEntity(wall);
		dungeon.addEntity(door);
		
		dungeon.moveEnemies();
		
		assertEquals(enemy.getX(),3);
		assertEquals(enemy.getY(),2);
	}
	
	@Test
	public void testAfraidMovementDiagonal() {
		Enemy enemy = new Enemy(dungeon,1,3);
		Player player = new Player(dungeon,5,5);
		
		dungeon.setPlayer(player);
		dungeon.addEntity(enemy);
		dungeon.setEnemyFlee();
		
		dungeon.moveEnemies();
		
		assertEquals(enemy.getX(),0);
		assertEquals(enemy.getY(),3);
	}
	
	@Test
	public void testAfraidMovementStraight() {
		Enemy enemy = new Enemy(dungeon,3,5);
		Player player = new Player(dungeon,5,5);
		
		dungeon.setPlayer(player);
		dungeon.addEntity(enemy);
		dungeon.setEnemyFlee();
		
		dungeon.moveEnemies();
		
		assertEquals(enemy.getX(),2);
		assertEquals(enemy.getY(),5);
	}
	
	@Test
	public void testMultipleMovement() {
		Enemy enemy1 = new Enemy(dungeon,3,3);
		Player player = new Player(dungeon,5,5);
		Enemy enemy2 = new Enemy(dungeon,1,1);
		
		dungeon.setPlayer(player);
		dungeon.addEntity(enemy1);
		dungeon.addEntity(enemy2);
		
		dungeon.setEnemyFlee();
		dungeon.setEnemyChase();
		
		dungeon.moveEnemies();
		
		assertEquals(enemy1.getX(),4);
		assertEquals(enemy1.getY(),3);
		
		assertEquals(enemy2.getX(),2);
		assertEquals(enemy2.getY(),1);
	}
	
	@Test
	public void testNoCombat() {
		Sword sword = new Sword(2,3,dungeon);
		Player player = new Player(dungeon,2,3);
		
		player.equipSword(sword);
		
		dungeon.setPlayer(player);
		dungeon.doCombat();
		
		assertEquals(sword.getDurability(),5);
	}
	
	@Test
	public void testCombatSword() {
		Sword sword = new Sword(2,3,dungeon);
		Player player = new Player(dungeon,2,3);
		Enemy enemy = new Enemy(dungeon,2,3);
		
		player.equipSword(sword);
		
		dungeon.setPlayer(player);
		dungeon.addEntity(enemy);
		dungeon.doCombat();
		
		assertEquals(sword.getDurability(),4);
		assertNotEquals(enemy.getX(),2);
		assertNotEquals(enemy.getY(),3);
	}
	
	@Test
	public void testCombatPotion() {
		Potion potion = new Potion(2,3,dungeon);
		Player player = new Player(dungeon,2,3);
		Enemy enemy = new Enemy(dungeon,2,3);
		
		player.equipPotion(potion);
		
		dungeon.setPlayer(player);
		dungeon.addEntity(enemy);
		dungeon.doCombat();
		
		assertEquals(potion.getTime(),5);
		assertNotEquals(enemy.getX(),2);
		assertNotEquals(enemy.getY(),3);
	}
	
	@Test
	public void testCombatLoss() {
		Player player = new Player(dungeon,2,3);
		Enemy enemy = new Enemy(dungeon,2,3);
		
		dungeon.setPlayer(player);
		dungeon.addEntity(enemy);
		dungeon.doCombat();
		
		assertEquals(enemy.getX(),2);
		assertEquals(enemy.getY(),3);
	}
	
	@Test
	public void testMoveIntoExit() {
		Player player = new Player(dungeon,2,3);
		Exit exit = new Exit(2,4);
		Observer obs = new GoalObserver();
		exit.setObserver(obs);
		obs.addSubject(exit);
		
		dungeon.setPlayer(player);
		dungeon.addEntity(exit);
		
		player.setX(2);
		player.setY(4);
		
		dungeon.checkExits();
		
		assertEquals(exit.getEntered(), true);
	}
	
	@Test
	public void testMoveOutOfExit() {
		Player player = new Player(dungeon,2,3);
		Exit exit = new Exit(2,4);
		Observer obs = new GoalObserver();
		exit.setObserver(obs);
		obs.addSubject(exit);
		
		dungeon.setPlayer(player);
		dungeon.addEntity(exit);
		
		player.setX(2);
		player.setY(4);
		
		dungeon.checkExits();
		
		player.setX(2);
		player.setY(3);
		
		dungeon.checkExits();
		
		assertEquals(exit.getEntered(), false);
	}
	
	@Test
	public void testMoveNoExit() {
		Player player = new Player(dungeon,2,3);
		Exit exit = new Exit(2,4);
		Observer obs = new GoalObserver();
		exit.setObserver(obs);
		obs.addSubject(exit);
		
		dungeon.setPlayer(player);
		dungeon.addEntity(exit);
		
		player.setX(2);
		player.setY(2);
		
		dungeon.checkExits();
		
		assertEquals(exit.getEntered(), false);
	}
	
	@Test
	public void testfindAltPortal() {
		Portal portal1 = new Portal(2,3,1,dungeon);
		Portal portal2 = new Portal(1,1,1,dungeon);
		Portal portal3 = new Portal(4,1,2,dungeon);
		Portal portal4 = new Portal(3,5,2,dungeon);
		
		dungeon.addEntity(portal1);
		dungeon.addEntity(portal2);
		dungeon.addEntity(portal3);
		dungeon.addEntity(portal4);
		
		dungeon.setAltPortals();
		
		assertEquals(portal1.getAltPortal(),portal2);
		assertEquals(portal2.getAltPortal(),portal1);
		
		assertEquals(portal3.getAltPortal(),portal4);
		assertEquals(portal4.getAltPortal(),portal3);
	}
	
	@Test
	public void testTeleportPlayerNoObstacle() {
		Portal portal1 = new Portal(2,3,1,dungeon);
		Portal portal2 = new Portal(1,1,1,dungeon);
		
		dungeon.addEntity(portal1);
		dungeon.addEntity(portal2);
		
		dungeon.setAltPortals();
		
		Player player = new Player(dungeon,2,3);
		dungeon.setPlayer(player);
		
		dungeon.teleportEntity();
		
		assertTrue((player.getX() == 0 && player.getY() == 1) || (player.getX() == 1 && player.getY() == 0) ||
				(player.getX() == 2 && player.getY() == 1) || (player.getX() == 1 && player.getY() == 2));
	}
	
	@Test
	public void testTeleportEnemyNoObstacle() {
		Portal portal1 = new Portal(2,3,1,dungeon);
		Portal portal2 = new Portal(1,1,1,dungeon);
		
		dungeon.addEntity(portal1);
		dungeon.addEntity(portal2);
		
		dungeon.setAltPortals();
		
		Player player = new Player(dungeon,4,4);
		dungeon.setPlayer(player);
		
		Enemy enemy = new Enemy(dungeon,2,3);
		dungeon.addEntity(enemy);
		
		dungeon.teleportEntity();
		
		assertTrue((enemy.getX() == 0 && enemy.getY() == 1) || (enemy.getX() == 1 && enemy.getY() == 0) ||
				(enemy.getX() == 2 && enemy.getY() == 1) || (enemy.getX() == 1 && enemy.getY() == 2));
	}
	
	@Test
	public void testTeleportBoulderNoObstacle() {
		Portal portal1 = new Portal(2,3,1,dungeon);
		Portal portal2 = new Portal(1,1,1,dungeon);
		
		dungeon.addEntity(portal1);
		dungeon.addEntity(portal2);
		
		dungeon.setAltPortals();
		
		Player player = new Player(dungeon,4,4);
		dungeon.setPlayer(player);
		
		Boulder boulder = new Boulder(dungeon,2,3);
		dungeon.addEntity(boulder);
		
		dungeon.teleportEntity();
		
		assertTrue((boulder.getX() == 0 && boulder.getY() == 1) || (boulder.getX() == 1 && boulder.getY() == 0) ||
				(boulder.getX() == 2 && boulder.getY() == 1) || (boulder.getX() == 1 && boulder.getY() == 2));
	}
	
	@Test 
	public void testTeleportWithObstabcles() {
		Portal portal1 = new Portal(2,3,1,dungeon);
		Portal portal2 = new Portal(1,1,1,dungeon);
		
		dungeon.addEntity(portal1);
		dungeon.addEntity(portal2);
		
		dungeon.setAltPortals();
		
		Player player = new Player(dungeon,4,4);
		dungeon.setPlayer(player);
		
		Enemy enemy = new Enemy(dungeon,2,3);
		dungeon.addEntity(enemy);
		
		Wall wall = new Wall(0,1);
		Door door = new Door(1,0,1);
		Boulder boulder = new Boulder(dungeon,1,2);
		
		dungeon.addEntity(wall);
		dungeon.addEntity(door);
		dungeon.addEntity(boulder);
		
		dungeon.teleportEntity();
		
		assertTrue((enemy.getX() == 2) && (enemy.getY() == 1));
	}
	
	@Test 
	public void testBlockedTeleport() {
		Portal portal1 = new Portal(2,3,1,dungeon);
		Portal portal2 = new Portal(1,1,1,dungeon);
		
		dungeon.addEntity(portal1);
		dungeon.addEntity(portal2);
		
		dungeon.setAltPortals();
		
		Player player = new Player(dungeon,4,4);
		dungeon.setPlayer(player);
		
		Enemy enemy = new Enemy(dungeon,2,3);
		dungeon.addEntity(enemy);
		
		Wall wall = new Wall(0,1);
		Door door = new Door(1,0,1);
		Boulder boulder = new Boulder(dungeon,1,2);
		Wall wall2 = new Wall(2,1);
		
		dungeon.addEntity(wall);
		dungeon.addEntity(door);
		dungeon.addEntity(boulder);
		dungeon.addEntity(wall2);
		
		dungeon.teleportEntity();
		
		assertTrue((enemy.getX() == 2) && (enemy.getY() == 3));
	}
}

