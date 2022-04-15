package it.unibo.radarSystem22.Sprint4.comm.tcp;

import it.unibo.radarSystem22.Sprint4.comm.interfaces.IAppMessage;
import it.unibo.radarSystem22.Sprint4.comm.interfaces.IAppMsgHandler;
import it.unibo.radarSystem22.Sprint4.comm.interfaces.Interaction;
import it.unibo.radarSystem22.Sprint4.comm.utils.AppMessage;

public class TcpAppMessageHandler extends Thread{
    private IAppMsgHandler handler ;
    private Interaction conn;

    public TcpAppMessageHandler (IAppMsgHandler handler,Interaction conn) {
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
                    IAppMessage m = new AppMessage(message);
                    handler.elaborate(m, conn);
                }
            }
        }catch(Exception e) {
            System.out.println("TcpAppMsgHandlerRun | ERROR "+e.getMessage());
        }
    }
}

