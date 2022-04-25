package it.unibo.radarSystem22.domain.concrete;

import it.unibo.radarSystem22.domain.interfaces.IDistanceMeasured;
import it.unibo.radarSystem22.domain.interfaces.ISonarForObs;
import it.unibo.radarSystem22.domain.models.SonarModel;
import it.unibo.radarSystem22.domain.utils.Distance;
import it.unibo.radarSystem22.domain.utils.DistanceMeasured;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SonarConcreteForObs extends SonarModel implements ISonarForObs {
    private  BufferedReader reader ;
    private Process p             = null;

    private int lastSonarVal = 0;
    protected IDistanceMeasured observableDistance;


    @Override
    public void activate() {
        System.out.println("SonarConcreteForObs | activate");
        if (p==null) {
            try {
                p = Runtime.getRuntime().exec("sudo ./SonarAlone");
                reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            } catch (Exception e) {
                System.out.println("SonarConcreteForObs | sonarSetUp ERROR" + e.getMessage());
            }
        }
        super.activate();
    }

    @Override
    public void deactivate() {
        System.out.println("SonarConcreteObservable | deactivate");
        if( p != null ) {
            p.destroy();  //Block the runtime process
            p=null;
        }
        super.deactivate();
    }


    @Override
    public IDistanceMeasured getDistance() {
        return observableDistance;
    }

    @Override
    protected void computeDistance() {
        try{
            String data = reader.readLine();
            if (data == null) return;
            int v = Integer.parseInt(data);
            System.out.println("SonarConcreteForObs | v=" +v);
            int lastSonarVal = dist.getVal();
            if(lastSonarVal !=v)
                dist = new Distance(v);
        }catch(Exception e){
            System.out.println("SonarConcreteForObs | "+ e.getMessage());
        }
    }

    @Override
    protected void sonarSetUp() {
        observableDistance = new DistanceMeasured();
        observableDistance.setVal(new Distance(lastSonarVal));
    }
}
