package it.unibo.comm2022.enablers;

import it.unibo.comm2022.interfaces.IAppMsgHandler;

import it.unibo.comm2022.udp.giannatempo.UdpServer;
import it.unibo.comm2022.utils.ProtocolType;
import it.unibo.comm2022.tcp.TcpServer;

public class EnableAsServer {
    protected String name;
    protected ProtocolType protocol;
    protected TcpServer tcpServer;
    protected UdpServer udpServer;
    private static int count=1;
    protected boolean isactive = false;

    public EnableAsServer(String name, int port, ProtocolType protocol, IAppMsgHandler handler){
        try {
            this.name     			= name;
            this.protocol 			= protocol;
            if( protocol != null  ) {
                setServerSupport( port, protocol, handler  );
            }
            else {
                System.out.println(name + " |  CREATED no protocol");
            }
        }catch (Exception e) {
            System.out.println(name+" |  CREATE Error: " + e.getMessage()  );
        }
    }

    private void setServerSupport(int port, ProtocolType protocol, IAppMsgHandler handler){
        if(protocol==ProtocolType.tcp){
            tcpServer = new TcpServer("EnableAsServerTcp"+count++, port, handler);
        }
        else if(protocol==ProtocolType.udp){
            udpServer = new UdpServer("EnableAsServerUdp"+count++, port, handler);
        }
    }

    public void  start() {
        switch( protocol ) {
            case tcp :  { tcpServer.activate();break;}
            case udp: { udpServer.activate();break;}
            default: break;
        }
        isactive = true;
    }

    public void stop() {
        if( ! isactive ) return;
        switch( protocol ) {
            case tcp :  { tcpServer.deactivate();break;}
            case udp:   { udpServer.deactivate();break;}
            default: break;
        }
        isactive = false;
    }

    public String getName(){
        return name;
    }

    public boolean isActive(){
        return isactive;
    }


}
