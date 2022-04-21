package it.unibo.radarSystem22.Sprint4.comm.utils;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;


public class CommSystemConfig {
	
	public static String mqttBrokerAddr = "tcp://localhost:1883"; //: 1883  OPTIONAL  tcp://broker.hivemq.com
	public static int serverTimeOut        =  600000;  //10 minuti	
 	public static ProtocolType protocolType = ProtocolType.tcp;
 	public static boolean tracing          = false;

	public static void setTheConfiguration(  ) {
		setTheConfiguration("../CommSystemConfig.json");
	}
	
	public static void setTheConfiguration( String resourceName ) {
		//Nella distribuzione resourceName � in una dir che include la bin  
		FileInputStream fis = null;
		try {
			System.out.println("setTheConfiguration from file:" + resourceName);
			if(  fis == null ) {
 				 fis = new FileInputStream(new File(resourceName));
			}
	        //JSONTokener tokener = new JSONTokener(fis);
			Reader reader       = new InputStreamReader(fis);
			JSONTokener tokener = new JSONTokener(reader);      
	        JSONObject object   = new JSONObject(tokener);
	        
	        mqttBrokerAddr   = object.getString("mqttBrokerAddr");
	        tracing          = object.getBoolean("tracing");
	        
	        switch( object.getString("protocolType") ) {
		        case "tcp"  : protocolType = ProtocolType.tcp; break;
				case "udp"  : protocolType = ProtocolType.udp; break;
		        case "coap" : protocolType = ProtocolType.coap; break;
		        case "mqtt" : protocolType = ProtocolType.mqtt; break;
	        }
 	        
		} catch (FileNotFoundException e) {
 			System.out.println("setTheConfiguration ERROR " + e.getMessage() );
		} catch (JSONException e) {
			throw new RuntimeException(e);
		}

	}	
	 
}
