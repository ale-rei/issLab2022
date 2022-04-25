package it.unibo.radarSystem22.domain.mock;

import it.unibo.radarSystem22.domain.interfaces.*;
import it.unibo.radarSystem22.domain.models.SonarModel;
import it.unibo.radarSystem22.domain.utils.BasicUtils;
import it.unibo.radarSystem22.domain.utils.Distance;

// Diverso dal prof
public class SonarMock extends SonarModel implements ISonar{
	private int step = 1;
	public void computeDistance() {
		int v = dist.getVal()-step;
		dist = new Distance(v);
		if ( v <= 1 ) {
			deactivate();
		}
		BasicUtils.delay(250);
	}

	@Override
	protected void sonarSetUp() {
		dist = new Distance(90);
	}

	@Override
	public IDistance getDistance(){return dist;}
}
