package it.unibo.comm2022.Sprint4.context;


import it.unibo.comm2022.Sprint4.interfaces.IAppMsgHandler;
import it.unibo.comm2022.Sprint4.interfaces.IContext;
import it.unibo.comm2022.Sprint4.interfaces.IContextMsgHandler;
import it.unibo.comm2022.Sprint4.tcp.TcpServer;

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
