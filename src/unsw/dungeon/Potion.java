package unsw.dungeon;

public class Potion extends Entity implements Collectible {
	private int timer;
	public Dungeon dungeon;
	
	public Potion(int x, int y, Dungeon dungeon) {
        super(x, y);
        this.timer = 5;
        this.dungeon = dungeon;
    }
	
	public int wearOff() {
    	this.timer--;
    	return timer;
    }
	
	public int getTime() {
		return timer;
	}
	
	public void onPickUp() {
		dungeon.getPlayer().equipPotion(this);
		
		setVisible(false);
		
		return;
	}
 		
}
