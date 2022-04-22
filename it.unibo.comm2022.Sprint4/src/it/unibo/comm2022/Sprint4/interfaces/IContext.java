package it.unibo.comm2022.Sprint4.interfaces;

public interface IContext {
    public void addComponent( String name, IAppMsgHandler h);
    public void removeComponent( String name );
    public void activate();
    public void deactivate();
}
