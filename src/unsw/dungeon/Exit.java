package unsw.dungeon;

public class Exit extends Entity implements Subject {
	Observer obs;
	Boolean entered;
		
	public Exit(int x, int y) {
		super(x, y);
		this.entered = false;
		// TODO Auto-generated constructor stub
	}

	public Boolean getEntered() {
		return entered;
	}

	public void setEntered(Boolean entered) {
		this.entered = entered;
	}

	public void notifyObserver() {
		if (getObs() != null) {
			obs.update((Subject) this);
		}
	}
	
	public void hasEntered() {
		if (getEntered() == false && getObs() != null) {
			notifyObserver();
			setEntered(true);
		}
	}
	
	public void hasLeft() {
		if (getEntered() == true && getObs() != null) {
			notifyObserver();
			setEntered(false);	
		}
	}

	@Override
	public void setObserver(Observer obs) {
		this.obs = obs;
	}

	public Observer getObs() {
		return obs;
	}
	
	

}
