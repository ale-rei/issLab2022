package it.unibo.radarSystem22.domain.models;

import it.unibo.radarSystem22.domain.concrete.SonarConcrete;
import it.unibo.radarSystem22.domain.interfaces.IDistance;
import it.unibo.radarSystem22.domain.interfaces.ISonar;
import it.unibo.radarSystem22.domain.mock.SonarMock;
import it.unibo.radarSystem22.domain.utils.Distance;
import it.unibo.radarSystem22.domain.utils.DomainSystemConfig;

public abstract class SonarModel implements ISonar{
	protected boolean state = false;
	protected IDistance dist = new Distance(90);

	public static ISonar create() {
		ISonar sonar;
		if( DomainSystemConfig.simulation )  return createSonarMock();
		else  return  createSonarConcrete();
	}

	public static ISonar createSonarMock() {
		System.out.println("SonarMock create");
		return new SonarMock();
	}

	public static ISonar createSonarConcrete(){
		System.out.println("SonarConcrete create");
		return new SonarConcrete();
	}

	@Override
	public void activate() {
		dist = new Distance(90);
		state = true;
		new Thread() {
			public void run(){
				while (state) {
					computeDistance();
				}
			}
		}.start();
	}

	@Override
	public void deactivate() {
		state = false;
	}

	@Override
	public IDistance getDistance() {
		return dist;
	}

	protected abstract void computeDistance();

	protected abstract void sonarSetUp() ;

	@Override
	public boolean isActive() {
		return state;
	}
}
