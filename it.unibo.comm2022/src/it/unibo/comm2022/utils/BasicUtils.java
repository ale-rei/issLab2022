package it.unibo.comm2022.utils;

public class BasicUtils {

	public static void delay(int n) {
		try {
			Thread.sleep(n);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void aboutThreads(String msg)   {
		String tname    = Thread.currentThread().getName();
		String nThreads = ""+Thread.activeCount() ;
		System.out.println( msg + " curthread=T n=N".replace("T", tname).replace("N", nThreads));
	}
}
