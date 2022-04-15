package it.unibo.comm2022.udp.giannatempo;

import it.unibo.comm2022.interfaces.IAppMsgHandler;
import it.unibo.comm2022.interfaces.Interaction;


/*
 * Ente attivo per la ricezione di messaggi su una connessione Interaction2021
 */
public class UdpApplMessageHandler extends Thread{
private IAppMsgHandler handler ;
private Interaction conn;

public UdpApplMessageHandler(  IAppMsgHandler handler, Interaction conn ) {
		this.handler = handler;
		this.conn    = conn;
 		this.start();
	}
 	
	@Override 
	public void run() {
		String name = handler.getName();
		try {
			System.out.println( "UdpApplMessageHandler | STARTS with handler=" + name + " conn=" + conn);
			while( true ) {
			    String msg = conn.receiveMsg();
			    System.out.println(name + "  | UdpApplMessageHandler received:" + msg + " handler="+handler);
			    if( msg == null ) {
			    	conn.close();
			    	break;
			    } else{ handler.elaborate( msg, conn ); }
			}
			System.out.println(name + " | BYE"   );
		}catch( Exception e) {
			System.out.println( name + "  | ERROR:" + e.getMessage()  );
		}	
	}
}
