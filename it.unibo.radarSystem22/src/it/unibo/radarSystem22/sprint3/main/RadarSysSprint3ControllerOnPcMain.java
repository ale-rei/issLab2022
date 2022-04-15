package it.unibo.radarSystem22.sprint3.main;

import it.unibo.comm2022.proxy.ProxyAsClient;
import it.unibo.comm2022.utils.BasicUtils;
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

public class RadarSysSprint3ControllerOnPcMain implements IApplication {

    private IRadarDisplay radar;
    private ILed led;
    private Controller controller;
    private ISonar sonar;


    public void doJob(String domainConfig, String radarConfig){
        setup(domainConfig,radarConfig);
        configure();
        execute();
    }

    @Override
    public String getName() {
        return this.getClass().getName();
    }

    private void setup(String domainConfig,String radarConfig){
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

            RadarSystemConfig.RadarGuiRemote    = false;
            RadarSystemConfig.raspAddr         = "localhost";
            RadarSystemConfig.serverPort          = 8000;
            RadarSystemConfig.protocolType       = ProtocolType.udp;
            RadarSystemConfig.ledPort             = 8010;
            RadarSystemConfig.sonarPort           = 8015;
        }
    }

    private void configure(){
        ProtocolType protocol = RadarSystemConfig.protocolType;
        led = new LedProxyAsClient("ledProxy", RadarSystemConfig.raspAddr, ""+RadarSystemConfig.ledPort, protocol);
        sonar = new SonarProxyAsClient("sonarProxy", RadarSystemConfig.raspAddr, ""+RadarSystemConfig.sonarPort, protocol);
        radar = DeviceFactory.createRadarGui();
        controller = Controller.create(led,sonar,radar);

    }

    private void execute(){
        ActionFunction endFun = (n) -> {
            System.out.println(n);
            terminate();
        };
        controller.start(endFun, 60);
    }

    private void terminate(){
        ((ProxyAsClient) led).close();
        System.exit(0);
    }

    public static void main(String args[]){
        BasicUtils.aboutThreads("main on pc main");
        new RadarSysSprint3ControllerOnPcMain().doJob(null,null);
        //new RadarSysSprint3ControllerOnPcMain().doJob("DomainSystemConfig.json","RadarSystemConfig.json");
    }
}
