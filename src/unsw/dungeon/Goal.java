package unsw.dungeon;

import java.util.List;

public interface Goal {
	public Boolean completed();
	public void printGoal();
	public List<String> showGoal();
}
