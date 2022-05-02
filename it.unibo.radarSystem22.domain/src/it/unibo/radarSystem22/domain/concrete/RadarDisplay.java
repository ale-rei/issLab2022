package it.unibo.radarSystem22.domain.concrete;

 
import it.unibo.radarSystem22.domain.interfaces.IRadarDisplay;
import radarPojo.radarSupport;

public class RadarDisplay implements IRadarDisplay{
	private String curDistance = "0";
	private static RadarDisplay display = null;

	public static RadarDisplay getRadarDisplay(){
		if( display == null ) {
			display = new RadarDisplay();
		}
		return display;
	}

	protected RadarDisplay() {
		radarSupport.setUpRadarGui();
	}
	@Override
	public void update(String distance, String angle) {
		System.out.println("RadarDisplay | update distance="+distance);
		curDistance =  distance;
		radarSupport.update(distance,angle);
	}

	public int getCurDistance() {
		System.out.println("RadarDisplay | getCurDistance="+curDistance);
		return Integer.parseInt(curDistance);
	}
}
