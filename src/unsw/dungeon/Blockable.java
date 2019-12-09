package unsw.dungeon;

public interface Blockable {
	Boolean canMove(Entity entity, int x, int y);
}
