package it.unibo.radarSystem22.Sprint4.interpreter;
import it.unibo.comm2022.Sprint4.interfaces.IApplInterpreter;
import it.unibo.comm2022.Sprint4.interfaces.IApplMessage;
import it.unibo.comm2022.Sprint4.utils.ColorsOut;
import it.unibo.comm2022.Sprint4.utils.CommUtils;
import it.unibo.radarSystem22.domain.interfaces.ISonar;


public class SonarApplInterpreter implements IApplInterpreter {
private	ISonar sonar;

	public SonarApplInterpreter(ISonar sonar) {
		this.sonar = sonar;
	}

 
	@Override
	public String elaborate(IApplMessage message) {
		//ColorsOut.out("SonarApplInterpreter | elaborate " + message, ColorsOut.BLUE);
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
 		if( payload.equals("activate")) sonar.activate();
		else if( payload.equals("deactivate")) sonar.deactivate();
 	}
 	protected String elabRequest( IApplMessage message ) {
 		String answer  = "request_unknown";
		String payload = message.msgContent();
		if( payload.equals("getDistance")) {
			//ColorsOut.out(name+ " | elaborate getDistance="  , ColorsOut.BLUE);
			answer = ""+sonar.getDistance().getVal();
 		}else if( payload.equals("isActive")) {
			ColorsOut.out("SonarApplInterpreter | isActive " + sonar.isActive(), ColorsOut.BLUE);
			answer = ""+sonar.isActive();
 		}
        IApplMessage reply = CommUtils.prepareReply( message, answer);
		return reply.toString();
	}
}
