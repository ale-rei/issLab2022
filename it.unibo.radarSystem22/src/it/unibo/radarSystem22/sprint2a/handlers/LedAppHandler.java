package it.unibo.radarSystem22.sprint2a.handlers;

import it.unibo.comm2022.interfaces.IAppMsgHandler;
import it.unibo.comm2022.interfaces.Interaction;
import it.unibo.comm2022.utils.AppMsgHandler;
import it.unibo.radarSystem22.domain.interfaces.ILed;


public class LedAppHandler extends AppMsgHandler {
    private ILed led;

    public static IAppMsgHandler create (String name, ILed led){
        return new LedAppHandler(name,led);
    }

    public LedAppHandler(String name, ILed led) {
        super(name);
        this.led = led;
    }

    @Override
    public void elaborate(String s, Interaction conn) {
        if(s.equals("getState")){
            this.sendMsgToClient(""+led.getState(),conn);
        }
        else if (s.equals("on")) {led.turnOn();}
        else if (s.equals("off")) {led.turnOff();}
    }
}
