package it.unibo.radarSystem22.sprint2a.proxy;

import it.unibo.comm2022.proxy.ProxyAsClient;
import it.unibo.comm2022.utils.ProtocolType;
import it.unibo.radarSystem22.domain.interfaces.ILed;

public class LedProxyAsClient extends ProxyAsClient implements ILed {

    public LedProxyAsClient (String name, String host, String entry, ProtocolType protocol){
        super(name,host,entry,protocol);
    }

    @Override //from ILed
    public void turnOn() {
        sendCommandOnConnection("on");
    }

    @Override //from ILed
    public void turnOff() {
        sendCommandOnConnection("off");
    }

    @Override //from ILed
    public boolean getState() {
        String answer = sendRequestOnConnection("getState");
        return answer.equals("true");
    }
}
