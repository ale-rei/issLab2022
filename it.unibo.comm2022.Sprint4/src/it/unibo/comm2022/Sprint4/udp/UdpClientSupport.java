package it.unibo.comm2022.Sprint4.udp;

import it.unibo.comm2022.Sprint4.interfaces.Interaction2021;


import java.net.DatagramSocket;
import java.net.InetAddress;


public class UdpClientSupport {

	
	public static Interaction2021 connect(String host, int port) throws Exception {
		DatagramSocket socket = new DatagramSocket();
        InetAddress address   = InetAddress.getByName(host);
        UdpEndpoint server    = new UdpEndpoint(address, port);
		Interaction2021 conn  = new UdpConnection(socket, server);
		return conn;
 	}
 
}
