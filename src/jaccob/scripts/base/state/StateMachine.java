package jaccob.scripts.base.state;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class StateMachine {
	private State start;
	private State current;
	private State last;
	
	private Stack<State> stack = new Stack<>();

	public StateMachine(State start) {
		this.start = start;
		stack.push(start);
	}
	
	public State getStart() {
		return start;
	}
	
	public State getCurrent() {
		return current;
	}
	
	public void next(StateData data) {
		if (!stack.isEmpty()) {
			last = stack.peek();
			if (last.start()) {
				stack.clear();
				stack.push(last);
			}
			
			current = last.update(data);
			
			if (current != null)
				stack.push(current);
			else
				stack.pop();
		}
	}
}
