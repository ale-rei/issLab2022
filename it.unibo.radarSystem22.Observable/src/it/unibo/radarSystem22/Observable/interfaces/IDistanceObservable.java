package it.unibo.radarSystem22.Observable.interfaces;

public interface IDistanceObservable {
	public void addObserver(IObserver o);
	public void removeObserver(IObserver o);
	public void setVal(int val);
}
