package unsw.dungeon;

public class Sword extends Entity implements Collectible {
	private int durability;
	public Dungeon dungeon;
	
	public Sword(int x, int y, Dungeon dungeon) {
        super(x, y);
        this.durability = 5;
        this.dungeon = dungeon;
    }
	
	public int use() {
    	this.durability--;
    	return durability;
    }
	
	public int getDurability() {
		return durability;
	}
	
	public void onPickUp() {
		dungeon.getPlayer().equipSword(this);
		
		setVisible(false);
		
		return;
	}
}
