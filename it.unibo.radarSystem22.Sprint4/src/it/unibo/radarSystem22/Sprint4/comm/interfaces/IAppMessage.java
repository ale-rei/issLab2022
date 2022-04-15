package it.unibo.radarSystem22.Sprint4.comm.interfaces;

public interface IAppMessage {
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
