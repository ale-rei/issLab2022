package it.unibo.radarSystem22.Sprint4.app.main;

import it.unibo.radarSystem22.Sprint4.app.proxy.LedProxyAsClient;
import it.unibo.radarSystem22.Sprint4.app.usecases.RadarSystemConfig;
import it.unibo.radarSystem22.Sprint4.comm.interfaces.IApplication;
import it.unibo.radarSystem22.Sprint4.comm.utils.BasicUtils;
import it.unibo.radarSystem22.Sprint4.comm.utils.CommSystemConfig;
import it.unibo.radarSystem22.Sprint4.comm.utils.ProtocolType;
import it.unibo.radarSystem22.domain.interfaces.ILed;

public class UseLedFromPc implements IApplication{
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
		System.out.println(" === " + getName() + " ===");
		RadarSystemConfig.DLIMIT           = 80;
		RadarSystemConfig.raspAddr         = "localhost";
		RadarSystemConfig.ctxServerPort    = 8756;
		CommSystemConfig.protcolType = ProtocolType.tcp;
	}
	
	protected void configure() {		
		String host           = RadarSystemConfig.raspAddr;
		ProtocolType protocol = CommSystemConfig.protcolType;
		String ctxport        = ""+RadarSystemConfig.ctxServerPort;
		led    		          = new LedProxyAsClient("ledPxy", host, ctxport, protocol );
		
 	}
	

	public void execute() {
		System.out.println("turnOn");
		led.turnOn();
		BasicUtils.delay(1000);
		//BasicUtils.waitTheUser();
		boolean ledState = led.getState();
		System.out.println("ledState after on="+ledState);
		BasicUtils.delay(1000);
		//BasicUtils.waitTheUser();
		System.out.println("turnOff");
		led.turnOff();
		ledState = led.getState();
		System.out.println("ledState after off="+ledState);
   	}

	public void terminate() {
		((LedProxyAsClient)led).close();
	}	
	
	public static void main( String[] args) throws Exception {
		new UseLedFromPc().doJob(null,null);
 	}
	
}
