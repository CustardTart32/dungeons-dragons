package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

public class CompositeGoal implements Goal{
	String type;
	Goal goalA;
	Goal goalB;

	public CompositeGoal(String type) {
		this.type = type;
	}
	
	
	public Boolean completed() {
		if (type.equals("AND")) {
			return goalA.completed() && goalB.completed();
		}
		else {
			return goalA.completed() || goalB.completed();
 		}
	}


	public void setGoalA(Goal goal_a) {
		this.goalA = goal_a;
	}


	public void setGoalB(Goal goal_b) {
		this.goalB = goal_b;
	}
	
	public String getType() {
		return type;
	}


	public void printGoal() {
		System.out.print(getType() + "\n");	
		
		goalA.printGoal();
		goalB.printGoal();
	}
	
	public List<String> showGoal() {
		List<String> str_goal = new ArrayList<String>();
		str_goal.addAll(goalA.showGoal());
		str_goal.addAll(goalB.showGoal());
		return str_goal;
	}

	public Goal getGoalA() {
		return goalA;
	}


	public Goal getGoalB() {
		return goalB;
	}
	
	
}
