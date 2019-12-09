/**
 *
 */
package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * A dungeon in the interactive dungeon player.
 *
 * A dungeon can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 *
 * @author Robert Clifton-Everest
 *
 */
public class Dungeon {

    private int width, height;
    private List<Entity> entities;
    private Player player;
    private Goal final_goal;
    private BooleanProperty completed;
    private BooleanProperty lost;
    private String treasure_prog;
    private String enemy_prog;
    private String switch_prog;

    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.player = null;
        this.completed = new SimpleBooleanProperty(false);
        this.lost = new SimpleBooleanProperty(false);
        this.treasure_prog = null;
        this.enemy_prog = null;
        this.switch_prog = null;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }
    
    public List<Entity> getEntities() {
    	return entities;
    }
    
    public BooleanProperty completed() {
    	return completed;
    }
    
    public void setCompleted(Boolean bool) {
    	completed().set(bool);
    }
    
    public BooleanProperty lost() {
    	return lost;
    }
    
    public void setLost(Boolean bool) {
    	lost().set(bool);
    }
    
    public Boolean canMove(Entity entity, int x, int y) {
    	for (Entity other: getEntities()) {
    		if (other instanceof Blockable && other.getX() == x && other.getY() == y) {    			
    			Blockable block = (Blockable) other;
    			if (block.canMove(entity, x, y) == false) {
    				return false;
    			}
    		}
    	}	
    	return true;
    }
    
    //Check
    public Boolean checkBlockable(int x, int y) {
    	for (Entity entity: getEntities() ) {
    		// A function that checks if there is a blockable entity in a square
    		if (entity instanceof Blockable && entity.getX() == x && entity.getY() == y) {
    			return true;
    		}
    	}
    	return false;
    }
    
    //Check
    public void alertSwitch(int x, int y) {
    	for (Entity entity : getEntities() ) {
    		if (entity instanceof Switch && entity.getX() == x && entity.getY() == y) {
    			((Switch) entity).updateSwitch();
    			break;
    		}
    	}
    }
    	
    
    //Check
    public void callPickUp() {
    	// Run the pickUp function to add it to the player
    	Entity subject = null;
    	
    	for (Entity currEnt : getEntities()) {
    		if (currEnt instanceof Collectible
    				&& getPlayer().getX() == currEnt.getX() 
    				&& getPlayer().getY() == currEnt.getY()) {
    			Collectible collectible = (Collectible) currEnt;
    			collectible.onPickUp();
    			subject = currEnt;
    			
//    			if (collectible instanceof Sword) { 
//    				Sword sword = (Sword) collectible;
//    				sword.onPickUp();
//    				subject = currEnt;
//    				break;
//    			} else if (collectible instanceof Potion) {
//    				Potion potion = (Potion) collectible;
//    				potion.onPickUp();
//    				subject = currEnt;
//    				break;
//    			} else if (collectible instanceof Key) {
//    				Key key = (Key) collectible;
//    				key.onPickUp();
//    				subject = currEnt;
//    				break;
//    			} else if (collectible instanceof Treasure) {
//    				Treasure treasure = (Treasure) collectible;
//    				treasure.onPickUp();
//    				subject = currEnt;
//    				break;
//    			}
    		}
    	}
    	
    	// Remove it from the list of entities that exist in the dungeon
    	entities.remove(subject);
    }
    
    public void moveEnemies() {
    	for (Entity entity: getEntities()) {
    		if (entity instanceof Enemy) {
    			Enemy enemy = (Enemy) entity;
    			enemy.makeMove();
    		}
    	}
    }
    
    public void doCombat() {
    	List<Entity> toRemove = new ArrayList<Entity> ();
    	
    	for (Entity entity: getEntities()) {
    		if (entity instanceof Enemy && entity.getX() == getPlayer().getX() && entity.getY() == getPlayer().getY()) {
    			Enemy enemy = (Enemy) entity;
    			
    			if (player.getPotion() != null) {
    				toRemove.add(entity);
    				enemy.die();
    			} 
    			else if (player.getSword() != null) {
       				toRemove.add(entity);
    				getPlayer().attack();
    				enemy.die();
    			} 
    			else {
    				lost.set(true);
    			}
    		}
    	}
    	
    	for (Entity entity: toRemove) {
    		getEntities().remove(entity);
    	}
    }
    
    public void checkExits() {
    	int playerX = getPlayer().getX();
    	int playerY = getPlayer().getY();
    	
    	for (Entity entity: getEntities()) {
    		if (entity instanceof Exit) {
    			Exit exit = (Exit) entity;
    			if (entity.getX() == playerX && entity.getY() == playerY) {
    				exit.hasEntered();
    				return;
    			}
    			exit.hasLeft();
    			return;
    		}
    	}
    }
    
    //Check
    public void unlockDoor(int x, int y, int keyID) {
    	for (Entity entity: getEntities()) {
    		if ((entity instanceof Door) 
    				&& (keyID == ((Door) entity).getId())
    				&&
    				(((x == entity.getX() + 1) && (y == entity.getY()))
    				|| ((x == entity.getX() - 1) && (y == entity.getY()))
    				|| ((y == entity.getY() + 1) && (x == entity.getX()))
    				|| ((y == entity.getY() - 1) && (x == entity.getX())))) {
    			((Door) entity).unlockDoor();
    		}
    	}
    }
    
    public void setEnemyFlee() {
    	for (Entity entity: getEntities()) {
    		if (entity instanceof Enemy) {
    			Enemy enemy = (Enemy) entity;
    			enemy.setMovement(new MovementAway());
    		}
    	}
    }
    
    public void setEnemyChase() {
    	for (Entity entity: getEntities()) {
    		if (entity instanceof Enemy) {
    			Enemy enemy = (Enemy) entity;
    			enemy.setMovement(new MovementTo());
    		}
    	}
    }
    
    public int teleportEntity() {
    	int num_teleport = 0;
    	
    	for (Entity entity: getEntities()) {
    		if (entity instanceof Portal) {
    			Portal portal = (Portal) entity;
    			
    			if (getPlayer().getX() == portal.getX() && getPlayer().getY() == portal.getY()) {
    				System.out.print("Stepped on portal\n");
    				
    				//Generate tile for portal to teleport
    				int[] exit_position = portal.getExitPosition();
    				
    				
    				getPlayer().setX(exit_position[0]);
    				getPlayer().setY(exit_position[1]);
    				num_teleport++;
    			}
    			
    			
    			for(Entity entity_alt: getEntities()) {
    				if (!(entity_alt instanceof Portal) && entity_alt.getX() == portal.getX() && entity_alt.getY() == portal.getY()) {
    					int[] exit_position = portal.getExitPosition();
    					
    					entity_alt.setX(exit_position[0]);
    					entity_alt.setY(exit_position[1]);
    					
    					//Case where boulder is teleported onto switch
    					if (entity_alt instanceof Boulder) {
    						//System.out.print("Alerting switch\n");
    						alertSwitch(exit_position[0],exit_position[1]);
    					}
    					
    					num_teleport++;
    				}
    			}
    		}
    	}
    	return num_teleport;
    }
    
    public void setAltPortals() {
    	for (Entity entity: getEntities()) {
    		if (entity instanceof Portal) {
    			Portal portal = (Portal) entity;
    			if (portal.getAltPortal() == null) {
        			Portal alt_portal = findAltPortal(portal);
        			portal.setAltPortal(alt_portal);
    			}
    		}
    	}
    }
    
    private Portal findAltPortal(Portal portal) {
    	for (Entity entity: getEntities()) {
    		if (entity instanceof Portal) {
    			Portal alt_portal = (Portal) entity;
    			if (alt_portal.isOppositePortal(portal)) {
    				//System.out.print("Found alt portal\n");
    				return alt_portal;
    			}
    		}
    	}
    	System.out.print("Could not find alt portal\n");
    	return null;
    }
    
    public Boolean checkSquare(int x, int y) {
    	for (Entity entity: getEntities()) {
    		if (entity instanceof Blockable && entity.getX() == x && entity.getY() == y) {
    			Blockable block = (Blockable) entity;
    			if (block.canMove(new Enemy(this,1,1),x,y) == false) {
    				return false;
    			}
    		}
    	}
    	
    	return true;
    }
    
    public void isComplete() {
    	if (getFinal_goal() != null && getFinal_goal().completed()) {
    		setCompleted(true);
    	}
    }

	public Goal getFinal_goal() {
		return final_goal;
	}

	public void setFinal_goal(Goal final_goal) {
		this.final_goal = final_goal;
	}
	
	public void printGoals() {
		getFinal_goal().printGoal();
		// System.out.println(getFinal_goal().showGoal());
	}
	
	public void processGoals() {
		for (String goal : getFinal_goal().showGoal()) {
			if (goal.contains("treasure")) { treasure_prog = goal.substring(0, 3); }
			else if (goal.contains("enemies")) { enemy_prog = goal.substring(0, 3); }
			else if (goal.contains("boulders")) { switch_prog = goal.substring(0, 3); }
		}
	}
	
	public String getTreasureProg() {
		if (treasure_prog == null) { return "0/0";}
		else { return treasure_prog; }
	}
	
	public String getEnemyProg() {
		if (enemy_prog == null) { return "0/0";}
		else { return enemy_prog; }
	}
	
	public String getSwitchProg() {
		if (switch_prog == null) { return "0/0";}
		else { return switch_prog; }
	}
	
	
}
    
