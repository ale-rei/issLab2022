package it.unibo.radarSystem22.Sprint4.main;

import it.unibo.comm2022.Sprint4.interfaces.IApplication;
import it.unibo.comm2022.Sprint4.utils.BasicUtils;
import it.unibo.comm2022.Sprint4.utils.ColorsOut;
import it.unibo.comm2022.Sprint4.utils.CommSystemConfig;
import it.unibo.comm2022.Sprint4.utils.ProtocolType;
import it.unibo.radarSystem22.Sprint4.proxy.SonarProxy;
import it.unibo.radarSystem22.Sprint4.usecases.RadarSystemConfig;
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
		ColorsOut.outappl(" === " + getName() + " ===", ColorsOut.MAGENTA);
		RadarSystemConfig.DLIMIT           = 80;
		RadarSystemConfig.ctxServerPort    = 8756;
		CommSystemConfig.protcolType = ProtocolType.tcp;
	}
	
	protected void configure() {		
		String host           = RadarSystemConfig.raspAddr;
		ProtocolType protocol = CommSystemConfig.protcolType;
		String ctxport        = ""+RadarSystemConfig.ctxServerPort;
		sonar    		      = new SonarProxy("sonarPxy", host, ctxport, protocol );
 	}
	

	public void execute() {
		ColorsOut.out("activate the sonar");
		sonar.activate();
		BasicUtils.delay(1000);
		//BasicUtils.waitTheUser();
		boolean sonarActive = sonar.isActive();
		ColorsOut.out("sonarActive="+sonarActive);
		if( sonarActive ) {
			for( int i=1; i<=3; i++ ) {
				int d = sonar.getDistance().getVal();
				ColorsOut.out("sonar distance="+d);
				BasicUtils.delay(1000);			
			}
		}
    }

	public void terminate() {
		sonar.deactivate();
  		//System.exit(0);
	}	
	
	public static void main( String[] args) throws Exception {
		new UseSonarFromPc().doJob(null,null);
 	}
	
}
