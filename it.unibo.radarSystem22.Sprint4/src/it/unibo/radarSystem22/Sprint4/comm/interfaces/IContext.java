package it.unibo.radarSystem22.Sprint4.comm.interfaces;

public interface IContext {
    public void addComponent( String name, IAppMsgHandler h);
    public void removeComponent( String name );
    public void activate();
    public void deactivate();
}
