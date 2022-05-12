package it.unibo.radarSystem22.Sprint4.proxy;

import it.unibo.comm2022.Sprint4.interfaces.IApplMessage;
import it.unibo.comm2022.Sprint4.proxy.ProxyAsClient;
import it.unibo.comm2022.Sprint4.utils.ApplMessage;
import it.unibo.comm2022.Sprint4.utils.ColorsOut;
import it.unibo.comm2022.Sprint4.utils.CommUtils;
import it.unibo.comm2022.Sprint4.utils.ProtocolType;
import it.unibo.radarSystem22.domain.interfaces.ILed;

/*
 * Adapter for the output device  Led
 */
public class LedProxy extends ProxyAsClient implements ILed {
	protected static IApplMessage turnOnLed ;
	protected static IApplMessage turnOffLed;  
	protected static IApplMessage getLedState;  

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
	     //ALTRI PROTOCOLLI ...	     
  	}

	@Override
	public void turnOff() {   
	    if( protocol == ProtocolType.tcp  ||  protocol == ProtocolType.udp ) {
	    	sendCommandOnConnection( turnOffLed.toString());
	    }
	   //ALTRI PROTOCOLLI ...	 
 	}

	@Override
	public boolean getState() {   
		String answer = ""; 
	    if( protocol == ProtocolType.tcp ||  protocol == ProtocolType.udp   ) {
	    	String reply = sendRequestOnConnection( getLedState.toString() );	    	
	    	ColorsOut.outappl(name+" |  getState reply=" + reply, ColorsOut.GREEN );
	    	answer = new ApplMessage(reply).msgContent();
	    }	    
	    //ALTRI PROTOCOLLI ...	 
		return answer.equals("true");
	}
}
