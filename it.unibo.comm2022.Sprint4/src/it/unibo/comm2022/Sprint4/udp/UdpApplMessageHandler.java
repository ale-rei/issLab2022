package it.unibo.comm2022.Sprint4.udp;


import it.unibo.comm2022.Sprint4.interfaces.IApplMessage;
import it.unibo.comm2022.Sprint4.interfaces.IApplMsgHandler;
import it.unibo.comm2022.Sprint4.interfaces.Interaction2021;
import it.unibo.comm2022.Sprint4.utils.ApplMessage;
import it.unibo.comm2022.Sprint4.utils.ColorsOut;

/*
 * Ente attivo per la ricezione di messaggi su una connessione Interaction2021
 */
public class UdpApplMessageHandler extends Thread{
private IApplMsgHandler handler ;
private Interaction2021 conn;

public UdpApplMessageHandler(  IApplMsgHandler handler, Interaction2021 conn ) {
		this.handler = handler;
		this.conn    = conn;
		ColorsOut.outappl("UdpApplMessageHandler | CREATED for conn=" + conn, ColorsOut.BLUE   );
 		this.start();
	}
 	
	@Override 
	public void run() {
		String name = handler.getName();
		try {
			ColorsOut.out( "UdpApplMessageHandler | STARTS with handler=" + name + " conn=" + conn, ColorsOut.BLUE );
			while( true ) {
				//ColorsOut.out(name + " | waits for message  ...");
			    String msg = conn.receiveMsg();
			    ColorsOut.out("UdpApplMessageHandler received:" + msg + " handler="+handler, ColorsOut.BLUE );
			    if( msg == null || msg.equals(UdpConnection.closeMsg) ) {
			    	conn.close();
			    	break;
			    } else{ 
			    	IApplMessage m = new ApplMessage(msg);
			    	handler.elaborate( m, conn ); 
			    }
			}
			ColorsOut.out("UdpApplMessageHandler | BYE"   );
		}catch( Exception e) {
			ColorsOut.outerr( "UdpApplMessageHandler  | ERROR:" + e.getMessage()  );
		}	
	}
}
