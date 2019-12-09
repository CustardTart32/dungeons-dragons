package unsw.dungeon.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;

import unsw.dungeon.Boulder;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Player;
import unsw.dungeon.Wall;

public class BoulderTest {
	private Dungeon dungeon;
	private Player player;
	private Boulder boulder1;
	private Boulder boulder2;
	private Boulder boulder3;
	private Boulder boulder4;
	private Wall wall;
	
	@Before
	public void initializeDungeon() {
		dungeon = new Dungeon(10, 10);
		player = new Player(dungeon, 3, 4);
		boulder1 = new Boulder(dungeon, 3, 3);
		boulder2 = new Boulder(dungeon, 8, 9);
		boulder3 = new Boulder(dungeon, 6, 6);
		boulder4 = new Boulder(dungeon, 6, 7);
		wall = new Wall(9, 9);
		dungeon.setPlayer(player);
		dungeon.addEntity(boulder1);
		dungeon.addEntity(boulder2);
		dungeon.addEntity(boulder3);
		dungeon.addEntity(boulder4);
		dungeon.addEntity(wall);
	}
	
	@Test
	public void pushBoulderUp() {
		initializeDungeon();
		player.moveUp();
		assertEquals(2, boulder1.getY());
		assertEquals(3, boulder1.getX());
	}
	
	@Test
	public void pushBoulderDown() {
		initializeDungeon();
		player.setX(3);
		player.setY(2);
		player.moveDown();
		assertEquals(4, boulder1.getY());
		assertEquals(3, boulder1.getX());
	}
	
	@Test
	public void pushBoulderLeft() {
		initializeDungeon();
		player.setX(4);
		player.setY(3);
		player.moveLeft();
		assertEquals(2, boulder1.getX());
		assertEquals(3, boulder1.getY());
	}
	
	@Test
	public void pushBoulderRight() {
		initializeDungeon();
		player.setX(2);
		player.setY(3);
		player.moveRight();
		assertEquals(4, boulder1.getX());
		assertEquals(3, boulder1.getY());
	}
	
	@Test
	public void pushTwoBouldersFail() {
		initializeDungeon();
		player.setX(6);
		player.setY(5);
		player.moveDown();
		assertEquals(6, boulder3.getX());
		assertEquals(6, boulder3.getY());
	}
	
	@Test
	public void pushBoulderWallFail() {
		initializeDungeon();
		player.setX(7);
		player.setY(9);
		player.moveRight();
		assertEquals(8, boulder2.getX());
		assertEquals(9, boulder2.getY());
	}
	
	
}
