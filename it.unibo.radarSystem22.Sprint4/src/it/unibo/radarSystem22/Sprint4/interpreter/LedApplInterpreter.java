package it.unibo.radarSystem22.Sprint4.interpreter;

import it.unibo.comm2022.Sprint4.interfaces.IApplInterpreter;
import it.unibo.comm2022.Sprint4.interfaces.IApplMessage;
import it.unibo.comm2022.Sprint4.utils.CommUtils;
import it.unibo.radarSystem22.domain.interfaces.ILed;


/*
 * TODO: il Led dovrebbe essere injected con un metodo o una annotation
 */
public class LedApplInterpreter implements IApplInterpreter {
private ILed led;
 
	public LedApplInterpreter(  ILed led) {
 		this.led = led;
	}

 	@Override
    public String elaborate( IApplMessage message ) {
 	 String answer = null; //no answer
      if(  message.isRequest() ) {
    	  answer = elabRequest(message);
      }else { //command => no answer
          elabCommand(message);
      }
  	  return answer; 
    }   
 	
 	protected void elabCommand( IApplMessage message ) {
 		String payload = message.msgContent();
	 	if( payload.equals("on"))   led.turnOn();
	 	else if( payload.equals("off") ) led.turnOff();			
 	}
 	
 	protected String elabRequest( IApplMessage message ) {
 	    String payload = message.msgContent();
 	    String answer  = "request_unknown";
        if(payload.equals("getState") ) {
            answer = ""+led.getState();
        }
        IApplMessage reply = CommUtils.prepareReply( message, answer);
        return (reply.toString() ); //msg(...)
 	}
}