package it.unibo.comm2022.udp.giannatempo;
import java.net.DatagramSocket;
import java.net.InetAddress;

import it.unibo.comm2022.interfaces.Interaction;


public class UdpClientSupport {

	
	public static Interaction connect(String host, int port) throws Exception {
		DatagramSocket socket = new DatagramSocket();
        InetAddress address   = InetAddress.getByName(host);
        UdpEndpoint server    = new UdpEndpoint(address, port);
		Interaction conn  = new UdpConnection(socket, server);
		return conn;
 	}
 
}
