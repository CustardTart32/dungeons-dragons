package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

public class Portal extends Entity{
	int id; 
	Portal altPortal;
	Dungeon dungeon;
	
	public Portal(int x, int y, int id, Dungeon dungeon) {
		super(x, y);
		this.id = id;
		this.dungeon = dungeon;
	}
	
	public int[] getExitPosition() {
		int[] alt_position = getAltPosition();
		//System.out.print(alt_position[0] + " " + alt_position[1] + "\n");
		List<Boolean> chosen = new ArrayList<Boolean> ();
		
		for (int i = 0; i < 4; i++) {
			chosen.add(false);
		}
		
		int[] result = {-1,-1};
		
		int i = 0;
		while (i < 4) {
			int random = (int)(Math.random() * 4);
			
			if (random == 0 && !chosen.get(random)) {
				if (isValidMove(alt_position[0], alt_position[1] - 1)) {
					result[0] = alt_position[0];
					result[1] = alt_position[1] - 1;
					return result;
				} else {
					chosen.set(random, true);
					i++;
				}
			}
			else if (random == 1 && !chosen.get(random)) {
				if (isValidMove(alt_position[0] + 1, alt_position[1])) {
					result[0] = alt_position[0] + 1;
					result[1] = alt_position[1];
					return result;
				} else {
					chosen.set(random, true);
					i++;
				}
			}
			else if (random == 2 && !chosen.get(random)) {
				if (isValidMove(alt_position[0], alt_position[1] + 1)) {
					result[0] = alt_position[0];
					result[1] = alt_position[1] + 1;
					return result;
				} else {
					chosen.set(random, true);
					i++;
				}
			}
			else if (random == 3 && !chosen.get(random)) {
				if (isValidMove(alt_position[0] - 1, alt_position[1])) {
					result[0] = alt_position[0] - 1;
					result[1] = alt_position[1];
					return result;
				} else {
					chosen.set(random, true);
					i++;
				}
			}
		}
		
		result[0] = getX();
		result[1] = getY();
		return result;
	}
	

	public Boolean isOppositePortal(Portal portal) {
		if (portal.getId() == this.getId() && (portal.getX() != this.getX() || portal.getY() != this.getY())) {
			return true;
		}
		else {
			return false;
		}
	}


	public int[] getAltPosition() {
		int[] positions = {getAltPortal().getX(), getAltPortal().getY()};
		return positions;
	}

	public int getId() {
		return id;
	}

	public Portal getAltPortal() {
		return altPortal;
	}


	public void setAltPortal(Portal altPortal) {
		this.altPortal = altPortal;
	}

	public Dungeon getDungeon() {
		return dungeon;
	}
	
    public Boolean isValidMove(int x, int y) {
    	return getDungeon().checkSquare(x,y);
    }
	
}
