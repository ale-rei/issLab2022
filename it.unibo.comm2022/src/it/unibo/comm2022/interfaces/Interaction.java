package it.unibo.comm2022.interfaces;

import java.io.IOException;

public interface Interaction {
	
	public void forward(  String msg ) throws IOException, Exception;
	public String request(  String msg ) throws IOException, Exception;
	public String receiveMsg( ) throws IOException;
	public void reply(  String msg ) throws IOException, Exception;
	public void close( ) throws IOException;
}
