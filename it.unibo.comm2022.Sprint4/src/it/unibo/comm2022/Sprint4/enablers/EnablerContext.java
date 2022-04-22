package it.unibo.comm2022.Sprint4.enablers;


import it.unibo.comm2022.Sprint4.context.ContextMsgHandler;
import it.unibo.comm2022.Sprint4.interfaces.IAppMsgHandler;
import it.unibo.comm2022.Sprint4.interfaces.IContext;
import it.unibo.comm2022.Sprint4.interfaces.IContextMsgHandler;
import it.unibo.comm2022.Sprint4.tcp.TcpServer;
import it.unibo.comm2022.Sprint4.udp.UdpServer;
import it.unibo.comm2022.Sprint4.utils.ProtocolType;

public class EnablerContext implements IContext {
    protected IContextMsgHandler ctxMsgHandler;
    protected String name;
    protected ProtocolType protocol;
    protected TcpServer serverTcp;
    protected UdpServer serverUdp;
    protected boolean isactive = false;

    public EnablerContext(String name, String port, ProtocolType protocol){
        this(name,port,protocol,new ContextMsgHandler("ctxH"));
    }

    public EnablerContext(String name, String port, ProtocolType protocol, IContextMsgHandler handler){
        try {
            this.name     			= name;
            this.protocol 			= protocol;
            ctxMsgHandler           = handler;
            if( protocol != null ) {
                setServerSupport( port, protocol, handler  );
            }else System.out.println(name+" |  CREATED no protocol"  );
        } catch (Exception e) {
            System.out.println(name+" |  CREATE Error: " + e.getMessage()  );
        }
    }

    protected void setServerSupport( String portStr, ProtocolType protocol, IContextMsgHandler handler) throws Exception{
        if( protocol == ProtocolType.tcp  ) {
            int port = Integer.parseInt(portStr);
            serverTcp = new TcpServer("ctxTcp" , port,  handler);
            System.out.println(name+" |  CREATED  on port=" + port + " protocol=" + protocol + " handler="+handler);
        }
        else if (protocol == ProtocolType.udp){
            int port = Integer.parseInt(portStr);
            serverUdp = new UdpServer("ctxUdp",port,handler);
            System.out.println(name+" |  CREATED  on port=" + port + " protocol=" + protocol + " handler="+handler);
        }

    }

    public String getName() {
        return name;
    }

    public boolean isActive() {
        return isactive;
    }

    @Override
    public void addComponent( String devname, IAppMsgHandler h) {
        ctxMsgHandler.addComponent(devname, h);
    }

    @Override
    public void removeComponent( String devname ) {
        ctxMsgHandler.removeComponent( devname );
    }

    @Override
    public void activate() {
        System.out.println(name+" |  activate ..." );
        switch( protocol ) {
            case tcp :  { serverTcp.activate();break;}
            case udp: {serverUdp.activate(); break;}
            default: break;
        }
        isactive = true;
    }

    @Override
    public void deactivate() {

        if( ! isactive ) return;
        switch( protocol ) {
            case tcp :  { serverTcp.deactivate();break;}
            case udp :  { serverUdp.deactivate();break;}
            default: break;
        }
        isactive = false;
    }
}

