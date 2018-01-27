package jaccob.scripts.base;

import org.powerbot.script.PollingScript;
import org.powerbot.script.rt4.ClientContext;

import jaccob.scripts.base.state.StateMachine;

public abstract class BaseScript<T extends BaseStateData> extends PollingScript<ClientContext> {
	protected T stateData;
	private StateMachine machine;
	private boolean usePolling;
	
	public BaseScript(jaccob.scripts.base.state.State<?> firstState, T stateData, boolean usePolling) {
		this.machine = new StateMachine(firstState);
		this.stateData = stateData;
		this.stateData.init(ctx);
		this.usePolling = usePolling;
	}
	
	public abstract void stateIndependentCode();
	
	@Override
	public void poll() {
		if (!usePolling) {
			while (!ctx.controller.isStopping()) {
				if (!ctx.game.loggedIn()) {
					ctx.controller.stop();
					return;
				}
				
				this.stateIndependentCode();
				
				if (ctx.controller.isSuspended()) {
					Thread.yield();
					continue;
				}
				
				machine.next(stateData);
			}
		} else {
			this.stateIndependentCode();
			
			machine.next(stateData);
		}
	}
}
