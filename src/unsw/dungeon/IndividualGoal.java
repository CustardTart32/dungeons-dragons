package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

public class IndividualGoal implements Goal{
	String type;
	Observer obs;
	
	public IndividualGoal(String type) {
		this.type = type;
		this.obs = new GoalObserver();
	}
	
	public Boolean completed() {
		return obs.is_complete();
	}

	public String getType() {
		return type;
	}

	public Observer getObs() {
		return obs;
	}

	public void setObs(Observer obs) {
		this.obs = obs;
	}
	
	public void printGoal() {
		Observer observer = getObs();
		GoalObserver obs = (GoalObserver) observer;
		
		System.out.print(obs.getProgress() + "/" + obs.getNumber() + " " + getType() + "\n");
	}
	
	public List<String> showGoal() {
		Observer observer = getObs();
		GoalObserver obs = (GoalObserver) observer;
		
		List<String> str_goal = new ArrayList<>();
		
		str_goal.add(obs.getProgress() + "/" + obs.getNumber() + " " + getType() + "\n");
		
		return str_goal;
		
	}
}
