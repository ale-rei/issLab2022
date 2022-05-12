package it.unibo.radarSystem22.Sprint4.handlers;

 
import it.unibo.comm2022.Sprint4.interfaces.IApplInterpreter;
import it.unibo.comm2022.Sprint4.interfaces.IApplMessage;
import it.unibo.comm2022.Sprint4.interfaces.IApplMsgHandler;
import it.unibo.comm2022.Sprint4.interfaces.Interaction2021;
import it.unibo.comm2022.Sprint4.utils.ApplMsgHandler;
import it.unibo.comm2022.Sprint4.utils.ColorsOut;
import it.unibo.radarSystem22.Sprint4.interpreter.SonarApplInterpreter;
import it.unibo.radarSystem22.domain.interfaces.ISonar;

 

public class SonarApplHandler extends ApplMsgHandler {
 
private IApplInterpreter sonarIntepr;

public static IApplMsgHandler create(String name, ISonar sonar) {
	return new SonarApplHandler(name, sonar); 
}
		public SonarApplHandler(String name, ISonar sonar) {
			super(name);
			sonarIntepr = new SonarApplInterpreter(sonar);
			ColorsOut.out(name+ " | SonarApplHandler CREATED with sonar= " + sonar, ColorsOut.MAGENTA);
	 	}
 
 		
 		@Override
		public void elaborate(IApplMessage message, Interaction2021 conn) {
 			ColorsOut.out(name+ " | elaborate " + message + " conn=" + conn, ColorsOut.MAGENTA);
 			if( message.isRequest() ) {
 				String answer = sonarIntepr.elaborate(message);
 				sendAnswerToClient( answer, conn );
   			}else sonarIntepr.elaborate(message);
 		}
}
