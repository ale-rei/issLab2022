package it.unibo.comm2022.Sprint4.utils;

import it.unibo.comm2022.Sprint4.interfaces.IAppMessage;

public class CommUtils {
	
	private static int msgNum=0;
	
	public static String getContent( String msg ) {
		String result = "";
		try {
			AppMessage m = new AppMessage(msg);
			result        = m.msgContent();
		}catch( Exception e) {
			result = msg;
		}
		return result;	
	}
	
	public static IAppMessage buildDispatch(String sender, String msgId, String payload, String dest) {
		try {
			return new AppMessage(msgId, AppMessageType.dispatch.toString(),sender,dest,payload,""+(msgNum++));
		} catch (Exception e) {
			System.out.println("buildDispatch ERROR:"+ e.getMessage());
			return null;
		}
	}
	
	public static IAppMessage buildRequest(String sender, String msgId, String payload, String dest) {
		try {
			return new AppMessage(msgId, AppMessageType.request.toString(),sender,dest,payload,""+(msgNum++));
		} catch (Exception e) {
			System.out.println("buildRequest ERROR:"+ e.getMessage());
			return null;
		}
	}
	
	public static IAppMessage prepareReply(IAppMessage requestMsg, String answer) {
		String sender  = requestMsg.msgSender();
		String receiver= requestMsg.msgReceiver();
		String reqId   = requestMsg.msgId();
		IAppMessage reply = null;
		if( requestMsg.isRequest() ) { 
 			reply = buildReply(receiver, reqId, answer, sender) ;
		}else { 
			System.out.println( "Utils | prepareReply ERROR: message not a request");
		}
		return reply;
    }
	
	public static IAppMessage buildReply(String sender, String msgId, String payload, String dest) {
		try {
			return new AppMessage(msgId, AppMessageType.reply.toString(),sender,dest,payload,""+(msgNum++));
		} catch (Exception e) {
			System.out.println("buildRequest ERROR:"+ e.getMessage());
			return null;
		}
	}	
}
