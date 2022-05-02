package it.unibo.radarSystem22.domain;

import it.unibo.radarSystem22.domain.interfaces.IObserver;
import it.unibo.radarSystem22.domain.interfaces.ISonarForObs;
import it.unibo.radarSystem22.domain.utils.DomainSystemConfig;

import java.util.Observable;

import static org.junit.Assert.assertTrue;


class SonarObserverForTesting implements IObserver{
	private String name;
	private boolean oneShot = false;
	private int v0          = -1;
	private int delta       =  1;
 	private ISonarForObs sonar;
	
	public SonarObserverForTesting(String name, ISonarForObs sonar, boolean oneShot) {
		this.name    = name;
 		this.sonar   = sonar;
		this.oneShot = oneShot;
	}

	@Override
	public void update(Observable source, Object data) {
		System.out.println( name + " | update data=" + data); //+ " from " + source
		 update( data.toString() );
	}

	@Override
	public void update(String vs) {
 		 if(oneShot) {
			 System.out.println( name + "| oneShot value=" + vs);
 			 assertTrue(  vs.equals( ""+DomainSystemConfig.testingDistance) );	
 		 }else {
 			 int value = Integer.parseInt(vs);
 			 if( v0 == -1 ) {	//set the first value observed
 				v0 = value;
				 System.out.println( name + "| v0=" + v0);
 			 }else {
				 System.out.println( name + "| value=" + value);
  				int vexpectedMin = v0-delta;
 				int vexpectedMax = v0+delta;
 				assertTrue(  value <= vexpectedMax && value >= vexpectedMin );
 				v0 = value;			 
 				if( v0 == 70 && name.equals("obs1")) sonar.getDistance().deleteObserver(this);
 				if( v0 == 50 && name.equals("obs2")) sonar.getDistance().deleteObserver(this);
 			 }
 		 }
	}
	
}

