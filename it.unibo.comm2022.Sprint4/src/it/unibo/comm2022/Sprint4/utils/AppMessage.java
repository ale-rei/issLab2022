package it.unibo.comm2022.Sprint4.utils;

import alice.tuprolog.Struct;
import alice.tuprolog.Term;
import it.unibo.comm2022.Sprint4.interfaces.IAppMessage;
import it.unibo.comm2022.Sprint4.interfaces.Interaction;


public class AppMessage implements IAppMessage {
    protected String msgId       = "";
    protected String msgType     = null;
    protected String msgSender   = "";
    protected String msgReceiver = "";
    protected String msgContent  = "";
    protected int msgNum         = 0;
    
    protected Interaction conn;
	
	public AppMessage(
			String MSGID, String MSGTYPE, String SENDER, String RECEIVER, String CONTENT, String SEQNUM ) {
        this.msgId 		= MSGID;
        this.msgType 	= MSGTYPE;
        this.msgSender 	= SENDER;
        this.msgReceiver = RECEIVER;
        this.msgContent 	= CONTENT;
        this.msgNum      = Integer.parseInt(SEQNUM);
	}
	
	public void setConn( Interaction conn ) {
		if( isRequest() ) this.conn = conn;
		else System.out.println("WARNING: setting conn in a non-request message");
	}
	public Interaction getConn(   ) {
		return conn;
	}	
	
    public AppMessage( String msg ) {
        //msg( MSGID, MSGTYPE, SENDER, RECEIVER, CONTENT, SEQNUM )
    	Struct msgStruct = (Struct) Term.createTerm(msg);
        setFields(msgStruct);
    }
	
    private void setFields( Struct msgStruct ) {
        msgId 		= msgStruct.getArg(0).toString();
        msgType 	= msgStruct.getArg(1).toString();
        msgSender 	= msgStruct.getArg(2).toString();
        msgReceiver = msgStruct.getArg(3).toString();
        msgContent 	= msgStruct.getArg(4).toString();
        msgNum 		= Integer.parseInt(msgStruct.getArg(5).toString());
    }

    public String msgId() {   return msgId; }
    public String msgType() { return msgType; }
    public String msgSender() { return msgSender; }
    public String msgReceiver() { return msgReceiver;  }
    public String msgContent() { return msgContent;  }
    public String msgNum() { return "" + msgNum; } 
    
    
    public boolean isEvent(){
        return msgType.equals( AppMessageType.event.toString() );
    }
    public boolean isDispatch(){
        return msgType.equals( AppMessageType.dispatch.toString() );
    }
    public boolean isRequest(){
        return msgType.equals( AppMessageType.request.toString() );
    }
    public boolean isInvitation(){
        return msgType.equals( AppMessageType.invitation.toString() );
    }
    public boolean isReply(){
        return msgType.equals( AppMessageType.reply.toString() );
    }   
    
    public String toString() {
    	return "msg($msgId,$msgType,$msgSender,$msgReceiver,$msgContent,$msgNum)"
    			.replace("$msgId",msgId).replace("$msgType",msgType)
    			.replace("$msgSender",msgSender).replace("$msgReceiver",msgReceiver)
    			.replace("$msgContent",msgContent).replace("$msgNum",""+msgNum);
    }

}
