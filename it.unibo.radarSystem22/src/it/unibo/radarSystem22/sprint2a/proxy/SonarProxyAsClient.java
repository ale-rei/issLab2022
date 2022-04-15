package it.unibo.radarSystem22.sprint2a.proxy;

import it.unibo.comm2022.proxy.ProxyAsClient;
import it.unibo.comm2022.utils.ProtocolType;
import it.unibo.radarSystem22.domain.interfaces.IDistance;
import it.unibo.radarSystem22.domain.interfaces.ISonar;
import it.unibo.radarSystem22.domain.utils.Distance;

public class SonarProxyAsClient extends ProxyAsClient implements ISonar {

    public SonarProxyAsClient(String name, String host, String entry, ProtocolType protocol){
        super (name, host,entry, protocol);
    }

    @Override
    public void activate() {
        sendCommandOnConnection("activate");
    }

    @Override
    public void deactivate() {
        sendCommandOnConnection("deactivate");
    }

    @Override
    public IDistance getDistance() {
        String answer = sendRequestOnConnection("getDistance");
        return new Distance(Integer.parseInt(answer));
    }

    @Override
    public boolean isActive() {
        String answer = sendRequestOnConnection("isActive");
        return answer.equals("true");
    }
}
