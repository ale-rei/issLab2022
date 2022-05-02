package it.unibo.radarSystem22.domain.models;

import it.unibo.radarSystem22.domain.concrete.LedConcrete;
import it.unibo.radarSystem22.domain.interfaces.ILed;
import it.unibo.radarSystem22.domain.mock.LedMock;
import it.unibo.radarSystem22.domain.mock.LedMockWithGui;
import it.unibo.radarSystem22.domain.utils.DomainSystemConfig;

public abstract class LedModel implements ILed{

	public boolean state =false;
	
	//Factory method
	public static ILed create() {
		ILed led;
		if(DomainSystemConfig.simulation) led = createLedMock();
		else led = createLedConcrete();
		return led;
	}

	private static ILed createLedMock() {
		System.out.println("LedMock create");
		if (DomainSystemConfig.ledGui) return LedMockWithGui.createLed();
		else return new LedMock();
	}
	public static ILed createLedConcrete(){
		System.out.println("LedConcrete create");
		return new LedConcrete();
	}

	protected abstract void ledActivate(boolean state);
	
	@Override
	public void turnOn() {
		state = true;
		ledActivate(state);
	}
	@Override
	public void turnOff() {
		state = false;
		ledActivate(state);
	}

	@Override
	public boolean getState() {
		return state;
	}
}
