package it.unibo.radarSystem22.sprint2a.handlers;


import it.unibo.comm2022.interfaces.IAppMsgHandler;
import it.unibo.comm2022.interfaces.Interaction;
import it.unibo.comm2022.utils.AppMsgHandler;
import it.unibo.radarSystem22.domain.interfaces.ISonar;

public class SonarAppHandler extends AppMsgHandler {
    private ISonar sonar;

    public static IAppMsgHandler create (String name, ISonar sonar){
        return new SonarAppHandler(name, sonar);
    }

    public SonarAppHandler(String name, ISonar sonar) {
        super(name);
        this.sonar=sonar;
    }

    @Override
    public void elaborate(String s, Interaction conn) {
        if(s.equals("getDistance")){
            sendMsgToClient(""+sonar.getDistance().getVal(),conn);
        }
        else if (s.equals("isActive")){
            sendMsgToClient(""+sonar.isActive(),conn);
        }
        else if(s.equals("activate")){
            sonar.activate();
        }
        else if(s.equals("deactivate")){
            sonar.deactivate();
        }
    }
}
