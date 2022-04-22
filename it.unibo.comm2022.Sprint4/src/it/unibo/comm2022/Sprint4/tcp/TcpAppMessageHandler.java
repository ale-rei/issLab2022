package it.unibo.comm2022.Sprint4.tcp;


import it.unibo.comm2022.Sprint4.interfaces.IAppMessage;
import it.unibo.comm2022.Sprint4.interfaces.IAppMsgHandler;
import it.unibo.comm2022.Sprint4.interfaces.Interaction;
import it.unibo.comm2022.Sprint4.utils.AppMessage;

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
           System.out.println("TcpApplMessageHandler | STARTS with handler=" + name + " conn=" + conn );
            while( true ) {
                String msg = conn.receiveMsg();
                System.out.println(name + "  | TcpApplMessageHandler received:" + msg + " handler="+handler );
                if( msg == null ) {
                    conn.close();
                    break;
                }
                else{
                    IAppMessage m = new AppMessage(msg);
                    handler.elaborate( m, conn );
                }
            }
            System.out.println(name + " | BYE"   );
        }catch( Exception e) {
            System.out.println( name + "  | ERROR:" + e.getMessage()  );
        }
    }
}


