package it.unibo.radarSystem22.domain.mock;

import it.unibo.radarSystem22.domain.interfaces.IDistanceMeasured;
import it.unibo.radarSystem22.domain.interfaces.ISonarForObs;
import it.unibo.radarSystem22.domain.models.SonarModel;
import it.unibo.radarSystem22.domain.utils.BasicUtils;
import it.unibo.radarSystem22.domain.utils.Distance;
import it.unibo.radarSystem22.domain.utils.DistanceMeasured;

public class SonarMockForObs extends SonarModel implements ISonarForObs {

    protected IDistanceMeasured observableDistance;

    @Override
    public IDistanceMeasured getDistance() {
        return observableDistance;
    }
    @Override
    protected void sonarSetUp() {
        observableDistance = new DistanceMeasured( );
        System.out.println("SonarMockForObs | sonarSetUp curVal="+dist);
        observableDistance.setVal(dist);
    }
    @Override
    protected void computeDistance() {
        int v = observableDistance.getVal() - 1;
        System.out.println("SonarMockForObs | produced:"+v);
        observableDistance.setVal(new Distance(v));
        if ( v <= 1 ) {
            deactivate();
        }
        dist = new Distance(v);
        BasicUtils.delay(250);
    }
}
