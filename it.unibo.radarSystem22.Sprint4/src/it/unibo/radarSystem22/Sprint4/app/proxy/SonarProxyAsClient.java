package it.unibo.radarSystem22.Sprint4.app.proxy;

import it.unibo.radarSystem22.Sprint4.comm.interfaces.IAppMessage;
import it.unibo.radarSystem22.Sprint4.comm.proxy.ProxyAsClient;
import it.unibo.radarSystem22.Sprint4.comm.utils.ProtocolType;
import it.unibo.radarSystem22.domain.interfaces.IDistance;
import it.unibo.radarSystem22.domain.interfaces.ISonar;
import it.unibo.radarSystem22.domain.utils.Distance;
import it.unibo.radarSystem22.Sprint4.comm.utils.AppMessage;
import it.unibo.radarSystem22.Sprint4.comm.utils.CommUtils;

public class SonarProxyAsClient extends ProxyAsClient implements ISonar{

	public static IAppMessage sonarActivate;
	public static IAppMessage sonarDeactivate;
	public static IAppMessage getDistance;
	public static IAppMessage isActive;
	
	public SonarProxyAsClient(String name, String host, String entry, ProtocolType protocol) {
		super(name, host, entry, protocol);
		sonarActivate   = CommUtils.buildDispatch(name,"cmd", "activate", "sonar");
		sonarDeactivate = CommUtils.buildDispatch(name,"cmd", "deactivate","sonar");
		getDistance     = CommUtils.buildRequest(name, "req", "getDistance", "sonar");
		isActive        = CommUtils.buildRequest(name, "req", "isActive", "sonar");
	}

	@Override
	public void activate() {
		if( protocol == ProtocolType.tcp || protocol == ProtocolType.udp) {
			sendCommandOnConnection(sonarActivate.toString());
		}
	}

	@Override
	public void deactivate() {
		if( protocol == ProtocolType.tcp || protocol == ProtocolType.udp) {
			sendCommandOnConnection(sonarDeactivate.toString());
			close();
		}
		
	}

	@Override
	public IDistance getDistance() {
		String answer = "0";
	    if( protocol == ProtocolType.tcp || protocol == ProtocolType.udp  ) {
	    	String reply = sendRequestOnConnection(getDistance.toString());
	    	System.out.println(name+" |  getDistance reply=" + reply );
	    	answer = new AppMessage(reply).msgContent();
	    }
 		return new Distance( Integer.parseInt(answer) );
	}

	@Override
	public boolean isActive() {
		String answer = "";
		if (protocol == ProtocolType.tcp || protocol == ProtocolType.udp) {
			String reply = sendRequestOnConnection(isActive.toString());
			answer = new AppMessage(reply).msgContent();
		}
		return answer.equals("true");
	}

}
