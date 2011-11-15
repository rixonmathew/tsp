package com.rixon.tsp.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.rixon.tsp.City;
import com.rixon.tsp.Path;

public class PathConcrete implements Path {

	Stack<City> citiesInPath;
	double pathLength = 0.00d;
	
	public PathConcrete()
	{
		citiesInPath = new Stack<City>();
	}
	
	@Override
	public void addCity(City city) {
		if (citiesInPath.contains(city))
			throw new IllegalArgumentException(city+" already exists in the path "+citiesInPath);
		
		//TODO the data type of Distance is coupled in many classes. How to make the type 
		//localized. ( Idea pathLength should not be double it should be of type Distance)
		if (!citiesInPath.isEmpty())
			pathLength += Double.valueOf(citiesInPath.peek().getDistance(city).getValue());
		citiesInPath.add(city);
		
	}

	@Override
	public double getPathLength() {
		return pathLength;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Path["+pathLength+"]");
//		builder.append("\tPathLength   : "+pathLength+"\n");
//		builder.append("\t citiesInPath : {");
//		for(City city:citiesInPath){
//			builder.append("\t"+city+"\n");
//		}
//		builder.append("\t }");
		return builder.toString();
	}

	@Override
	public List<City> getOrderedCityList() {
		List<City> cityList = new ArrayList<City>();
		for(City city:citiesInPath){
			cityList.add(city);
		}
		return cityList;
	}

	@Override
	public String getPathName() {
		StringBuilder builder = new StringBuilder();
		for(City city:citiesInPath){
			builder.append(city.getName());
		}
		builder.append(":"+pathLength);
		return builder.toString();
	}
}
