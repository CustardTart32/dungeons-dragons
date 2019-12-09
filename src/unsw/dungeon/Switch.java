package unsw.dungeon;

public class Switch extends Entity implements Subject {
	Observer observer;
	private Boolean state;
	
	public Switch (int x, int y) {
        super(x, y);
        this.state = false;
    }
	
	public void updateSwitch() {
		if (state == false) {
			this.state = true;
		} else {this.state = false; }
		System.out.println("State is now: " + state);
		if (getObserver() != null) {
			notifyObserver();
		}
	}
	

	public Boolean getState() {
		return state;
	}
	
	public void notifyObserver() { 
		if (observer != null) {
			observer.update((Subject) this);
		}
	}
	
	public void setObserver(Observer obs) {
		this.observer = obs;
	}
	
	public Observer getObserver() {
		return observer;
	}
	
}
