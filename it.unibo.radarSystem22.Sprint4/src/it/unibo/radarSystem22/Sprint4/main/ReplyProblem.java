package it.unibo.radarSystem22.Sprint4.main;

import it.unibo.comm2022.Sprint4.enablers.EnablerContext;
import it.unibo.comm2022.Sprint4.interfaces.IAppMessage;
import it.unibo.comm2022.Sprint4.interfaces.IAppMsgHandler;
import it.unibo.comm2022.Sprint4.proxy.ProxyAsClient;
import it.unibo.comm2022.Sprint4.utils.BasicUtils;
import it.unibo.comm2022.Sprint4.utils.CommUtils;
import it.unibo.comm2022.Sprint4.utils.ProtocolType;
import it.unibo.radarSystem22.Sprint4.handlers.RequestHandler;

public class ReplyProblem {
private int port = 8000;
private ProxyAsClient pxy;
private EnablerContext server;

	public void doJob() {
		configure();
		execute();
		terminate();
	}
	
	protected void configure() {
		//Attivo un server con Handler che attiva thread
		IAppMsgHandler rh1      = new RequestHandler("rh1");
		server  = new EnablerContext( "ctx", ""+port, ProtocolType.tcp );
		server.addComponent("rh1", rh1);
 		server.activate();

		pxy = new ProxyAsClient("pxy", "localhost", ""+port, ProtocolType.tcp);
	    BasicUtils.aboutThreads( "After configure   "  );
	}
 
	protected void execute() {
	    BasicUtils.aboutThreads( "Before execute  "  );
		//Attivo due Thread di richiesta		
		new Thread() {
			public void run() {
				String thname = getName().toLowerCase().replace("-", "");
			    BasicUtils.aboutThreads(thname + " | Before request   "  );
				IAppMessage m    = CommUtils.buildRequest(thname, "req", "r1","rh1");
				String answer     = pxy.sendRequestOnConnection(m.toString());
			    BasicUtils.aboutThreads(thname + " | After answer for " + m);
				System.out.println(thname + " answer to r1:" + answer);
			}
		}.start();
		new Thread() {
			public void run() {
				String thname = getName().toLowerCase().replace("-", "");
			    BasicUtils.aboutThreads(thname + " | Before request   "  );
 				IAppMessage m = CommUtils.buildRequest(thname, "req", "r2","rh1");
				String answer  = pxy.sendRequestOnConnection(m.toString());
			    BasicUtils.aboutThreads(thname + " | After answer for " + m);
				System.out.println(thname + " answer to r2:" + answer);
			}
		}.start();
		
		
	}
	
	protected void terminate() {
		BasicUtils.delay(5000);
		System.out.println("TERMINATE");
		server.deactivate();
		pxy.close();
		
	}
	
	public static void main( String[] args) throws Exception {
		new ReplyProblem().doJob( );
 	}

	
}
