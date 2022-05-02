package it.unibo.radarSystem22.domain.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;


import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;


public class DomainSystemConfig {
	public static  boolean simulation    = false;
 	public static  boolean ledGui        = false;
	public static int sonarDelay          =  100;
	public static int DLIMIT              =  70;

	public static int sonarDistanceMax    =  150;

	public static boolean testing = false;
	public static int testingDistance = 22;

	public static void setTheConfiguration(  ) {
		setTheConfiguration("../DomainSystemConfig.json");
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

			simulation = object.getBoolean("simulation");
			sonarDelay = object.getInt("sonarDelay");
			DLIMIT = object.getInt("DLIMIT");
			sonarDistanceMax = object.getInt("sonarDistanceMax");
			ledGui = object.getBoolean("ledGui");
			testingDistance = object.getInt("testingDistance");
			testing = object.getBoolean("testing");
		} catch (JSONException e) {
			e.getMessage();
		}
	}
}
