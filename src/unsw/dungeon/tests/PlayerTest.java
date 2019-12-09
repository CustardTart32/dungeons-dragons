package unsw.dungeon.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;

import unsw.dungeon.Boulder;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Player;

public class PlayerTest {
	private Dungeon dungeon;
	private Player player;
	@Before
	public void initializeDungeon() {
		dungeon = new Dungeon(10, 10);
		player = new Player(dungeon, 3, 2);
		dungeon.setPlayer(player);
	}
	
	@Test
	public void testMoveUp() {
		initializeDungeon();
		player.moveUp();
		assertEquals(1, player.getY());
		assertEquals(3, player.getX());
	}
	
	@Test
	public void testMoveDown() {
		initializeDungeon();
		player.moveDown();
		assertEquals(3, player.getY());
		assertEquals(3, player.getX());
	}
	
	@Test
	public void testMoveLeft() {
		initializeDungeon();
		player.moveLeft();
		assertEquals(2, player.getX());
		assertEquals(2, player.getY());
	}
	
	@Test
	public void testMoveRight() {
		initializeDungeon();
		player.moveRight();
		assertEquals(4, player.getX());
		assertEquals(2, player.getY());
	}
}