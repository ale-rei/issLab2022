package it.unibo.comm2022.Sprint4.utils;


import it.unibo.comm2022.Sprint4.interfaces.IAppMessage;
import it.unibo.comm2022.Sprint4.interfaces.IAppMsgHandler;
import it.unibo.comm2022.Sprint4.interfaces.Interaction;

public abstract class AppMsgHandler implements IAppMsgHandler {
protected String name;
   
 	public AppMsgHandler( String name  ) {
		this.name = name;
	}
 	
 	public String getName() {
		return name;
	}	 
   	
 	public void sendMsgToClient( String message, Interaction conn  ) {
		try {

			conn.forward( message );
		} catch (Exception e) {
 			System.out.println(name + " | ApplMsgHandler sendMsgToClient ERROR " + e.getMessage());
		}
 	} 

 	@Override
 	public void sendAnswerToClient( String reply, Interaction conn   ) {
  		try {
			conn.reply(reply);
		} catch (Exception e) {
			System.out.println(name + " | ApplMsgHandler sendAnswerToClient ERROR " + e.getMessage() );
		}
 	}

 	public abstract void elaborate(IAppMessage message, Interaction conn) ;
 	
}
