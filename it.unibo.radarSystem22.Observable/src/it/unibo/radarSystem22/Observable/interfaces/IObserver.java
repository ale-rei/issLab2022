package it.unibo.radarSystem22.Observable.interfaces;

import it.unibo.radarSystem22.domain.interfaces.IDistance;

public interface IObserver {
	public void update(IDistance d);
}
