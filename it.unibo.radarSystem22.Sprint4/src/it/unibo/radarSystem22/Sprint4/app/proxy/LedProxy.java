package it.unibo.radarSystem22.Sprint4.app.proxy;

import it.unibo.radarSystem22.Sprint4.comm.interfaces.IAppMessage;
import it.unibo.radarSystem22.Sprint4.comm.proxy.ProxyAsClient;
import it.unibo.radarSystem22.Sprint4.comm.utils.AppMessage;
import it.unibo.radarSystem22.Sprint4.comm.utils.CommUtils;
import it.unibo.radarSystem22.Sprint4.comm.utils.ProtocolType;
import it.unibo.radarSystem22.domain.interfaces.ILed;


/*
 * Adapter for the output device  Led
 */
public class LedProxy extends ProxyAsClient implements ILed {
	protected static IAppMessage turnOnLed ;
	protected static IAppMessage turnOffLed;
	protected static IAppMessage getLedState;

	public LedProxy( String name, String host, String entry, ProtocolType protocol  ) {
		super(name,host,entry, protocol);
   	    turnOnLed   = CommUtils.buildDispatch(name,"cmd", "on",      "led");
   	    turnOffLed  = CommUtils.buildDispatch(name,"cmd", "off",     "led");
   	    getLedState = CommUtils.buildRequest(name, "ask", "getState","led");
	}

	@Override
	public void turnOn() { 
	     if( protocol == ProtocolType.tcp  ||  protocol == ProtocolType.udp) {
	         sendCommandOnConnection(turnOnLed.toString());
	     }

  	}

	@Override
	public void turnOff() {   
	    if( protocol == ProtocolType.tcp  ||  protocol == ProtocolType.udp ) {
	    	sendCommandOnConnection( turnOffLed.toString());
	    }

 	}

	@Override
	public boolean getState() {   
		String answer = ""; 
	    if( protocol == ProtocolType.tcp ||  protocol == ProtocolType.udp   ) {
	    	String reply = sendRequestOnConnection( getLedState.toString() );	    	
	    	System.out.println(name+" |  getState reply=" + reply);
	    	answer = new AppMessage(reply).msgContent();
	    }	    

		return answer.equals("true");
	}
}
