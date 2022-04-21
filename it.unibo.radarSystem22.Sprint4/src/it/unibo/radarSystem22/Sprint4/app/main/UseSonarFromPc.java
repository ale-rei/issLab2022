package it.unibo.radarSystem22.Sprint4.app.main;

import it.unibo.radarSystem22.Sprint4.app.proxy.SonarProxy;
import it.unibo.radarSystem22.Sprint4.app.usecases.RadarSystemConfig;
import it.unibo.radarSystem22.Sprint4.comm.interfaces.IApplication;
import it.unibo.radarSystem22.Sprint4.comm.utils.BasicUtils;
import it.unibo.radarSystem22.Sprint4.comm.utils.CommSystemConfig;
import it.unibo.radarSystem22.Sprint4.comm.utils.ProtocolType;
import it.unibo.radarSystem22.domain.interfaces.ISonar;

public class UseSonarFromPc implements IApplication {
 	private ISonar  sonar ;
 	
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
		RadarSystemConfig.ctxServerPort    = 8756;
		CommSystemConfig.protocolType = ProtocolType.udp;
	}
	
	protected void configure() {		
		String host           = RadarSystemConfig.raspAddr;
		ProtocolType protocol = CommSystemConfig.protocolType;
		String ctxport        = ""+RadarSystemConfig.ctxServerPort;
		sonar    		      = new SonarProxy("sonarPxy", host, ctxport, protocol );
 	}
	

	public void execute() {
		System.out.println("activate the sonar");
		sonar.activate();
		BasicUtils.delay(1000);
		boolean sonarActive = sonar.isActive();
		System.out.println("sonarActive="+sonarActive);
		if( sonarActive ) {
			for( int i=1; i<=3; i++ ) {
				int d = sonar.getDistance().getVal();
				System.out.println("sonar distance="+d);
				BasicUtils.delay(1000);			
			}
		}
    }

	public void terminate() {
		sonar.deactivate();

	}	
	
	public static void main( String[] args) throws Exception {
		new UseSonarFromPc().doJob(null,null);
 	}
	
}
