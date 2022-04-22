package it.unibo.radarSystem22.Sprint4.main;

import it.unibo.comm2022.Sprint4.interfaces.IApplication;
import it.unibo.comm2022.Sprint4.utils.BasicUtils;
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
		System.out.println(" === " + getName() + " ===");
		RadarSystemConfig.DLIMIT           = 80;
		RadarSystemConfig.raspAddr         = "localhost";
		RadarSystemConfig.ctxServerPort    = 8756;
		CommSystemConfig.protocolType = ProtocolType.udp;
	}
	
	protected void configure() {		
		String host           = RadarSystemConfig.raspAddr;
		ProtocolType protocol = CommSystemConfig.protocolType;
		String ctxport        = ""+RadarSystemConfig.ctxServerPort;
		led    		          = new LedProxy("ledPxy", host, ctxport, protocol );

 	}
	

	public void execute() {
		System.out.println("turnOn");
		led.turnOn();
		BasicUtils.delay(1000);

		boolean ledState = led.getState();
		System.out.println("ledState after on="+ledState);
		BasicUtils.delay(1000);

		System.out.println("turnOff");
		led.turnOff();
		ledState = led.getState();
		System.out.println("ledState after off="+ledState);
   	}

	public void terminate() {
		((LedProxy)led).close();
	}	
	
	public static void main( String[] args) throws Exception {
		new UseLedFromPc().doJob(null,null);
 	}
	
}
