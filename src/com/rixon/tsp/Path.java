package com.rixon.tsp;

import java.util.List;

public interface Path {

	double getPathLength();
	
	List<City> getOrderedCityList();
	
	void addCity(City city);
	
	String getPathName();
}
