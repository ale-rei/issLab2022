package it.unibo.comm2022.utils;

public class CommUtils {
 	
	public static boolean isCoap() {
		return CommSystemConfig.protcolType==ProtocolType.coap ;
	}
	public static boolean isMqtt() {
		return CommSystemConfig.protcolType==ProtocolType.mqtt ;
	}
	public static boolean isTcp() {
		return CommSystemConfig.protcolType==ProtocolType.tcp ;
	}
	public static boolean isUdp(){ return CommSystemConfig.protcolType == ProtocolType.udp;}

}
