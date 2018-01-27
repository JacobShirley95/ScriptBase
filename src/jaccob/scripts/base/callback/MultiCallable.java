package jaccob.scripts.base.callback;

import java.util.concurrent.Callable;

public class MultiCallable implements Callable<Boolean> {
	private int result = -1;
	private Callable<Boolean>[] callables;

	public MultiCallable(Callable<Boolean>... callables) {
		this.callables = callables;
	}
	
	@Override
	public Boolean call() throws Exception {
		for (int i = 0; i < callables.length; i++) {
			if (callables[i].call()) {
				result = i;
				return true;
			}
		}
		
		return false;
	}
	
	public int getResult() {
		return result;
	}
}
