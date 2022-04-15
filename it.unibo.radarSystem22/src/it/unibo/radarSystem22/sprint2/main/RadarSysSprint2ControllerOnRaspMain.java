package it.unibo.radarSystem22.sprint2.main;

import it.unibo.comm2022.utils.ProtocolType;
import it.unibo.radarSystem22.IApplication;
import it.unibo.radarSystem22.domain.factory.DeviceFactory;
import it.unibo.radarSystem22.domain.interfaces.ILed;
import it.unibo.radarSystem22.domain.interfaces.IRadarDisplay;
import it.unibo.radarSystem22.domain.interfaces.ISonar;
import it.unibo.radarSystem22.domain.utils.DomainSystemConfig;
import it.unibo.radarSystem22.sprint1.ActionFunction;
import it.unibo.radarSystem22.sprint1.Controller;
import it.unibo.radarSystem22.sprint1.RadarSystemConfig;
import it.unibo.radarSystem22.sprint2.proxy.RadarGuiProxyAsClient;

import org.json.JSONException;

public class RadarSysSprint2ControllerOnRaspMain implements IApplication{
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

			RadarSystemConfig.serverPort        = 8080;
			RadarSystemConfig.hostAddr          = "localhost";
			RadarSystemConfig.DLIMIT			= 70;
			RadarSystemConfig.RadarGuiRemote	= false;
		}
	}

	public void configure() {
		System.out.println("InitComponent");
		led = DeviceFactory.createLed();
		radar = new RadarGuiProxyAsClient ("radarPxy", RadarSystemConfig.hostAddr, ""+RadarSystemConfig.serverPort,
				ProtocolType.tcp);
		sonar = DeviceFactory.createSonar();
		controller = Controller.create(led,sonar,radar);
	}

	protected void execute() {
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
		new RadarSysSprint2ControllerOnRaspMain().doJob("DomainSystemConfig.json","RadarSystemConfig.json");
		//new RadarSysSprint2ControllerOnRaspMain().doJob(null,null);
	}
}
