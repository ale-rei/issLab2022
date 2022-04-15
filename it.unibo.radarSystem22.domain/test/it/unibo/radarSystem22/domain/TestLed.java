package it.unibo.radarSystem22.domain;


import static org.junit.Assert.assertTrue;

import it.unibo.radarSystem22.domain.utils.DomainSystemConfig;
import org.junit.Test;

import it.unibo.radarSystem22.domain.factory.DeviceFactory;
import it.unibo.radarSystem22.domain.interfaces.ILed;
import it.unibo.radarSystem22.domain.utils.BasicUtils;

public class TestLed {
	
	@Test
	public void testLedMock() {
		System.out.println("testLedMock");
		DomainSystemConfig.simulation = false;
		DomainSystemConfig.ledGui = true;
		ILed led = DeviceFactory.createLed();
		assertTrue(!led.getState());
		led.turnOn();
		
		assertTrue(led.getState());
		BasicUtils.delay(1000);
		
		led.turnOff();
		assertTrue(!led.getState());
		BasicUtils.delay(1000);
	}
	
}
