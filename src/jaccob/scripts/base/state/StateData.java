package jaccob.scripts.base.state;

import java.util.HashMap;
import java.util.Map;

import org.powerbot.script.rt4.ClientAccessor;
import org.powerbot.script.rt4.ClientContext;

public abstract class StateData  {
	public Map<Object, Object> items = new HashMap<Object, Object>();
	public ClientContext ctx = null;
	
	public StateData() {
	}
	
	public void init(ClientContext ctx) {
		this.ctx = ctx;
	}
}
