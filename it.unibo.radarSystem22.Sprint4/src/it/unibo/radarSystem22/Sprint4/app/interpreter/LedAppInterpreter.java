package it.unibo.radarSystem22.Sprint4.app.interpreter;

import it.unibo.radarSystem22.Sprint4.comm.interfaces.IAppInterpreter;
import it.unibo.radarSystem22.Sprint4.comm.interfaces.IAppMessage;
import it.unibo.radarSystem22.Sprint4.comm.utils.CommUtils;
import it.unibo.radarSystem22.domain.interfaces.ILed;

public class LedAppInterpreter implements IAppInterpreter{

	private ILed led;
	
	public LedAppInterpreter(ILed led) {
		this.led=led;
	}
	
	@Override
	public String elaborate(IAppMessage message) {
		String answer = null;
		if(message.isRequest()) answer = elabRequest(message);
		else elabCommand(message);
		return answer;
	}

	private void elabCommand(IAppMessage message) {
		String payload = message.msgContent();
		if (payload.equals("on")) led.turnOn();
		else  if(payload.equals("off")) led.turnOff();
		
	}

	private String elabRequest(IAppMessage message) {
		String payload = message.msgContent();
		String answer = "request _unknown";
		if (payload.equals("getState")) answer = ""+led.getState();
		IAppMessage reply = CommUtils.prepareReply(message, answer);
		return reply.toString();
	}

	
}
