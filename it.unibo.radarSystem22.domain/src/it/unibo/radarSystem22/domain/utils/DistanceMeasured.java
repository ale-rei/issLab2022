package it.unibo.radarSystem22.domain.utils;

import it.unibo.radarSystem22.domain.interfaces.IDistance;
import it.unibo.radarSystem22.domain.interfaces.IDistanceMeasured;
import it.unibo.radarSystem22.domain.interfaces.IObserver;
import java.util.Observable;

@SuppressWarnings("deprecation")
public class DistanceMeasured extends Observable implements IDistanceMeasured {
    private IDistance dist;


    @Override
    public int getVal() {
        return dist.getVal();
    }
    @Override
    public String toString() {
        return ""+ getVal();
    }

    @Override
    public void setVal(IDistance val) {
        dist = val;
        setChanged();
        notifyObservers(dist);
    }

    @Override
    public void addObserver(IObserver obs) {
        super.addObserver(obs);
    }

    @Override
    public void deleteObserver(IObserver obs) {
        super.deleteObserver(obs);
    }
}
