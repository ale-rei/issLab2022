package it.unibo.radarSystem22.domain.factory;


import it.unibo.radarSystem22.domain.concrete.RadarDisplay;
import it.unibo.radarSystem22.domain.concrete.SonarConcreteForObs;
import it.unibo.radarSystem22.domain.interfaces.*;
import it.unibo.radarSystem22.domain.mock.SonarMockForObs;
import it.unibo.radarSystem22.domain.models.LedModel;
import it.unibo.radarSystem22.domain.models.SonarModel;
import it.unibo.radarSystem22.domain.utils.DomainSystemConfig;

public class DeviceFactory {

	public static ILed createLed() {
		return LedModel.create();
	}

	public static ISonar createSonar() {
		return SonarModel.create();
	}

	public static IRadarDisplay createRadarGui() {
		return RadarDisplay.getRadarDisplay();
	}

	public static ISonarForObs createSonarForObs(){
		if (DomainSystemConfig.simulation){
			return new SonarMockForObs();
		}
		else{
			return new SonarConcreteForObs();
		}
	}

}
