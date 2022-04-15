package it.unibo.radarSystem22.domain;

import org.junit.*;

import it.unibo.radarSystem22.domain.factory.DeviceFactory;
import it.unibo.radarSystem22.domain.interfaces.ISonar;
import it.unibo.radarSystem22.domain.utils.BasicUtils;
import it.unibo.radarSystem22.domain.utils.DomainSystemConfig;

public class TestSonar {

	@Before
	public void up() {
		System.out.println("up");
	}
	
	@After
	public void down() {
		System.out.println("down");
	}
	@Test
	public void testSonarMock() {
		System.out.println("testSonarMock");
		DomainSystemConfig.simulation = true;
		DomainSystemConfig.sonarDelay = 10; 
		int delta = 1;
		ISonar sonar = DeviceFactory.createSonar();
		new SonarConsumerForTesting( sonar, delta ).start();
		sonar.activate();
		while( sonar.isActive() ) {BasicUtils.delay(100);}  
	}
}

