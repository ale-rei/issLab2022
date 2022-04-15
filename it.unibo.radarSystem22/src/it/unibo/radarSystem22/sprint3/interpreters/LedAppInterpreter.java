package it.unibo.radarSystem22.sprint3.interpreters;

import it.unibo.radarSystem22.domain.interfaces.ILed;
import it.unibo.radarSystem22.sprint3.interfaces.IAppInterpreter;

public class LedAppInterpreter implements IAppInterpreter {
    private ILed led;

    public LedAppInterpreter(ILed led){
        this.led=led;
    }
    @Override
    public String elaborate(String message) {
        if(message.equals("getState")){
            return ""+led.getState();
        }
        else if(message.equals("on")){
            led.turnOn();
        }
        else if(message.equals("off")){
            led.turnOff();
        }
        return message+" done";
    }
}
