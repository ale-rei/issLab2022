package it.unibo.comm2022.interfaces;

import java.io.IOException;

public interface IAppMsgHandler {
	public String getName();
	public void elaborate( String message, Interaction conn );
	public void sendMsgToClient(String message, Interaction conn);
	public void sendAnswerToClient(String message,Interaction conn) throws IOException;
}

