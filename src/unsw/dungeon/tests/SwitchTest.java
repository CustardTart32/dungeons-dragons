package unsw.dungeon.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;

import unsw.dungeon.Boulder;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Player;
import unsw.dungeon.Switch;

public class SwitchTest {
	private Dungeon dungeon;
	private Player player;
	private Boulder boulder;
	private Switch floor_switch;
	
	@Before
	public void initializeDungeon() {
		dungeon = new Dungeon(10, 10);
		player = new Player(dungeon, 3, 4);
		boulder = new Boulder(dungeon, 3, 3);
		floor_switch = new Switch(2, 2);
		dungeon.setPlayer(player);
		dungeon.addEntity(boulder);
		dungeon.addEntity(floor_switch);
	}
	
	@Test
	public void activateSwitch() {
		initializeDungeon();
		boulder.moveBoulder(2, 2);
		assertEquals(true, floor_switch.getState());
	}
	
	@Test
	public void deactivateSwitch() {
		initializeDungeon();
		boulder.moveBoulder(2, 2);
		assertEquals(true, floor_switch.getState());
		boulder.moveBoulder(7, 7);
		assertEquals(false, floor_switch.getState());
	}
	
}