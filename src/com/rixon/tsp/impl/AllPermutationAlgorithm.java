package com.rixon.tsp.impl;

import java.util.ArrayList;
import java.util.List;

import com.rixon.tsp.Algorithm;
import com.rixon.tsp.City;
import com.rixon.tsp.Path;
import com.rixon.tsp.TravellingSalesmanProblem;
import com.rixon.tsp.TravellingSalesmanSolution;

public class AllPermutationAlgorithm implements Algorithm {

	TravellingSalesmanProblem problem;
	City startCity;
	City endCity;

	
	@Override
	public String getAlgorithmName() {
		return "All Permutations";
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
		
		TravellingSalesmanSolution solution = new TSSConcrete();
		findStartAndEndCities();
		
		List<City> cityList = getCityListForTravel();
		List<int[]> allPermutations = AlgorithmUtils.getPermutations(cityList.size());
		for (int[] indices:allPermutations) {
			Path path = new PathConcrete();
			path.addCity(startCity);
			for (int i=0;i<indices.length;i++){
				path.addCity(cityList.get(indices[i]));
			}
			path.addCity(endCity);
			solution.addPath(path);
		}
		solution.setSolved(true);
		return solution;
		
	}

	private List<City> getCityListForTravel() {
		List<City> cityList = new ArrayList<City>(problem.getCityList());
		cityList.remove(startCity);
		cityList.remove(endCity);
		return cityList;
	}
}
