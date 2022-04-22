package it.unibo.comm2022.Sprint4.interfaces;

public interface IAppMsgHandler {
    public String getName();
    public void elaborate( IAppMessage message, Interaction conn ) ; //utilizzo dopo Context
    public void sendMsgToClient( String message, Interaction conn );
    public void sendAnswerToClient( String message, Interaction conn  );
}
