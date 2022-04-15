package it.unibo.radarSystem22.sprint3.handlers;

import it.unibo.comm2022.interfaces.IAppMsgHandler;
import it.unibo.comm2022.interfaces.Interaction;
import it.unibo.comm2022.utils.AppMsgHandler;
import it.unibo.radarSystem22.domain.interfaces.ISonar;
import it.unibo.radarSystem22.sprint3.interfaces.IAppInterpreter;
import it.unibo.radarSystem22.sprint3.interpreters.SonarAppInterpreter;

public class SonarAppHandler extends AppMsgHandler {
    private IAppInterpreter sonarInterp;

    public static IAppMsgHandler create(String name, ISonar sonar){
        return new SonarAppHandler(name,sonar);
    }

    public SonarAppHandler(String name, ISonar sonar){
        super(name);
        sonarInterp = new SonarAppInterpreter(sonar);
    }

    @Override
    public void elaborate(String s, Interaction conn) {
        if( s.equals("getDistance") || s.equals("isActive")) {
            sendMsgToClient( sonarInterp.elaborate(s), conn );
        }else{
            sonarInterp.elaborate(s);
        }
    }
}
