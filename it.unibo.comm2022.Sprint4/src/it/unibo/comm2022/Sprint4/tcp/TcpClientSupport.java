package it.unibo.comm2022.Sprint4.tcp;



import it.unibo.comm2022.Sprint4.interfaces.Interaction;

import java.net.Socket;

public class TcpClientSupport {

    public static Interaction connect (String ip, int port, int num) {
        for(int i=0;i<=num;i++) {
            try {
                Socket socket = new Socket (ip,port);
                Interaction conn = new TcpConnection (socket);
                return conn;
            }catch(Exception e) {
                System.out.println("TcpConnect | ERROR "+e.getMessage());
            }

        }
        return null;
    }
}
