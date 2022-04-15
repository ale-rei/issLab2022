package it.unibo.radarSystem22.sprint2.proxy;

import it.unibo.comm2022.proxy.ProxyAsClient;
import it.unibo.comm2022.utils.ProtocolType;
import it.unibo.radarSystem22.domain.interfaces.IRadarDisplay;

public class RadarGuiProxyAsClient extends ProxyAsClient implements IRadarDisplay{

	public RadarGuiProxyAsClient(String name, String host, String entry, ProtocolType protocol) {
		super(name, host, entry, protocol);
		
	}
	
	@Override //from IRadarDisplay
	public int getCurDistance() {
		String answer = sendRequestOnConnection("getCurDistance");
		return Integer.parseInt(answer);
	}

	@Override //from IRadarDisplay
	public void update(String distance, String angle) {
		String msg = "{ \"distance\" : D , \"angle\" : A }".replace("D",distance).replace("A",angle);
		try {
			sendCommandOnConnection(msg);
		} catch (Exception e) {
 			System.out.println("RadarProxyUpdate | ERROR "+e.getMessage());
		}   
	}

}
