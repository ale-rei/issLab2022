package it.unibo.radarSystem22.Sprint4.proxy;


import it.unibo.comm2022.Sprint4.interfaces.IApplMessage;
import it.unibo.comm2022.Sprint4.proxy.ProxyAsClient;
import it.unibo.comm2022.Sprint4.utils.ApplMessage;
import it.unibo.comm2022.Sprint4.utils.ColorsOut;
import it.unibo.comm2022.Sprint4.utils.CommUtils;
import it.unibo.comm2022.Sprint4.utils.ProtocolType;
import it.unibo.radarSystem22.domain.interfaces.IDistance;
import it.unibo.radarSystem22.domain.interfaces.ISonar;
import it.unibo.radarSystem22.domain.utils.Distance;


public class SonarProxy extends ProxyAsClient implements ISonar{
	public static IApplMessage sonarActivate   ;
	public static    IApplMessage sonarDeactivate ;  
	public static    IApplMessage getDistance  ; 
	public static    IApplMessage isActive     ; 
  	
 	public SonarProxy( String name, String host, String entry, ProtocolType protocol ) {
		super( name,  host,  entry, protocol );
		sonarActivate   = CommUtils.buildDispatch(name,"cmd", "activate",   "sonar");
		sonarDeactivate = CommUtils.buildDispatch(name,"cmd", "deactivate", "sonar");
		getDistance     = CommUtils.buildRequest(name, "ask", "getDistance","sonar");
		isActive        = CommUtils.buildRequest(name, "ask", "isActive",   "sonar");
 	}
 	@Override
	public void activate() {
	     if( protocol == ProtocolType.tcp || protocol == ProtocolType.udp ) {
 			sendCommandOnConnection(sonarActivate.toString());	
	     }
	   //ALTRI PROTOCOLLI ...	
	}

	@Override
	public void deactivate() {
	     if( protocol == ProtocolType.tcp  || protocol == ProtocolType.udp  ) {
 			sendCommandOnConnection(sonarDeactivate.toString());		
	    	close();
	     }
	}

	@Override
	public IDistance getDistance() {
		String answer = "0";
	    if( protocol == ProtocolType.tcp || protocol == ProtocolType.udp  ) {
	    	String reply = sendRequestOnConnection(getDistance.toString());
	    	ColorsOut.outappl(name+" |  getDistance reply=" + reply, ColorsOut.GREEN );
	    	answer = new ApplMessage(reply).msgContent();
	    }
 		return new Distance( Integer.parseInt(answer) );
	}

	@Override
	public boolean isActive() {
		String answer = "";
	    if( protocol == ProtocolType.tcp || protocol == ProtocolType.udp  ) {
	    	String reply = sendRequestOnConnection( isActive.toString() );	    	
	    	ColorsOut.outappl(name+" |  isActive reply=" + reply, ColorsOut.GREEN );
	    	answer = new ApplMessage(reply).msgContent();
	    }
		return answer.equals( "true" );
	}
 	
 }



