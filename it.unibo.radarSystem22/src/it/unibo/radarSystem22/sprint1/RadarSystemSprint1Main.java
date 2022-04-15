package it.unibo.radarSystem22.sprint1;

import it.unibo.radarSystem22.IApplication;
import it.unibo.radarSystem22.domain.factory.DeviceFactory;
import it.unibo.radarSystem22.domain.interfaces.ILed;
import it.unibo.radarSystem22.domain.interfaces.IRadarDisplay;
import it.unibo.radarSystem22.domain.interfaces.ISonar;
import it.unibo.radarSystem22.domain.utils.DomainSystemConfig;
import org.json.JSONException;

public class RadarSystemSprint1Main implements IApplication{
	private ILed led;
	private ISonar sonar;
	private IRadarDisplay radar;
	private Controller controller;

	@Override
	public void doJob (String domainConfig, String systemConfig) {
		try {
			setup(domainConfig,systemConfig);
		}catch(Exception e) {
			e.toString();
		}
		configure();
		execute();
	}

	private void setup(String domainConfig, String radarConfig) throws JSONException {
		if (domainConfig != null)
			DomainSystemConfig.setTheConfiguration(domainConfig);
		if (radarConfig != null) {
			RadarSystemConfig.setTheConfiguration(radarConfig);
		}
		if (domainConfig == null && radarConfig == null) {
			DomainSystemConfig.sonarDelay       = 200;
			DomainSystemConfig.simulation   	= true;
			DomainSystemConfig.ledGui           = true;
			DomainSystemConfig.DLIMIT      		= 70;

			RadarSystemConfig.DLIMIT			= 70;
			RadarSystemConfig.RadarGuiRemote	= false;
		}
	}

	public void configure() {
		System.out.println("InitComponent");
		led = DeviceFactory.createLed();
		radar = RadarSystemConfig.RadarGuiRemote ? null : DeviceFactory.createRadarGui();
		sonar = DeviceFactory.createSonar();
		controller = Controller.create(led,sonar,radar);
	}

	private void execute(){
		ActionFunction end = (n) -> {
			terminate();
		};
		controller.start(end, 30);
	}

	private void terminate() {
		sonar.deactivate();
		System.exit(0);
	}

	public IRadarDisplay getRadarGui() { return radar; }
 	public ILed getLed() { return led; }
 	public ISonar getSonar() { return sonar; }
 	public Controller getController() { return controller; }
 	

	@Override
	public String getName() {
		return this.getClass().getName();
	}

	public static void main (String [] args) throws Exception {
		new RadarSystemSprint1Main().doJob("DomainSystemConfig.json","RadarSystemConfig.json");
		//new RadarSystemSprint1Main().doJob(null, null);
	}
}
