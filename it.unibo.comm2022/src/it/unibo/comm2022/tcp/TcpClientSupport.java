package it.unibo.comm2022.tcp;

import java.net.Socket;

import it.unibo.comm2022.interfaces.Interaction;
import it.unibo.comm2022.utils.BasicUtils;

public class TcpClientSupport {
	
	public static Interaction connect (String ip, int port, int num) {
		for(int i=0;i<=num;i++) {
			try {
				Socket socket = new Socket (ip,port);
				Interaction conn = new TcpConnection (socket);
				return conn;
			}catch(Exception e) {
				BasicUtils.delay(5000);
				System.out.println("TcpConnect | ERROR "+e.getMessage());
			}

		}
		return null;
	}
}
