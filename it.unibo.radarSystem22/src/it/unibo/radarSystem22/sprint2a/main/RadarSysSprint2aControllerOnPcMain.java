package it.unibo.radarSystem22.sprint2a.main;

import it.unibo.comm2022.utils.ProtocolType;
import it.unibo.radarSystem22.IApplication;
import it.unibo.radarSystem22.domain.factory.DeviceFactory;
import it.unibo.radarSystem22.domain.interfaces.ILed;
import it.unibo.radarSystem22.domain.interfaces.IRadarDisplay;
import it.unibo.radarSystem22.domain.interfaces.ISonar;
import it.unibo.radarSystem22.domain.utils.DomainSystemConfig;
import it.unibo.radarSystem22.sprint1.ActionFunction;
import it.unibo.radarSystem22.sprint1.Controller;
import it.unibo.radarSystem22.sprint1.RadarSystemConfig;
import it.unibo.radarSystem22.sprint2a.proxy.LedProxyAsClient;
import it.unibo.radarSystem22.sprint2a.proxy.SonarProxyAsClient;

public class RadarSysSprint2aControllerOnPcMain implements IApplication {
    private IRadarDisplay radar;
    private ILed led;
    private ISonar sonar;
    private Controller controller;

    private void setup(String domainConfig, String radarConfig ){
        if(domainConfig != null){
            DomainSystemConfig.setTheConfiguration(domainConfig);
        }
        if(radarConfig!=null){
            RadarSystemConfig.setTheConfiguration(radarConfig);
        }
        if(domainConfig == null && radarConfig == null){
            DomainSystemConfig.simulation  = true;
            DomainSystemConfig.sonarDelay  = 200;
            DomainSystemConfig.ledGui      = true;
            DomainSystemConfig.DLIMIT       = 70;

            RadarSystemConfig.RadarGuiRemote    = false;
            RadarSystemConfig.raspAddr         = "localhost";
            RadarSystemConfig.serverPort          = 8080;
            RadarSystemConfig.protocolType       = ProtocolType.udp;
            RadarSystemConfig.ledPort             = 8010;
            RadarSystemConfig.sonarPort           = 8015;
        }
    }

    private void configure(){
        ProtocolType protocol = RadarSystemConfig.protocolType;
        led    		= new LedProxyAsClient("ledPxy",
                RadarSystemConfig.raspAddr, ""+RadarSystemConfig.ledPort, protocol );
        sonar  		= new SonarProxyAsClient("sonarPxy",
                RadarSystemConfig.raspAddr, ""+RadarSystemConfig.sonarPort, protocol);
        radar  		= DeviceFactory.createRadarGui();
        controller = Controller.create(led, sonar, radar);
    }
    private void execute(){
        ActionFunction endFun = (n) -> {
            System.out.println(n);
            terminate();
        };
        controller.start(endFun, 30);
    }
    private void terminate(){
        sonar.deactivate();
        System.exit(0);
    }
    @Override
    public void doJob(String domainConfig, String radarConfig) {
        setup(domainConfig,radarConfig);
        configure();
        execute();
    }

    @Override
    public String getName() {
        return this.getClass().getName();
    }

    public static void main(String args[]){
        new RadarSysSprint2aControllerOnPcMain().doJob("DomainSystemConfig.json","RadarSystemConfig.json");
        //new RadarSysSprint2aControllerOnPcMain().doJob(null,null);
    }
}
