package it.unibo.radarSystem22.Sprint4.main;

import it.unibo.comm2022.Sprint4.interfaces.IApplication;
import it.unibo.comm2022.Sprint4.utils.CommSystemConfig;
import it.unibo.comm2022.Sprint4.utils.ProtocolType;
import it.unibo.radarSystem22.Sprint4.interfaces.ActionFunction;
import it.unibo.radarSystem22.Sprint4.proxy.LedProxy;
import it.unibo.radarSystem22.Sprint4.proxy.SonarProxy;
import it.unibo.radarSystem22.Sprint4.usecases.Controller;
import it.unibo.radarSystem22.Sprint4.usecases.RadarSystemConfig;
import it.unibo.radarSystem22.domain.factory.DeviceFactory;
import it.unibo.radarSystem22.domain.interfaces.ILed;
import it.unibo.radarSystem22.domain.interfaces.IRadarDisplay;
import it.unibo.radarSystem22.domain.interfaces.ISonar;

public class RadarSystemMainWithCtxOnPc implements IApplication {
	private IRadarDisplay radar;
	private ISonar sonar;
	private ILed  led ;
	private Controller controller;
	
	@Override
	public String getName() {
		return this.getClass().getName() ; 
	}
	@Override
	public void doJob(String domainConfig, String systemConfig ) {
		setup(domainConfig,systemConfig);
		configure();
		execute();		
	}
	
	public void setup( String domainConfig, String systemConfig )  {
		RadarSystemConfig.DLIMIT           = 80;
		RadarSystemConfig.tracing          = true;
		RadarSystemConfig.ctxServerPort    = 8756;
		RadarSystemConfig.raspAddr         = "localhost"; //"localhost";//
		CommSystemConfig.protcolType = ProtocolType.tcp;
	}
	
	protected void configure() {		
		String host           = RadarSystemConfig.raspAddr;
		ProtocolType protocol = CommSystemConfig.protcolType;
		String ctxport        = ""+RadarSystemConfig.ctxServerPort;
		led    		= new LedProxy("ledPxy",     host, ctxport, protocol );
  		sonar  		= new SonarProxy("sonarPxy", host, ctxport, protocol );
  		radar  		= DeviceFactory.createRadarGui();
  		controller 	= Controller.create(led, sonar, radar);
	}
	

	public void execute() {
	    ActionFunction endFun = (n) -> { System.out.println(n); terminate(); };
		controller.start(endFun, 30);
 	}

	public void terminate() {
		//BasicUtils.delay(20000);
		sonar.deactivate();
		System.exit(0);
	}	
	
	public static void main( String[] args) throws Exception {
		//ColorsOut.out("Please set RadarSystemConfig.pcHostAddr in RadarSystemConfig.json");
		new RadarSystemMainWithCtxOnPc().doJob(null,null);
 	}
	
}
