package it.unibo.radarSystem22.Sprint4.app.usecases;


import it.unibo.radarSystem22.domain.interfaces.IDistance;
import it.unibo.radarSystem22.domain.interfaces.ILed;

public class LedAlarmUsecase {
 	public static void doUseCase(ILed led, IDistance d) {
 		try {
 			System.out.println("LedAlarmUsecase | sonar distance=" + d.getVal() + "|"+RadarSystemConfig.DLIMIT );
			if( d.getVal() <  RadarSystemConfig.DLIMIT ) led.turnOn(); else  led.turnOff();
 		} catch (Exception e) {
			System.out.println("LedAlarmUsecase | ERROR " + e.getMessage() );
		}					
 	}
 
}
