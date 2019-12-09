package unsw.dungeon;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Loads a dungeon from a .json file.
 *
 * By extending this class, a subclass can hook into entity creation. This is
 * useful for creating UI elements with corresponding entities.
 *
 * @author Robert Clifton-Everest
 *
 */
public abstract class DungeonLoader {

    private JSONObject json;

    public DungeonLoader(String filename) throws FileNotFoundException {
        json = new JSONObject(new JSONTokener(new FileReader("dungeons/" + filename)));
    }

    /**
     * Parses the JSON to create a dungeon.
     * @return
     */
    public Dungeon load() {
        int width = json.getInt("width");
        int height = json.getInt("height");

        Dungeon dungeon = new Dungeon(width, height);

        JSONArray jsonEntities = json.getJSONArray("entities");

        for (int i = 0; i < jsonEntities.length(); i++) {
            loadEntity(dungeon, jsonEntities.getJSONObject(i));
        }
        
        //Load goals and set observers
        JSONObject jsonGoals = json.getJSONObject("goal-condition");
        
        createGoalTree(jsonGoals, dungeon);
        
    	//recurse down tree to find individual goals and set observer pattern
        setObservers(dungeon.getFinal_goal(),dungeon);
        
        //Set Opposite Portals
        dungeon.setAltPortals();
        
        return dungeon;
    }
    
    private void setObservers(Goal goal, Dungeon dungeon) {
    	if (goal instanceof IndividualGoal) {
    		IndividualGoal inv_goal = (IndividualGoal) goal;
    		String type = inv_goal.getType();
    		List<Entity> entities = dungeon.getEntities();
    		
    		
    		if (type.equals("exit")) {
        		for (Entity entity: entities) {
        			if (entity instanceof Exit) {
        				Exit exit = (Exit) entity;
        				exit.setObserver(inv_goal.getObs());
        				inv_goal.getObs().addSubject((Subject) exit);
        			}
        		}	
    		}
    		
    		if (type.equals("treasure")) {
        		for (Entity entity: entities) {
        			if (entity instanceof Treasure) {
        				Treasure treasure = (Treasure) entity;
        				treasure.setObserver(inv_goal.getObs());
        				inv_goal.getObs().addSubject((Subject) treasure);
        			}
        		}	
    		}
    		
    		if (type.equals("enemies")) {
    			for (Entity entity: entities) {
        			if (entity instanceof Enemy) {
        				Enemy enemy = (Enemy) entity;
        				enemy.setObserver(inv_goal.getObs());
        				inv_goal.getObs().addSubject((Subject) enemy);
        			}
        		}	
    		}
    		
    		if (type.equals("boulders")) {
    			for (Entity entity: entities) {
        			if (entity instanceof Switch) {
        				Switch floor_switch = (Switch) entity;
        				floor_switch.setObserver(inv_goal.getObs());
        				inv_goal.getObs().addSubject((Subject) floor_switch);
        			}
        		}	
    		}
    		
    		// If boulder is already on floor switch, notify that observer
    		for (Entity entity : dungeon.getEntities()) {
    			if (entity instanceof Switch) {
    				for (Entity check : dungeon.getEntities()) {
    					if (check instanceof Boulder 
    							&& entity.getX() == check.getX()
    							&& entity.getY() == check.getY()) {
    						((Switch) entity).updateSwitch();
    						continue;
    					}
    				}
    			}
    		}
    		
    	}
    	else {
    		CompositeGoal comp_goal = (CompositeGoal) goal;
    		setObservers(comp_goal.getGoalA(), dungeon);
    		setObservers(comp_goal.getGoalB(), dungeon);
    	}
    	
    }
    
    private void createGoalTree(JSONObject goals, Dungeon dungeon) {
    	Goal goal;
    	String type = goals.get("goal").toString();
    	
    	if (type.equals("AND")) {
        	goal = new CompositeGoal("AND");
        	recurseGoalTree(goals.getJSONArray("subgoals"), (CompositeGoal) goal);
        } 
        else if (type.equals("OR")) {
        	goal = new CompositeGoal("OR");
        	recurseGoalTree(goals.getJSONArray("subgoals"), (CompositeGoal) goal);
        } 
        else {
        	goal = new IndividualGoal(type);
        }
    	
    	dungeon.setFinal_goal(goal);
    	
    	return;
    }
    
    private void recurseGoalTree(JSONArray subgoals, CompositeGoal goal) {
    	Goal new_subgoal;
    	
    	String subgoal_a = subgoals.getJSONObject(0).getString("goal");
    	String subgoal_b = subgoals.getJSONObject(1).getString("goal");
    	
    	if (subgoal_a.equals("AND")) {
    		new_subgoal = new CompositeGoal("AND");
    		goal.setGoalA(new_subgoal);
    		recurseGoalTree(subgoals.getJSONObject(0).getJSONArray("subgoals"), (CompositeGoal) new_subgoal);
    	}
    	else if (subgoal_a.equals("OR")) {
    		new_subgoal = new CompositeGoal("OR");
    		goal.setGoalA(new_subgoal);
    		recurseGoalTree(subgoals.getJSONObject(0).getJSONArray("subgoals"), (CompositeGoal) new_subgoal);
    	}
    	else {
    		new_subgoal = new IndividualGoal(subgoal_a);
    		goal.setGoalA(new_subgoal);
    	}
    	
    	if (subgoal_b.equals("AND")) {
    		new_subgoal = new CompositeGoal("AND");
    		goal.setGoalB(new_subgoal);
    		recurseGoalTree(subgoals.getJSONObject(1).getJSONArray("subgoals"), (CompositeGoal) new_subgoal);
    	}
    	else if (subgoal_b.equals("OR")) {
    		new_subgoal = new CompositeGoal("OR");
    		goal.setGoalB(new_subgoal);
    		recurseGoalTree(subgoals.getJSONObject(1).getJSONArray("subgoals"), (CompositeGoal) new_subgoal);
    	}
    	else {
    		new_subgoal = new IndividualGoal(subgoal_b);
    		goal.setGoalB(new_subgoal);
    	}
    }

    private void loadEntity(Dungeon dungeon, JSONObject json) {
        String type = json.getString("type");
        int x = json.getInt("x");
        int y = json.getInt("y");

        Entity entity = null;
        switch (type) {
        case "player":
            Player player = new Player(dungeon, x, y);
            dungeon.setPlayer(player);
            onLoad(player);
            entity = player;
            break;
        case "wall":
            Wall wall = new Wall(x, y);
            onLoad(wall);
            entity = wall;
            break;
        case "sword":
        	Sword sword = new Sword(x, y, dungeon);
        	onLoad(sword);
        	entity = sword;
        	break;
        case "invincibility":
        	Potion potion = new Potion(x, y, dungeon);
        	onLoad(potion);
        	entity = potion;
        	break;
        case "key":
        	int id = json.getInt("id");
        	Key key = new Key(x, y, id, dungeon);
        	onLoad(key);
        	entity = key;
        	break;	
        case "enemy":
        	Enemy enemy = new Enemy(dungeon,x,y);
        	onLoad(enemy);
        	entity = enemy;
        	break;
	    case "treasure":
	    	Treasure treasure = new Treasure(x, y);
	    	onLoad(treasure);
	    	entity = treasure;
	    	break;
	    case "exit":
	    	Exit exit = new Exit(x,y);
	    	onLoad(exit);
	    	entity = exit;
	    	break;
	    case "door":
	    	int door_id = json.getInt("id");
	    	Door door = new Door(x, y, door_id);
	    	onLoad(door);
	    	entity = door;
	    	break;
	    case "open_door":
	    	int open_door_id = json.getInt("id");
	    	Door open_door = new Door(x, y, open_door_id);
	    	onLoad(open_door);
	    	entity = open_door;
	    	break;
	    case "portal":
	    	int portal_id = json.getInt("id");
	    	Portal portal = new Portal(x,y,portal_id, dungeon);
	    	onLoad(portal);
	    	entity = portal;
	    	break;
	    case "boulder":
	    	Boulder boulder = new Boulder(dungeon, x, y);
	    	onLoad(boulder);
	    	entity = boulder;
	    	break;
	    case "switch":
	    	Switch floor_switch = new Switch(x, y);
	    	onLoad(floor_switch);
	    	entity = floor_switch;
	    	break;
    	default:
    		break;
        }
        dungeon.addEntity(entity);
    }

    public abstract void onLoad(Entity player);

    public abstract void onLoad(Wall wall);
    
    public abstract void onLoad(Sword sword);
    
    public abstract void onLoad(Potion potion);
    
    public abstract void onLoad(Key key);
    
    public abstract void onLoad(Enemy enemy);
    
    public abstract void onLoad(Treasure treasure);
    
    public abstract void onLoad(Exit exit);
    
    public abstract void onLoad(Door door);
    
    public abstract void onLoad(Boulder boulder);

	public abstract void onLoad(Switch floor_switch); 

    public abstract void onLoad(Portal portal);
    // TODO Create additional abstract methods for the other entities

}
