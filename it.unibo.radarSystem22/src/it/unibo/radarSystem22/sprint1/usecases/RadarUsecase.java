package it.unibo.radarSystem22.sprint1.usecases;

import it.unibo.radarSystem22.domain.interfaces.IDistance;
import it.unibo.radarSystem22.domain.interfaces.IRadarDisplay;

public class RadarUsecase {
    public static void doUsecase(IRadarDisplay radar, IDistance d){
        if(radar != null){
            int distance = d.getVal();
            radar.update(""+distance, "90");
        }

    }
}
