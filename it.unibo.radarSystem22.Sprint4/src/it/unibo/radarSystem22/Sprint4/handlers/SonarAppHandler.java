package it.unibo.radarSystem22.Sprint4.handlers;


import it.unibo.comm2022.Sprint4.interfaces.IAppInterpreter;
import it.unibo.comm2022.Sprint4.interfaces.IAppMessage;
import it.unibo.comm2022.Sprint4.interfaces.IAppMsgHandler;
import it.unibo.comm2022.Sprint4.interfaces.Interaction;
import it.unibo.comm2022.Sprint4.utils.AppMsgHandler;
import it.unibo.radarSystem22.Sprint4.interpreter.SonarAppInterpreter;
import it.unibo.radarSystem22.domain.interfaces.ISonar;

public class SonarAppHandler extends AppMsgHandler {

private IAppInterpreter sonarInterp;
	
	public static IAppMsgHandler create(String name, ISonar sonar) {
		return new SonarAppHandler(name,sonar);
	}
	
	public SonarAppHandler(String name, ISonar sonar) {
		super(name);
		sonarInterp = new SonarAppInterpreter(sonar);
	}

	@Override
	public void elaborate(IAppMessage message, Interaction conn ){
		if( message.isRequest() ) {
			String answer = sonarInterp.elaborate(message);
			sendAnswerToClient( answer, conn );
		}
		else sonarInterp.elaborate(message);
	}
}
