package it.unibo.radarSystem22.Sprint4.comm.udp;


import it.unibo.radarSystem22.Sprint4.comm.interfaces.IAppMsgHandler;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;



public class UdpServer extends Thread{
private DatagramSocket socket;
private byte[] buf;
public Map<UdpEndpoint,UdpServerConnection> connectionsMap; //map a port to a specific connection object, if any
protected IAppMsgHandler userDefHandler;
protected String name;
protected boolean stopped = true;

 	public UdpServer( String name, int port,  IAppMsgHandler userDefHandler   ) {
		super(name);
		connectionsMap = new ConcurrentHashMap<UdpEndpoint,UdpServerConnection>();
	      try {
	  		this.userDefHandler   = userDefHandler;
	  		System.out.println(getName() + " | costructor port=" + port);
			this.name             = getName();
			socket                = new DatagramSocket( port );
	     }catch (Exception e) { 
	    	 System.out.println(getName() + " | costruct ERROR: " + e.getMessage());
	     }
	}
	
	@Override
	public void run() {
	      try {
		  	System.out.println( "UdpServer | STARTING ... " + name);
			while( ! stopped ) {
				//Wait a packet				 
				System.out.println( "UdpServer | waits a packet "  );
				buf = new byte[UdpConnection.MAX_PACKET_LEN];
				DatagramPacket packet = new DatagramPacket(buf, buf.length);
				socket.receive(packet);
				InetAddress address = packet.getAddress();
	            int port = packet.getPort();
	            UdpEndpoint client = new UdpEndpoint(address, port);
	            //String received = new String(packet.getData(), 0, packet.getLength());
	            UdpServerConnection conn = connectionsMap.get(client);
	            if(conn == null) {
	            	conn = new UdpServerConnection(socket, client, connectionsMap);
	            	connectionsMap.put(client, conn);
			 		//Create HERE a message handler on the connection !!!
			 		new UdpApplMessageHandler( userDefHandler, conn );		 	 		
            }else {
	            	 System.out.println("UdpServer | CONNECTION ALREADY SET with " + client  );
	            }
	            conn.handle(packet);		 
		 		//Create a message handler on the connection NOT HERE!!
		 		//new UdpApplMessageHandler( userDefHandler, conn );			 		
			}//while
		  }catch (Exception e) {  //Scatta quando la deactive esegue: serversock.close();
			  System.out.println( "UdpServer |  probably socket closed: " + e.getMessage());
		  }
	}
	
	public void activate() {
		if( stopped ) {
			stopped = false;
			this.start();
		}//else already activated
	}
 
	public void deactivate() {
		try {
			System.out.println( "UdpServer |  DEACTIVATE serversock=" +  socket);
			stopped = true;
			socket.close();
			connectionsMap.clear();
		} catch (Exception e) {
			System.out.println( "UdpServer |  deactivate ERROR: " + e.getMessage());
		}
	}

	public int getNumConnections() {
		return connectionsMap.size();
	}
 
}
