package com.rixon.tsp;

public interface Coordinate {
	
	Distance getDistance(Coordinate coordinate);
	
	int getX();
	
	int getY();

}
