package it.unibo.comm2022.utils;

import it.unibo.comm2022.interfaces.IAppMsgHandler;
import it.unibo.comm2022.interfaces.Interaction;

import java.io.IOException;

public abstract class AppMsgHandler implements IAppMsgHandler{
	
	protected String name;

	public AppMsgHandler(String name) {
		this.name=name;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public abstract void elaborate (String message, Interaction conn);
	

	@Override
	public void sendMsgToClient(String message, Interaction conn) {
		try {
			conn.forward(message);
		}catch(Exception e) {
			System.out.println("SendMsgToClient | ERROR "+e.getMessage());
		}
	}

	@Override
	public void sendAnswerToClient(String reply, Interaction conn) throws IOException {
		try {
			conn.reply(reply);
		}catch(Exception e) {
			System.out.println("SendAnswerToClient | ERROR "+e.getMessage());
		}
	}

}
