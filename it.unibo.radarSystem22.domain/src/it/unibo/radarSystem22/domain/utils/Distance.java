package it.unibo.radarSystem22.domain.utils;

import it.unibo.radarSystem22.domain.interfaces.*;

public class Distance implements IDistance{

	private int dist;
	
	public Distance(int d) {
		dist = d;
	}

	public Distance(String d){
		dist=Integer.parseInt(d);
	}
	
	@Override
	public int getVal() {
		return dist;
	}

	@Override
	public String toString(){
		return ""+dist;
	}

}
