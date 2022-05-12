package it.unibo.radarSystem22.Sprint4.main;

import it.unibo.comm2022.Sprint4.interfaces.IApplication;
import it.unibo.comm2022.Sprint4.utils.BasicUtils;
import it.unibo.comm2022.Sprint4.utils.ColorsOut;
import it.unibo.comm2022.Sprint4.utils.CommSystemConfig;
import it.unibo.comm2022.Sprint4.utils.ProtocolType;
import it.unibo.radarSystem22.Sprint4.proxy.LedProxy;
import it.unibo.radarSystem22.Sprint4.usecases.RadarSystemConfig;
import it.unibo.radarSystem22.domain.interfaces.ILed;


public class UseLedFromPc implements IApplication {
 	private ILed  led ;
 	
	@Override
	public String getName() {
		return this.getClass().getName() ; 
	}
	@Override
	public void doJob(String domainConfig, String systemConfig ) {
		setup(domainConfig,systemConfig);
		configure();
		execute();	
		terminate();
	}
	
	public void setup( String domainConfig, String systemConfig )  {
		ColorsOut.outappl(" === " + getName() + " ===", ColorsOut.MAGENTA);
		RadarSystemConfig.DLIMIT           = 80;
		RadarSystemConfig.raspAddr         = "localhost";
		RadarSystemConfig.ctxServerPort    = 8756;
		CommSystemConfig.protcolType = ProtocolType.tcp;
	}
	
	protected void configure() {		
		String host           = RadarSystemConfig.raspAddr;
		ProtocolType protocol = CommSystemConfig.protcolType;
		String ctxport        = ""+RadarSystemConfig.ctxServerPort;
		led    		          = new LedProxy("ledPxy", host, ctxport, protocol );
		//WARNING: il LedProxy va chiuso con un casting perch� ILed non ha deactivate
 	}
	

	public void execute() {
		ColorsOut.out("turnOn");
		led.turnOn();
		BasicUtils.delay(1000);
		//BasicUtils.waitTheUser();
		boolean ledState = led.getState();
		ColorsOut.out("ledState after on="+ledState);
		BasicUtils.delay(1000);
		//BasicUtils.waitTheUser();
		ColorsOut.out("turnOff");
		led.turnOff();
		ledState = led.getState();
		ColorsOut.out("ledState after off="+ledState);
   	}

	public void terminate() {
		((LedProxy)led).close();
  		//System.exit(0);
	}	
	
	public static void main( String[] args) throws Exception {
		new UseLedFromPc().doJob(null,null);
 	}
	
}
