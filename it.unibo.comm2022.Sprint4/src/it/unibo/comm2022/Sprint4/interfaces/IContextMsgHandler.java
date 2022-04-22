package it.unibo.comm2022.Sprint4.interfaces;

public interface IContextMsgHandler extends IAppMsgHandler{
    public void addComponent( String name, IAppMsgHandler h);
    public void removeComponent( String name );
    public IAppMsgHandler getHandler( String name );
}
