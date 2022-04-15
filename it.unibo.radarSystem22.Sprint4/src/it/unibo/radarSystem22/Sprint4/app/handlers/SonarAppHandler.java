package it.unibo.radarSystem22.Sprint4.app.handlers;

import it.unibo.radarSystem22.Sprint4.app.interpreter.SonarAppInterpreter;
import it.unibo.radarSystem22.Sprint4.comm.interfaces.IAppInterpreter;
import it.unibo.radarSystem22.Sprint4.comm.interfaces.IAppMessage;
import it.unibo.radarSystem22.Sprint4.comm.interfaces.IAppMsgHandler;
import it.unibo.radarSystem22.Sprint4.comm.interfaces.Interaction;
import it.unibo.radarSystem22.Sprint4.comm.utils.AppMsgHandler;
import it.unibo.radarSystem22.domain.interfaces.ISonar;

public class SonarAppHandler extends AppMsgHandler{

private IAppInterpreter sonarInterp;
	
	public static IAppMsgHandler create(String name, ISonar sonar) {
		return new SonarAppHandler(name,sonar);
	}
	
	public SonarAppHandler(String name, ISonar sonar) {
		super(name);
		sonarInterp = new SonarAppInterpreter(sonar);
	}

	@Override
	public void elaborate( IAppMessage message, Interaction conn ){
		if( message.isRequest() ) {
			String answer = sonarInterp.elaborate(message);
			sendAnswerToClient( answer, conn );
		}
		else sonarInterp.elaborate(message);
	}
}
