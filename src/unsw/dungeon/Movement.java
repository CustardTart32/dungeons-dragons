package unsw.dungeon;

import java.util.List;

public interface Movement {
	public void calculateMove(List<String> directions, int x_distance, int y_distance);
}
