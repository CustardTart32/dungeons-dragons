package unsw.dungeon;

public class Treasure extends Entity implements Collectible, Subject {
	Observer observer;
	
	public Treasure (int x, int y) {
        super(x, y);
    }
	
	public void notifyObserver() {
		observer.update((Subject) this);
	}
	
	public void setObserver(Observer obs) {
		this.observer = obs;
	}
	
	
	public void onPickUp() {
		setVisible(false);
		
		if (getObserver() != null) {
			notifyObserver();
		}
	}

	public Observer getObserver() {
		return observer;
	}
	
	
}
