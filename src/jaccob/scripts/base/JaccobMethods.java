package jaccob.scripts.base;

import java.util.concurrent.Callable;

import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;

import jaccob.scripts.base.callback.MultiCallable;
import jaccob.scripts.base.interaction.Interaction;

public class JaccobMethods {
	private ClientContext ctx;
	
	public JaccobMethods(ClientContext ctx) {
		this.ctx = ctx;
	}
	
	public static final int randomRange(int[] minAndMax) {
		return (int)(minAndMax[0] + (Math.random() * (minAndMax[1] - minAndMax[0])));
	}
	
	public static final int getRandomAngle(int[] yaws) {
		int r = randomRange(yaws);
		if (r < 0)
			r += 360;
		return r;
	}
	
	public final boolean checkRun(int minPercent) {
		if (ctx.movement.energyLevel() > minPercent) {
			return ctx.movement.running(true);
		}
		return ctx.movement.running();
	}
	
	public final int invMoney() {
		return ctx.inventory.select().id(995).count(true);
	}
	
	public static final int wait(int duration, int freq, Callable<Boolean>... callables) {
		MultiCallable mC = new MultiCallable(callables);
		
		if (Condition.wait(mC, duration, freq)) {
			return mC.getResult();
		}
		
		return -1;
	}
	
	public final int getCofferAmount() {
		return ctx.varpbits.varpbit(795) / 2;
	}
	
	public final int wait(Callable<Boolean>... callables) {
		return wait(50, 100, callables);
	}
	
	public final boolean waitTillReasonableStop(final int dist, Interaction interaction) {
		Condition.sleep(500);
		return Condition.wait(new Callable<Boolean>() {
			@Override
			public Boolean call() throws Exception {
				if (interaction != null)
					interaction.prepare();
				return distanceToDest() < dist;
			}
		}, 100, 60);
	}
	
	public final int distanceToDest() {
		return ctx.movement.distance(ctx.movement.destination());
	}
	
	public static final int tryUntil(int tries, Callable<Boolean>... callables) {
		try {
			for (int t = 0; t < tries; t++) {
				int i = 0;
				for (Callable<Boolean> cb : callables) {
					if (cb.call())
						return i;
					i++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return -1;
	}
}
