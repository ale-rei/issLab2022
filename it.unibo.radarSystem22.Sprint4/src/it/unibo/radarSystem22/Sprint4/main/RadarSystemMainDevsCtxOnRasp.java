package it.unibo.radarSystem22.Sprint4.main;

import it.unibo.comm2022.Sprint4.context.ContextMsgHandler;
import it.unibo.comm2022.Sprint4.enablers.EnablerContext;
import it.unibo.comm2022.Sprint4.interfaces.IAppMsgHandler;
import it.unibo.comm2022.Sprint4.interfaces.IApplication;
import it.unibo.comm2022.Sprint4.interfaces.IContext;
import it.unibo.comm2022.Sprint4.utils.BasicUtils;
import it.unibo.comm2022.Sprint4.utils.ProtocolType;
import it.unibo.radarSystem22.Sprint4.handlers.LedAppHandler;
import it.unibo.radarSystem22.Sprint4.handlers.SonarAppHandler;
import it.unibo.radarSystem22.Sprint4.usecases.RadarSystemConfig;
import it.unibo.radarSystem22.domain.factory.DeviceFactory;
import it.unibo.radarSystem22.domain.interfaces.ILed;
import it.unibo.radarSystem22.domain.interfaces.ISonar;
import it.unibo.radarSystem22.domain.utils.DomainSystemConfig;

public class RadarSystemMainDevsCtxOnRasp implements IApplication {
	private ISonar sonar;
	private ILed led ;
  	
 	private IContext contextServer;

	
	@Override
	public String getName() {
		return this.getClass().getName() ; 
	}
	@Override
	public void doJob(String domainConfig, String systemConfig ) {
		setup(domainConfig,   systemConfig);
		configure();
		execute();		
	}
	
	public void setup( String domainConfig, String systemConfig )  {
	    BasicUtils.aboutThreads(getName() + " | Before setup ");
		if( domainConfig != null ) {
			DomainSystemConfig.setTheConfiguration(domainConfig);
		}
		if( systemConfig != null ) {
			RadarSystemConfig.setTheConfiguration(systemConfig);
		}
		if( domainConfig == null && systemConfig == null) {
			DomainSystemConfig.simulation  = true;

	    	DomainSystemConfig.ledGui      = true;

		    RadarSystemConfig.RadarGuiRemote   = true;
 			RadarSystemConfig.ctxServerPort    = 8756;
 			RadarSystemConfig.protocolType      = ProtocolType.tcp;
		}
 
	}
	protected void configure() {		
 	   led   = DeviceFactory.createLed();
	   sonar = DeviceFactory.createSonar();
   

	   contextServer = new EnablerContext("ctx",""+RadarSystemConfig.ctxServerPort,
 			                  RadarSystemConfig.protocolType, new ContextMsgHandler("ctxH"));

 	   IAppMsgHandler sonarHandler = SonarAppHandler.create("sonarH",sonar);
	   IAppMsgHandler ledHandler   = LedAppHandler.create("ledH",led);
	   contextServer.addComponent("sonar", sonarHandler);
	   contextServer.addComponent("led",   ledHandler);
	}
	
	protected void execute() {		
		contextServer.activate();
	}
	
	public static void main( String[] args) throws Exception {
		new RadarSystemMainDevsCtxOnRasp().doJob(null,null);
		//new RadarSystemMainDevsCtxOnRasp().doJob("DomainSystemConfig.json","RadarSystemConfig.json");
 	}

}
