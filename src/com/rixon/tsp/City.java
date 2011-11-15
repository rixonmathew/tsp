package com.rixon.tsp;

public interface City {

	public String getName();

	public boolean isStart();

	public boolean isEnd();

	public Coordinate getCoordinate();
	
	public Distance getDistance(City city);
}
