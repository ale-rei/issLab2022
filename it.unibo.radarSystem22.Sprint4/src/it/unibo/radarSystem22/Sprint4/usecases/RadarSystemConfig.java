package it.unibo.radarSystem22.Sprint4.usecases;

import it.unibo.comm2022.Sprint4.utils.ProtocolType;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;


public class RadarSystemConfig {
    public static boolean tracing         = false;
    public static boolean testing         = false;
    public static int DLIMIT              =  15;
    public static boolean RadarGuiRemote = false;


    public static String hostAddr         = "localhost";
    public static String raspAddr         = "localhost";

    //Aggiunte dello SPRINT4
    public static ProtocolType protocolType = ProtocolType.tcp;
    public static int ctxServerPort      = 8018;

    public static void setTheConfiguration(  ) {
        setTheConfiguration("../RadarSystemConfig.json");
    }

    public static void setTheConfiguration( String resourceName ) {

        FileInputStream fis = null;
        try {
            System.out.println("setTheConfiguration from file:" + resourceName);
            if(  fis == null ) {
                fis = new FileInputStream(new File(resourceName));
            }

            Reader reader       = new InputStreamReader(fis);
            JSONTokener tokener = new JSONTokener(reader);
            JSONObject object   = new JSONObject(tokener);

            RadarGuiRemote   = object.getBoolean("RadarGuiRemote");
            DLIMIT           = object.getInt("DLIMIT");

            //Aggiunte dello SPRINT4
            ctxServerPort    = object.getInt("ctxServerPort");

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
