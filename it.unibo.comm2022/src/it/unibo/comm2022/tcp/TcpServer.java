package it.unibo.comm2022.tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


import it.unibo.comm2022.interfaces.IAppMsgHandler;
import it.unibo.comm2022.interfaces.Interaction;


public class TcpServer extends Thread{
	protected boolean stop=true;
	private ServerSocket serversock;
	protected IAppMsgHandler userDefHandler;
	protected String name;

	
	public TcpServer(String name, int port, IAppMsgHandler userDefHandler) {
		super(name);
		this.userDefHandler=userDefHandler;
		try {
			serversock = new ServerSocket (port);
			serversock.setSoTimeout(600000);      //CommSystemConfig
		}catch(Exception e) {
			System.out.println("TcpServer | ERROR "+e.getMessage());
		}
	}
	
	public void activate() {
		if(stop) {
			stop=false;
			this.start();
		}
	}
	
	public void deactivate() {
		try{
			stop=true;
			serversock.close();
		}catch(Exception e) {
			System.out.println("ClosedTcpServer | ERROR "+e.getMessage());
		}
	}

	@Override
	public void run() {
		try {
			System.out.println(getName() + " | STARTING ... ");
			while( ! stop ) {
				Socket sock          = serversock.accept();
				System.out.println(getName() + " | accepted connection  " );
				Interaction conn = new TcpConnection(sock);
				new TcpAppMsgHandler( userDefHandler, conn );
			}
		}catch (Exception e) {
			System.out.println(getName() + " | probably socket closed: " + e.getMessage());
		}
	}
}
