package unsw.dungeon;

public class Boulder extends Entity implements Blockable  {
	
	Dungeon dungeon;
	
	public Boulder(Dungeon dungeon, int x, int y) {
        super(x , y);
        this.dungeon = dungeon;
        
    }
	
	public Boolean canMove(Entity entity, int x, int y) {
		
		if (entity instanceof Enemy) { return false; }
		if (entity.getX() == x - 1 && entity.getY() == y) {
			// Check for an instance of blockable to the right 
			if (getDungeon().checkBlockable(x+1, y)) { return false; }
			else { moveBoulder(x+1, y); return true; }
		} else if (entity.getX() == x + 1 && entity.getY() == y) {
			// Check for an instance of blockable to the left
			if (getDungeon().checkBlockable(x-1, y)) { return false; }
			else { moveBoulder(x-1, y); return true; }
		} else if (entity.getY() == y - 1 && entity.getX() == x) {
			// Check for an instance of blockable upwards
			if (getDungeon().checkBlockable(x, y+1)) { return false; }
			else { moveBoulder(x, y+1); return true; }
		} else if (entity.getY() == y + 1 && entity.getX( )== x) {
			// Check for an instance of blockable downwards
			if (getDungeon().checkBlockable(x, y-1)) { return false; }
			else { moveBoulder(x, y-1); return true; }
		}
		return true;
	}
	
	public void moveBoulder(int x, int y) {
		dungeon.alertSwitch(getX(), getY());
		this.setX(x);
		this.setY(y);
		dungeon.alertSwitch(x, y);
	}
	
	public Dungeon getDungeon() {
		return dungeon;
	}

}
