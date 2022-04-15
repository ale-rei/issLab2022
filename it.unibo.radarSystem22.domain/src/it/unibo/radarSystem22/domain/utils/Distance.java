package it.unibo.radarSystem22.domain.utils;

import it.unibo.radarSystem22.domain.interfaces.*;

public class Distance implements IDistance{

	public int dist;
	
	public Distance(int dist) {
		this.dist = dist;
	}

	public Distance(String dist){
		this.dist=Integer.parseInt(dist);
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
