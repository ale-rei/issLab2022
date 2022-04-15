package it.unibo.radarSystem22.Sprint4.comm.interfaces;

public interface IContextMsgHandler extends IAppMsgHandler{
    public void addComponent( String name, IAppMsgHandler h);
    public void removeComponent( String name );
    public IAppMsgHandler getHandler( String name );
}
