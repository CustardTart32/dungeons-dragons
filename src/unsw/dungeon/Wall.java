package unsw.dungeon;

public class Wall extends Entity implements Blockable {

    public Wall(int x, int y) {
        super(x , y);
    }
    
    public Boolean canMove(Entity entity, int x, int y) {
    	return false;
    }
}
