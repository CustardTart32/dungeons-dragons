package unsw.dungeon;

public class Door extends Entity implements Blockable {
	
	private Boolean state;
	private int id;

	public Door(int x, int y, int id) {
        super(x , y);
        this.state = false;
        this.id = id;
    }
	
	public void unlockDoor() {
		this.state = true;
		setVisible(false);
		System.out.println("Door unlocked!");
	}
	
	public int getId() {
		return id;
	}
	
	public Boolean getState() {
		return state;
	}
	
	public Boolean canMove(Entity entity, int x, int y) {
		return state;
    }
	
}
