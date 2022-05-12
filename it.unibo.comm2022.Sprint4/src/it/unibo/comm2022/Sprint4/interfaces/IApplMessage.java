package it.unibo.comm2022.Sprint4.interfaces;

public interface IApplMessage {

    public String msgId();
    public String msgType();
    public String msgSender();
    public String msgReceiver();
    public String msgContent();
    public String msgNum();

    public boolean isDispatch();
    public boolean isRequest();
    public boolean isReply();
    public boolean isEvent();

    
}
