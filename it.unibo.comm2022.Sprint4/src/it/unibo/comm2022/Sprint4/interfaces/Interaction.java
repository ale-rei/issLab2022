package it.unibo.comm2022.Sprint4.interfaces;

public interface Interaction {
    public void forward(  String msg ) throws Exception;
    public String request(  String msg ) throws Exception;
    public void reply(  String reqid ) throws Exception;
    public String receiveMsg(  ) throws Exception ;
    public void close( )  throws Exception;
}
