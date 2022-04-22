package it.unibo.radarSystem22.Sprint4.interpreter;

import it.unibo.comm2022.Sprint4.interfaces.IAppInterpreter;
import it.unibo.comm2022.Sprint4.interfaces.IAppMessage;
import it.unibo.comm2022.Sprint4.utils.CommUtils;
import it.unibo.radarSystem22.domain.interfaces.ISonar;

public class SonarAppInterpreter implements IAppInterpreter {

    private ISonar sonar;

    public SonarAppInterpreter(ISonar sonar) {
        this.sonar=sonar;
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
        if (payload.equals("activate")) sonar.activate();
        else  if(payload.equals("deactivate")) sonar.deactivate();

    }

    private String elabRequest(IAppMessage message) {
        String answer  = "request_unknown";
        String payload = message.msgContent();
        if( payload.equals("getDistance")) {
            answer = ""+sonar.getDistance().getVal();
        }else if( payload.equals("isActive")) {
            answer = ""+sonar.isActive();
        }
        IAppMessage reply = CommUtils.prepareReply( message, answer);
        return reply.toString();
    }

}
