package it.unibo.radarSystem22.Sprint4.comm.udp;


import it.unibo.radarSystem22.Sprint4.comm.interfaces.Interaction;

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
