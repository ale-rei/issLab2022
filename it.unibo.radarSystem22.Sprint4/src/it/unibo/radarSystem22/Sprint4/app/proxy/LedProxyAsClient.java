package it.unibo.radarSystem22.Sprint4.app.proxy;

import it.unibo.radarSystem22.Sprint4.comm.interfaces.IAppMessage;
import it.unibo.radarSystem22.Sprint4.comm.proxy.ProxyAsClient;
import it.unibo.radarSystem22.Sprint4.comm.utils.AppMessage;
import it.unibo.radarSystem22.Sprint4.comm.utils.CommUtils;
import it.unibo.radarSystem22.Sprint4.comm.utils.ProtocolType;
import it.unibo.radarSystem22.domain.interfaces.ILed;

public class LedProxyAsClient extends ProxyAsClient implements ILed{

	public static IAppMessage turnOnLed;
	public static IAppMessage turnOffLed;
	public static IAppMessage getLedState;
	public LedProxyAsClient(String name, String host, String entry, ProtocolType protocol) {
		super(name, host, entry, protocol);
		turnOnLed   = CommUtils.buildDispatch(name,"cmd", "on", "led");
	    turnOffLed  = CommUtils.buildDispatch(name,"cmd", "off", "led");
	    getLedState = CommUtils.buildRequest(name, "req", "getState", "led");
	}

	@Override
	public void turnOn() {
		
		if( protocol == ProtocolType.tcp || protocol == ProtocolType.udp) {
			sendCommandOnConnection(turnOnLed.toString());
		}
	}

	@Override
	public void turnOff() {
		
		if( protocol == ProtocolType.tcp || protocol == ProtocolType.udp) {
			sendCommandOnConnection(turnOnLed.toString());
		}
	}

	@Override
	public boolean getState() {
		
		String answer="";
        if( protocol == ProtocolType.tcp || protocol == ProtocolType.udp) {
        	String reply = sendRequestOnConnection( getLedState.toString() );
            answer = new AppMessage(reply).msgContent();
        }
        return answer.equals("true");
	}

}
