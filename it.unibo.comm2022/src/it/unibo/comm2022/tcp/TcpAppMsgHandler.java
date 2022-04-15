package it.unibo.comm2022.tcp;

import it.unibo.comm2022.interfaces.IAppMsgHandler;
import it.unibo.comm2022.interfaces.Interaction;

public class TcpAppMsgHandler extends Thread{
	private IAppMsgHandler handler;
	private Interaction conn;
	
	public TcpAppMsgHandler (IAppMsgHandler handler,Interaction conn) {
		this.handler=handler;
		this.conn=conn;
		this.start();
	}
	
	@Override
	public void run() {
		String name = handler.getName();
		try {
			while(true) {
				String message = conn.receiveMsg();
				if(message==null) {
					conn.close();
					break;
				}
				else {
					handler.elaborate(message, conn);
				}
			}
			System.out.println(name + " | BYE"   );
		}catch(Exception e) {
			System.out.println("TcpAppMsgHandlerRun | ERROR "+e.getMessage());
		}
	}
}
