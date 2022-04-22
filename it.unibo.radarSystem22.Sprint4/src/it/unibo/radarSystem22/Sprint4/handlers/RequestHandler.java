package it.unibo.radarSystem22.Sprint4.handlers;

import it.unibo.comm2022.Sprint4.interfaces.IAppMessage;
import it.unibo.comm2022.Sprint4.interfaces.Interaction;
import it.unibo.comm2022.Sprint4.utils.AppMsgHandler;
import it.unibo.comm2022.Sprint4.utils.BasicUtils;
import it.unibo.comm2022.Sprint4.utils.CommUtils;



public class RequestHandler extends AppMsgHandler {
private boolean firstRequest = true;
	public RequestHandler(String name) {
		super(name);
 	}

	@Override
	public void elaborate(IAppMessage message, Interaction conn) {
 		if( message.isRequest() ) {
			 elabRequest(message,conn);
 		}
	}
	
	protected void elabRequest(IAppMessage message, Interaction conn) {
		new Thread() {
			public void run() {
	 			try {
				    if(  firstRequest ) {
						firstRequest = false;
						BasicUtils.delay(3000);
					}
	 		 		System.out.println(name + " elaborate "+ message);
	 			    IAppMessage answer = CommUtils.prepareReply( message, message.msgContent()+"_done");
	 		 		System.out.println(name + " sending "+answer);
					conn.reply(answer.toString());
				} catch (Exception e) {
					System.out.println(name + "elaborate ERROR:" + e.getMessage());
				}			 
			}//run
		}.start();
	}
}
