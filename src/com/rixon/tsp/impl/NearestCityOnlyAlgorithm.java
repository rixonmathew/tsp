package com.rixon.tsp.impl;

import java.util.ArrayList;
import java.util.List;

import com.rixon.tsp.Algorithm;
import com.rixon.tsp.City;
import com.rixon.tsp.Path;
import com.rixon.tsp.TravellingSalesmanProblem;
import com.rixon.tsp.TravellingSalesmanSolution;

public class NearestCityOnlyAlgorithm implements Algorithm {

	TravellingSalesmanProblem problem;
	City startCity;
	City endCity;

	@Override
	public String getAlgorithmName() {
		return "Nearest City Only";
	}

	@Override
	public void setProblemDetails(TravellingSalesmanProblem problem) {
		this.problem = problem;
	}

	private void findStartAndEndCities() {
		for (City city:problem.getCityList())
		{
			if (city.isStart()){
				startCity = city;
			}
			if (city.isEnd()) {
				endCity = city;
			}
			//if both are set then break the loop
			if ((startCity != null) && (endCity != null)){
				break;
			}
		}
	}

	
	@Override
	public TravellingSalesmanSolution solve() {
		/**
		 * This solution constructs the path by finding nearest city at each point to move 
		 * forward. This solution might not be the correct solution verify
		 * 1) Identify start and end city.
		 * 2) From starting city iterate through all other cities except start and end 
		 *    and find nearest city in terms of distance
		 * 3) if no cities are found then shortest distance is to end city.
		 */
		TravellingSalesmanSolution solution = new TSSConcrete();
		findStartAndEndCities();
		List<City> cityList = getCityListForTravel();
		Path path = determineLeastDistancePath(cityList);
		solution.addPath(path);
		solution.setSolved(true);
		return solution;
	}
	
	private List<City> getCityListForTravel() {
		List<City> cityList = new ArrayList<City>(problem.getCityList());
		//remove start and end
		cityList.remove(startCity);
		cityList.remove(endCity);
		return cityList;
	}

	private Path determineLeastDistancePath(List<City> cityList) {
		Path path = new PathConcrete();
		City currentCity = startCity,nearestCity;
		path.addCity(currentCity);
		while (cityList.size()>0)
		{
			nearestCity = cityList.get(0);
			for (City city:cityList)
			{
				if (currentCity.getDistance(city).compareTo(currentCity.getDistance(nearestCity)) == -1)
				{
					nearestCity = city;
				}
			}
			path.addCity(nearestCity);
			cityList.remove(nearestCity);
			currentCity = nearestCity;
		}
		path.addCity(endCity);
		return path;
	}
}