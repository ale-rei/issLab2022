package it.unibo.radarSystem22.Sprint4.comm.context;

import it.unibo.radarSystem22.Sprint4.comm.interfaces.IAppMsgHandler;
import it.unibo.radarSystem22.Sprint4.comm.interfaces.IContext;
import it.unibo.radarSystem22.Sprint4.comm.interfaces.IContextMsgHandler;
import it.unibo.radarSystem22.Sprint4.comm.tcp.TcpServer;

public class TcpContextServer extends TcpServer implements IContext {
    private IContextMsgHandler ctxMsgHandler;

    public TcpContextServer(String name, String port){
        this (name, Integer.parseInt(port));
    }
    public TcpContextServer(String name, int port){
        super(name, port,  new ContextMsgHandler("ctxH"));
        this.ctxMsgHandler = (ContextMsgHandler) userDefHandler;  
    }
    @Override
    public void addComponent(String name, IAppMsgHandler h) {
        ctxMsgHandler.addComponent(name,h);
    }

    @Override
    public void removeComponent(String name) {
        ctxMsgHandler.removeComponent(name);
    }


}
