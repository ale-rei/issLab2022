package it.unibo.radarSystem22.Sprint4.app.handlers;

import it.unibo.radarSystem22.Sprint4.app.interpreter.LedAppInterpreter;
import it.unibo.radarSystem22.Sprint4.comm.interfaces.IAppInterpreter;
import it.unibo.radarSystem22.Sprint4.comm.interfaces.IAppMessage;
import it.unibo.radarSystem22.Sprint4.comm.interfaces.IAppMsgHandler;
import it.unibo.radarSystem22.Sprint4.comm.interfaces.Interaction;
import it.unibo.radarSystem22.Sprint4.comm.utils.AppMsgHandler;
import it.unibo.radarSystem22.domain.interfaces.ILed;

public class LedAppHandler extends AppMsgHandler{

	private IAppInterpreter ledInterp;
	
	public static IAppMsgHandler create(String name, ILed led) {
		return new LedAppHandler(name,led);
	}
 
	public LedAppHandler(String name, ILed led) {
		super(name);
		ledInterp = new LedAppInterpreter(led) ;
 	}
	

	@Override
	public void elaborate(IAppMessage message, Interaction conn) {
		if(message.isRequest()) sendAnswerToClient(ledInterp.elaborate(message),conn);
		else ledInterp.elaborate(message);
	}

}
