package it.unibo.radarSystem22.Sprint4.proxy;


import it.unibo.comm2022.Sprint4.interfaces.IAppMessage;
import it.unibo.comm2022.Sprint4.proxy.ProxyAsClient;
import it.unibo.comm2022.Sprint4.utils.AppMessage;
import it.unibo.comm2022.Sprint4.utils.CommUtils;
import it.unibo.comm2022.Sprint4.utils.ProtocolType;
import it.unibo.radarSystem22.domain.interfaces.ILed;


public class LedProxy extends ProxyAsClient implements ILed {
    protected static IAppMessage turnOnLed ;
    protected static IAppMessage turnOffLed;
    protected static IAppMessage getLedState;

    public LedProxy(String name, String host, String entry, ProtocolType protocol  ) {
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
