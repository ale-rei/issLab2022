package it.unibo.radarSystem22.Sprint4.app.main;

import it.unibo.radarSystem22.Sprint4.app.interfaces.ActionFunction;
import it.unibo.radarSystem22.Sprint4.app.proxy.LedProxy;
import it.unibo.radarSystem22.Sprint4.app.proxy.SonarProxy;
import it.unibo.radarSystem22.Sprint4.app.usecases.Controller;
import it.unibo.radarSystem22.Sprint4.app.usecases.RadarSystemConfig;
import it.unibo.radarSystem22.Sprint4.comm.interfaces.IApplication;
import it.unibo.radarSystem22.Sprint4.comm.utils.CommSystemConfig;
import it.unibo.radarSystem22.Sprint4.comm.utils.ProtocolType;
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
		CommSystemConfig.protocolType = ProtocolType.tcp;
	}
	
	protected void configure() {		
		String host           = RadarSystemConfig.raspAddr;
		ProtocolType protocol = CommSystemConfig.protocolType;
		String ctxport        = ""+RadarSystemConfig.ctxServerPort;
		led    		= new LedProxy("ledPxy",     host, ctxport, protocol );
  		sonar  		= new SonarProxy("sonarPxy", host, ctxport, protocol );
  		radar  		= DeviceFactory.createRadarGui();
  		controller 	= Controller.create(led, sonar, radar);
	}
	

	public void execute() {
	    ActionFunction endFun = (n) -> { System.out.println(n); terminate(); };
		controller.start(endFun, 60);
 	}

	public void terminate() {
		sonar.deactivate();
		System.exit(0);
	}	
	
	public static void main( String[] args) throws Exception {
		new RadarSystemMainWithCtxOnPc().doJob(null,null);
 	}
	
}
