package it.unibo.comm2022.Sprint4.tcp;



import it.unibo.comm2022.Sprint4.interfaces.IAppMsgHandler;
import it.unibo.comm2022.Sprint4.interfaces.Interaction;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer extends Thread{
    private boolean stop=true;
    protected IAppMsgHandler userDefHandler;
    private int port;
    private ServerSocket serversock;

    public TcpServer(String name, int port, IAppMsgHandler userDefHandler) {
        super(name);
        this.port=port;
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
        while(!stop) {
            try {
                System.out.println("TcpServerRun | START");
                Socket socket = serversock.accept();
                System.out.println("TcpServer | connection accepted");
                Interaction conn = new TcpConnection(socket);
                new TcpAppMessageHandler(userDefHandler,conn);
            } catch (IOException e) {
                System.out.println("TcpServerRun | ERROR "+e.getMessage());
            }
        }
    }
}
