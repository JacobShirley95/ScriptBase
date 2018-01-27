package jaccob.scripts.base;

import org.powerbot.script.rt4.ClientContext;

import jaccob.scripts.base.callback.Callables;
import jaccob.scripts.base.state.StateData;

public class BaseStateData extends StateData {
	public JaccobMethods methods;
	public Callables callables;

	@Override
	public void init(ClientContext ctx) {
		super.init(ctx);
		
		methods = new JaccobMethods(ctx);
		callables = new Callables(ctx);
	}
}
