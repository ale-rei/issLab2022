package it.unibo.radarSystem22.Sprint4.handlers;


import it.unibo.comm2022.Sprint4.interfaces.IApplInterpreter;
import it.unibo.comm2022.Sprint4.interfaces.IApplMessage;
import it.unibo.comm2022.Sprint4.interfaces.IApplMsgHandler;
import it.unibo.comm2022.Sprint4.interfaces.Interaction2021;
import it.unibo.comm2022.Sprint4.utils.ApplMsgHandler;
import it.unibo.comm2022.Sprint4.utils.ColorsOut;
import it.unibo.radarSystem22.Sprint4.interpreter.LedApplInterpreter;
import it.unibo.radarSystem22.domain.interfaces.ILed;

public class LedApplHandler extends ApplMsgHandler {
private IApplInterpreter ledInterpr;

	public static IApplMsgHandler create(String name, ILed led) {
		return new LedApplHandler(name,led);
	}
 
	public LedApplHandler(String name, ILed led) {
		super(name);
		ledInterpr = new LedApplInterpreter(led) ;
 	}
	

 	
 	@Override
	public void elaborate(IApplMessage message, Interaction2021 conn) {
		ColorsOut.out(name + " | elaborate message=" + message + " conn=" + conn , ColorsOut.GREEN);
 		if( message.isRequest() ) 
 			sendAnswerToClient( ledInterpr.elaborate(message), conn );
 		else ledInterpr.elaborate(message);
	}

}
