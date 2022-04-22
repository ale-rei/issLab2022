package it.unibo.comm2022.Sprint4.udp;


import it.unibo.comm2022.Sprint4.interfaces.Interaction;


import java.net.DatagramSocket;
import java.net.InetAddress;


public class UdpClientSupport {

	
	public static Interaction connect(String host, int port) throws Exception {
		DatagramSocket socket = new DatagramSocket();
        InetAddress address   = InetAddress.getByName(host);
        UdpEndpoint server    = new UdpEndpoint(address, port);
		Interaction conn  = new UdpConnection(socket, server);
		return conn;
 	}
 
}
