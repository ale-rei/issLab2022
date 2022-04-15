package it.unibo.radarSystem22.sprint1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import it.unibo.comm2022.utils.ProtocolType;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class RadarSystemConfig {
    public static int DLIMIT              	=  70;
    public static boolean  RadarGuiRemote 	= false;
    public static boolean tracing         	= false;
    public static boolean testing         	= false;
    
    //sprint 2
	public static String hostAddr         = "localhost";
	public static String raspAddr         = "localhost";
	public static int serverPort          = 8080;
    //sprint 2a e 3
	public static int ledPort             = 8010;
	public static int sonarPort           = 8015;
	public static ProtocolType protocolType = ProtocolType.tcp;

	//funzioni
    public static void setTheConfiguration(  ) {
		setTheConfiguration("../RadarSystemConfig.json");
	}
	
	public static void setTheConfiguration(String resourceName ) {
		FileReader fis = null;
		try {
			System.out.println("setTheConfiguration from file: " + resourceName);
			if (fis == null) {
				try {
					fis = new FileReader(new File(resourceName));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
	
			JSONTokener tokener = new JSONTokener(fis);      
	        JSONObject object   = new JSONObject(tokener);

			RadarGuiRemote = object.getBoolean("RadarGuiRemote");
			tracing = object.getBoolean("tracing");
			DLIMIT = object.getInt("DLIMIT");
			testing = object.getBoolean("testing");

			//sprint 2
			hostAddr = object.getString("hostAddr");
			serverPort = object.getInt("serverPort");
			raspAddr = object.getString("raspAddr");

			// sprint3
			ledPort								= object.getInt("ledPort");
			sonarPort							= object.getInt("sonarPort");

			switch (object.getString("protocolType")){
				case "tcp" : protocolType = ProtocolType.tcp; break;
				case "udp" : protocolType = ProtocolType.udp; break;
				case "coap" : protocolType = ProtocolType.coap; break;
				case "mqtt" : protocolType = ProtocolType.mqtt; break;
			}

		} catch (JSONException e) {
			e.getMessage();
		}
	}
}
