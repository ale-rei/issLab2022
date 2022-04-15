package it.unibo.radarSystem22.Sprint4.comm.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class CommSystemConfig {
	public static int serverTimeOut        =  600000;
	public static ProtocolType protcolType = ProtocolType.tcp;
	public static boolean tracing          = false;

	public static void setTheConfiguration(  ) {
		setTheConfiguration("../CommSystemConfig.json");
	}

	public static void setTheConfiguration(String resourceName ) {
		FileReader fis = null;
		try {
			if (fis == null) {
				try {
					fis = new FileReader(new File(resourceName));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}

			JSONTokener tokener = new JSONTokener(fis);
			JSONObject object   = new JSONObject(tokener);

			switch( object.getString("protocolType") ) {
				case "tcp"  :
					protcolType = ProtocolType.tcp;
					break;
				case "coap" :
					protcolType = ProtocolType.coap;
					break;
				case "mqtt" :
					protcolType = ProtocolType.mqtt;
					break;
			}

			serverTimeOut = object.getInt("serverTimeOut");
			tracing = object.getBoolean("tracing");

		} catch (JSONException e) {
			e.getMessage();
		}
	}
}
