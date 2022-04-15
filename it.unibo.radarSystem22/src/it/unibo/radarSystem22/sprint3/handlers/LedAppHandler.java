package it.unibo.radarSystem22.sprint3.handlers;

import it.unibo.comm2022.interfaces.IAppMsgHandler;
import it.unibo.comm2022.interfaces.Interaction;
import it.unibo.comm2022.utils.AppMsgHandler;
import it.unibo.radarSystem22.domain.interfaces.ILed;
import it.unibo.radarSystem22.sprint3.interfaces.IAppInterpreter;
import it.unibo.radarSystem22.sprint3.interpreters.LedAppInterpreter;

public class LedAppHandler extends AppMsgHandler {
    private IAppInterpreter ledInterp;

    public static IAppMsgHandler create(String name, ILed led){
        return new LedAppHandler(name,led);
    }

    public LedAppHandler (String name, ILed led){
        super(name);
        ledInterp = new LedAppInterpreter(led);
    }

    @Override
    public void elaborate(String s, Interaction conn) {
        if(s.equals("getState")){
            sendMsgToClient(ledInterp.elaborate(s),conn);
        }
        else{
            ledInterp.elaborate(s);
        }
    }
}
