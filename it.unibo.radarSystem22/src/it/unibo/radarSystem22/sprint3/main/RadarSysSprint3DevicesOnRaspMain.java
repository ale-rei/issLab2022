package it.unibo.radarSystem22.sprint3.main;

import it.unibo.comm2022.enablers.EnableAsServer;
import it.unibo.comm2022.interfaces.IAppMsgHandler;
import it.unibo.comm2022.utils.BasicUtils;
import it.unibo.comm2022.utils.ProtocolType;
import it.unibo.radarSystem22.IApplication;
import it.unibo.radarSystem22.domain.factory.DeviceFactory;
import it.unibo.radarSystem22.domain.interfaces.ILed;
import it.unibo.radarSystem22.domain.interfaces.ISonar;
import it.unibo.radarSystem22.domain.utils.DomainSystemConfig;
import it.unibo.radarSystem22.sprint1.RadarSystemConfig;
import it.unibo.radarSystem22.sprint3.handlers.LedAppHandler;
import it.unibo.radarSystem22.sprint3.handlers.SonarAppHandler;

public class RadarSysSprint3DevicesOnRaspMain implements IApplication {

    private ILed led;
    private ISonar sonar;
    private EnableAsServer ledEnabler;
    private EnableAsServer sonarEnabler;

    public void doJob(String domainConfig, String radarConfig) {
        setup(domainConfig,radarConfig);
        configure();
        execute();
    }

    private void setup(String domainConfig, String radarConfig){
        if(domainConfig!=null){
            DomainSystemConfig.setTheConfiguration(domainConfig);
        }
        if(radarConfig!=null){
            DomainSystemConfig.setTheConfiguration(radarConfig);
        }
        if (domainConfig == null && radarConfig == null) {
            DomainSystemConfig.simulation  = true;
            DomainSystemConfig.sonarDelay  = 200;
            DomainSystemConfig.ledGui      = true;
            DomainSystemConfig.DLIMIT      = 70;

            RadarSystemConfig.RadarGuiRemote    = true;
            RadarSystemConfig.serverPort          = 8000;
            RadarSystemConfig.hostAddr         = "localhost";
            RadarSystemConfig.protocolType       = ProtocolType.udp;
            RadarSystemConfig.ledPort             = 8010;
            RadarSystemConfig.sonarPort           = 8015;
        }
    }

    private void configure(){
        ProtocolType protocol = RadarSystemConfig.protocolType;
        led = DeviceFactory.createLed();
        sonar = DeviceFactory.createSonar();
        IAppMsgHandler ledh = LedAppHandler.create("ledh",led);
        IAppMsgHandler sonarh = SonarAppHandler.create("sonarh",sonar);

        sonarEnabler = new EnableAsServer("sonarSrv", RadarSystemConfig.sonarPort,
                protocol, sonarh);
        ledEnabler   = new EnableAsServer("ledSrv", RadarSystemConfig.ledPort,
                protocol, ledh );
    }

    private void execute(){
        ledEnabler.start();
        sonarEnabler.start();
    }

    @Override
    public String getName() {
        return this.getClass().getName();
    }

    public static void main(String args[]){
        BasicUtils.aboutThreads("main on rasp main");
        new RadarSysSprint3DevicesOnRaspMain().doJob(null,null);
        //new RadarSysSprint3DevicesOnRaspMain().doJob("DomainSystemConfig.json","RadarSystemConfig.json");
    }
}
