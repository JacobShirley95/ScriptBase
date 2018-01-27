package jaccob.scripts.base.state;

public abstract class State<T extends StateData> {
	public State() {
	}
	
	public boolean start() {
		return false;
	}
	
	public abstract State<T> update(T data);
}
