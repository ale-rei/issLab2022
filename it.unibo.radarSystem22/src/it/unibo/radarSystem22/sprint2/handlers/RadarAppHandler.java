package it.unibo.radarSystem22.sprint2.handlers;

import java.io.IOException;

import it.unibo.comm2022.interfaces.IAppMsgHandler;
import org.json.JSONException;
import org.json.JSONObject;

import it.unibo.comm2022.interfaces.Interaction;
import it.unibo.comm2022.utils.AppMsgHandler;
import it.unibo.radarSystem22.domain.interfaces.IRadarDisplay;

public class RadarAppHandler extends AppMsgHandler{

	private IRadarDisplay radar;
	private int curDistance=0;
	private String angle="";

	public static IAppMsgHandler create(String name, IRadarDisplay radar){
		return new RadarAppHandler(name,radar);
	}
	public RadarAppHandler(String name, IRadarDisplay radar) {
		super(name);
		this.radar=radar;
	}

	@Override
	public void elaborate(String message, Interaction conn) {
		System.out.println(message);
		if(message.equals("getCurDistance")){
			try {
				conn.reply(""+curDistance);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

		else{
			System.out.println(message);
			JSONObject jsonObj;
			try {
				jsonObj = new JSONObject(message);
				curDistance = jsonObj.getInt("distance");
				angle    = ""+jsonObj.getInt("angle");
			} catch (JSONException e) {
				e.printStackTrace();
			}
			radar.update( ""+curDistance, angle);
		}

	}

}
