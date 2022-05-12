/*
ClientUsingHttp.java
*/
package unibo.wenvUsage22.myBoundaryWalk.alarms;

import unibo.actor22.Qak22Context;
import unibo.actor22.annotations.Actor22;
import unibo.actor22.annotations.Context22;
import unibo.actor22comm.utils.CommSystemConfig;
import unibo.actor22comm.utils.CommUtils;
import unibo.wenvUsage22.common.ApplData;

@Context22(name = "pcCtx", host = "localhost", port = "8083")
@Actor22(name = ApplData.robotName,contextName="pcCtx",implement = V5BoundaryWalkerAnnotAlarms.class)
//@Actor22(name = "sentinel",contextName="pcCtx",implement = Sentinel.class)
public class V5MainBoundaryWalkerAnnotAlarms {
 	

	protected void configure() throws Exception {
 		Qak22Context.configureTheSystem(this);


		Qak22Context.showActorNames();
		CommSystemConfig.tracing = false;
 	}
 
 /*
MAIN
 */
	public static void main(String[] args) throws Exception   {
		CommUtils.aboutThreads("Before start - ");
 		new V5MainBoundaryWalkerAnnotAlarms().configure();
  		CommUtils.aboutThreads("At end - ");
	}
	
 
	
 }
