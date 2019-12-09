package unsw.dungeon;

public class Key extends Entity implements Collectible {
	private int id;
	private Dungeon dungeon;
	
	public Key(int x, int y, int id, Dungeon dungeon) {
        super(x, y);
        this.id = id;
        this.dungeon = dungeon;
    }
	
	public int getId() {
		return id;
	}

	public void onPickUp() {
		dungeon.getPlayer().equipKey(this);
		
		setVisible(false);
		
		return;
	}
}
