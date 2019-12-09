	package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

public class GoalObserver implements Observer {
	List<Subject> subjects;
	List<Boolean> satisfied;
	
	public GoalObserver() {
		subjects = new ArrayList<Subject> ();
		satisfied = new ArrayList<Boolean> ();
	}

	//This logic fails in the instance where there are multiple exits involved 
	public Boolean is_complete() {
		return !satisfied.contains(false);
	}
	
	public int getProgress() {
		int counter = 0;
		
		for (Boolean bool: getSatisfied()) {
			if (bool == true) {
				counter++;
			}
		}
		
		return counter;
	}
	
	public int getNumber() {
		return getSatisfied().size();
	}

	public void update(Subject subject) {
		int index = getSubjects().indexOf(subject);
		
		if (index != -1) {
			satisfied.set(index, !getSatisfied().get(index));
		}
		
	}

	public void addSubject(Subject subject) {
		getSubjects().add(subject);
		getSatisfied().add(false);
		
	}

	public List<Subject> getSubjects() {
		return subjects;
	}

	public List<Boolean> getSatisfied() {
		return satisfied;
	}
	
}
