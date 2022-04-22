package it.unibo.radarSystem22.Sprint4.interpreter;

import it.unibo.comm2022.Sprint4.interfaces.IAppInterpreter;
import it.unibo.comm2022.Sprint4.interfaces.IAppMessage;
import it.unibo.comm2022.Sprint4.utils.CommUtils;
import it.unibo.radarSystem22.domain.interfaces.ILed;

public class LedAppInterpreter implements IAppInterpreter {

    private ILed led;

    public LedAppInterpreter(ILed led) {
        this.led=led;
    }

    @Override
    public String elaborate(IAppMessage message) {
        String answer = null;
        if(message.isRequest()) answer = elabRequest(message);
        else elabCommand(message);
        return answer;
    }

    private void elabCommand(IAppMessage message) {
        String payload = message.msgContent();
        if (payload.equals("on")) led.turnOn();
        else  if(payload.equals("off")) led.turnOff();

    }

    private String elabRequest(IAppMessage message) {
        String payload = message.msgContent();
        String answer = "request _unknown";
        if (payload.equals("getState")) answer = ""+led.getState();
        IAppMessage reply = CommUtils.prepareReply(message, answer);
        return reply.toString();
    }


}
