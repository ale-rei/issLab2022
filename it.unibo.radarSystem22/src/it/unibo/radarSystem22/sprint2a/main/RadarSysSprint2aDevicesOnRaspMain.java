package it.unibo.radarSystem22.sprint2a.main;

import it.unibo.comm2022.interfaces.IAppMsgHandler;
import it.unibo.comm2022.tcp.TcpServer;
import it.unibo.comm2022.udp.giannatempo.UdpServer;
import it.unibo.comm2022.utils.ProtocolType;
import it.unibo.radarSystem22.IApplication;
import it.unibo.radarSystem22.domain.factory.DeviceFactory;
import it.unibo.radarSystem22.domain.interfaces.ILed;
import it.unibo.radarSystem22.domain.interfaces.ISonar;
import it.unibo.radarSystem22.domain.utils.DomainSystemConfig;
import it.unibo.radarSystem22.sprint1.RadarSystemConfig;
import it.unibo.radarSystem22.sprint2a.handlers.LedAppHandler;
import it.unibo.radarSystem22.sprint2a.handlers.SonarAppHandler;

public class RadarSysSprint2aDevicesOnRaspMain implements IApplication {
    private ISonar sonar;
    private ILed led ;
    private TcpServer ledServerTcp ;
    private TcpServer sonarServerTcp ;

   private UdpServer ledServerUdp ;
   private UdpServer sonarServerUdp;

    @Override
    public void doJob(String domainConfig, String radarConfig) {
        setup(domainConfig,radarConfig);
        configure();
        execute();
    }

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
            DomainSystemConfig.DLIMIT      = 70;

            RadarSystemConfig.RadarGuiRemote    = false;
            RadarSystemConfig.hostAddr         = "localhost";
            RadarSystemConfig.serverPort          = 8080;
            RadarSystemConfig.protocolType       = ProtocolType.udp;
            RadarSystemConfig.ledPort             = 8010;
            RadarSystemConfig.sonarPort           = 8015;
        }
    }

    private void configure(){
        ProtocolType protocol = RadarSystemConfig.protocolType;
        led        = DeviceFactory.createLed();
        IAppMsgHandler ledh = LedAppHandler.create("ledh", led);
        switch( protocol ) {
            case tcp :  {
                ledServerTcp  = new TcpServer("ledServerTcp",RadarSystemConfig.ledPort,ledh );
                break;
            }
            case udp:{
                ledServerUdp = new UdpServer("ledServerUdp", RadarSystemConfig.ledPort, ledh);
                break;
            }
            default:
                break;
        }
        sonar      = DeviceFactory.createSonar();
        IAppMsgHandler sonarh = SonarAppHandler.create("sonarh", sonar);
        switch( protocol ) {
            case tcp : {
                sonarServerTcp   = new TcpServer("sonarServer",RadarSystemConfig.sonarPort,sonarh);
                break;
            }
            case udp:{
                sonarServerUdp = new UdpServer("sonarServerUdp", RadarSystemConfig.sonarPort, sonarh);
                break;
            }
            default:
                break;
        }
    }

    private void execute(){
        switch (RadarSystemConfig.protocolType){
            case tcp: {
                ledServerTcp.activate();
                sonarServerTcp.activate();
                break;
            }
            case udp: {
                ledServerUdp.activate();
                sonarServerUdp.activate();
                break;
            }
        }
    }

    @Override
    public String getName() {
        return this.getClass().getName();
    }

    public static void main(String args[]){
        new RadarSysSprint2aDevicesOnRaspMain().doJob("DomainSystemConfig.json","RadarSystemConfig.json");
        //new RadarSysSprint2aDevicesOnRaspMain().doJob(null,null);
    }
}
