package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

public class Enemy extends Entity implements Subject {
	Dungeon dungeon;
	Observer obs;
	Movement movement;
	
	
	public Enemy(Dungeon dungeon, int x, int y) {
		super(x, y);
		this.dungeon = dungeon;
		this.movement = new MovementTo();
	}
	
	public void makeMove() {		
		List<String> directions = new ArrayList<String>();  
		
		calculateMove(directions);
		makeMove(directions);
	}
	
	
	public void calculateMove(List<String> directions) {
		int x_distance = getPlayerX() - getX();
		int y_distance = getY() - getPlayerY();
		
		getMovement().calculateMove(directions, x_distance, y_distance);
	}
	
	public void makeMove(List<String> directions) {
		for (String direction: directions) {
			if (direction.equals("up")) {
				if (isValidMove(this,getX(), getY() - 1)) {
					setY(getY() - 1);
					return;
				}
 			}
			else if (direction.equals("right")) {
				if (isValidMove(this, getX() + 1, getY())) {
					setX(getX() + 1);
					return;
				}
			}
			else if (direction.equals("left")) {
				if (isValidMove(this, getX() - 1, getY())) {
					setX(getX() - 1);
					return;
				}
			}
			else {
				if (isValidMove(this, getX() , getY() + 1)) {
					setY(getY() + 1);
					return;
				}
			}	
		}
	}
	
	public void die() {
		System.out.print("Enemy died\n");
		
		
		if (getObserver() != null) {
			notifyObserver();
		}
		
		setVisible(false);
	}
	
	public void notifyObserver() {
		getObserver().update((Subject) this);
	}
	
	public Dungeon getDungeon() {
		return dungeon;
	}
	
	public Player getPlayer() {
		return getDungeon().getPlayer();
	}
	
	public int getPlayerX() {
		return getPlayer().getX();
	}
	
	public int getPlayerY() {
		return getPlayer().getY();
	}
	
	public Boolean isValidMove(Entity entity, int x, int y) {
		return getDungeon().canMove(entity, x, y);
	}
	
	public void setX(int x) {
		x().set(x);
	}
	
	public void setY(int y) {
		y().set(y);
	}
	
	public Observer getObserver() {
		return obs;
	}

	public void setObserver(Observer obs) {
		this.obs = obs;
	}

	public Movement getMovement() {
		return movement;
	}

	public void setMovement(Movement movement) {
		this.movement = movement;
	}
	
}
