package unsw.dungeon;

public interface Observer {
	public Boolean is_complete();
	public void update(Subject subject);
	public void addSubject(Subject subject);
}
