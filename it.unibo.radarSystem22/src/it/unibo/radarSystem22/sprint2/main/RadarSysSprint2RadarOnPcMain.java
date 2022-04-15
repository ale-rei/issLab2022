package it.unibo.radarSystem22.sprint2.main;

import it.unibo.comm2022.interfaces.IAppMsgHandler;
import it.unibo.comm2022.tcp.TcpServer;
import it.unibo.radarSystem22.IApplication;
import it.unibo.radarSystem22.domain.interfaces.IRadarDisplay;
import it.unibo.radarSystem22.domain.utils.DomainSystemConfig;
import it.unibo.radarSystem22.sprint1.RadarSystemConfig;
import it.unibo.radarSystem22.sprint2.handlers.RadarAppHandler;
import it.unibo.radarSystem22.domain.factory.DeviceFactory;



public class RadarSysSprint2RadarOnPcMain implements IApplication{

	private TcpServer server;
	private IRadarDisplay radar;
	
	@Override
	public void doJob(String domainConfig, String radarConfig) {
		setup(domainConfig,radarConfig);
		configure();
		execute();
	}

	private void configure() {
		int port = RadarSystemConfig.serverPort;		
   		radar  	 = DeviceFactory.createRadarGui();
  	    IAppMsgHandler radarh = new RadarAppHandler("radarh", radar);
 	    server  = new TcpServer("pcServer",port,radarh );
	}

	private void execute() {
		server.activate();
	}

	private void setup(String domainConfig, String radarConfig) {
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
				RadarSystemConfig.raspAddr          = "localhost";
				RadarSystemConfig.DLIMIT			= 70;
				RadarSystemConfig.RadarGuiRemote	= false;
			}
	}


	@Override
	public String getName() {
		return this.getClass().getName();
	}
	
	public static void main (String args[]) {
		new RadarSysSprint2RadarOnPcMain().doJob("DomainSystemConfig.json","RadarSystemConfig.json");
		//new RadarSysSprint2RadarOnPcMain().doJob(null,null);
	}

}
