package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity {
	
	private Dungeon dungeon;
    Sword sword;
    Potion potion;
    List<Key> keys;

    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
        this.sword = null;
        this.potion = null;
        this.keys = new ArrayList<>();
    }
    
    public Dungeon getDungeon() {
    	return dungeon;
    }
    
    public Sword getSword() {
		return sword;
	}

	public Potion getPotion() {
		return potion;
	}

	public List<Key> getKeys() {
		return keys;
	}

    public void moveUp() {
        if (getY() > 0) {
        	if (isValidMove(this,getX(), getY() - 1)) {
        		y().set(getY() - 1);
        		doInteractions();	
        	}
        }
            
    }

    public void moveDown() {
        if (getY() < dungeon.getHeight() - 1) {
        	
        	if (isValidMove(this, getX(), getY() + 1)) {
            	y().set(getY() + 1);
            	doInteractions();
        	}
        }
            
    }
    
    public void moveLeft() {
        
    	if (getX() > 0) {
    		if (isValidMove(this, getX() - 1, getY())) {
        		x().set(getX() - 1);
        		doInteractions();
        	}
    	}
    }

	public void moveRight() {
        if (getX() < dungeon.getWidth() - 1) {
        	if (isValidMove(this, getX() + 1, getY())) {
            	x().set(getX() + 1);
            	doInteractions();

        	}
        }  
    }
	
	public void doInteractions() {
		//Teleport Player and Other Entities
		getDungeon().teleportEntity();
		
		//Combat for player movement
		getDungeon().doCombat(); 
		
		//Check potion if still in use
		checkPotion();
		
		//Pick up any collectibles
		getDungeon().callPickUp();
		
		//Enemy Movement
		getDungeon().moveEnemies();
		
		//Teleport Enemies if they moved into a portal
		getDungeon().teleportEntity();
		
		//Combat for Enemy Movement
		getDungeon().doCombat(); 
		
		//Check for exits
		getDungeon().checkExits();
		
		//Finish level if player is on exit and has completed objectives
		getDungeon().isComplete();

	}
    
    public Boolean isValidMove(Entity entity, int x, int y) {
    	return getDungeon().canMove(entity,x,y);
    }
    
    public void equipSword(Sword sword) {
    	this.sword = sword;
    	System.out.println("Sword");
    }
    
    public void equipPotion(Potion potion) {
    	this.potion = potion;
    	getDungeon().setEnemyFlee();
    	System.out.println("Potion");
    }
    
    public void equipKey(Key key) {
    	this.keys.add(key);
    	System.out.println("Key");
    }
    
    public void attack() {
    	if (getSword().use() == 0) {
    		this.sword = null;
    	}
    }
    
    public void unlockDoor() {
    	for (Key key : this.keys) {
    		// Check 4 adjacent positions
    		getDungeon().unlockDoor(getX(), getY(), key.getId());
    	}
    }
    
    public void checkPotion() {
    	if (getPotion() != null && getPotion().wearOff() == 0) {
    		this.potion = null;
    		getDungeon().setEnemyChase();
    		System.out.println("Potion wore off!");
    	}
    }
    
    public void showInventory() {
    	if (sword != null) {System.out.println("Sword durability: " + sword.getDurability());}
    	if (potion != null) {System.out.println("Invicibility will last for : " + potion.getTime() + " more steps");}
    	System.out.println("Keys: [ " );
    	for (Key key : this.keys) {
    		System.out.println(key.getId());
    	}
    	System.out.println("]");
    }
    
    public String showKeys() {
    	List<String> list_keys = new ArrayList<>();
    	for (Key key : this.keys) {
    		list_keys.add(String.valueOf(key.getId()));
    	}
    	return String.valueOf(list_keys);
    }
    
    public String showSwordDurability() {
    	String str_sword;
    	if (sword == null) { str_sword = "0"; }
    	else { str_sword = String.valueOf(sword.getDurability()); }
    	return str_sword;
    }
    
    public String showPotionSteps() {
    	String str_potion;
    	if (potion == null) { str_potion = "0"; }
    	else { str_potion = String.valueOf(potion.getTime()); }
    	return str_potion;
    }
}
