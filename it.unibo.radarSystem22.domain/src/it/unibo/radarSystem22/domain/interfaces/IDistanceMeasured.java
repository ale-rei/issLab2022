package it.unibo.radarSystem22.domain.interfaces;
  
public interface IDistanceMeasured extends IDistance, IObservable{
	public void setVal(IDistance d );
}
