package com.rixon.tsp.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.rixon.tsp.Algorithm;
import com.rixon.tsp.City;
import com.rixon.tsp.Path;
import com.rixon.tsp.TravellingSalesmanProblem;
import com.rixon.tsp.TravellingSalesmanSolution;

public class TSPBasic implements TravellingSalesmanProblem {

	List<City> cities = new ArrayList<City>();
	Algorithm algorithm;
	
	public TSPBasic(List<City> cities)
	{
		if (cities != null) {
			this.cities.addAll(cities);
		}
	}

	@Override
	public List<City> getCityList() {
		return Collections.unmodifiableList(cities);
	}
	
	@Override
	public void setSolutionAlgorithm(Algorithm algorithm) {
		this.algorithm = algorithm;
		this.algorithm.setProblemDetails(this);
	}

	@Override
	public TravellingSalesmanSolution solve() {
		if (algorithm == null) {
			algorithm = AlgorithmProvider.nearestCityOnly();
			algorithm.setProblemDetails(this);
		}
		return algorithm.solve();
	}

	@Override
	public String toString() {
		return "TSPBasic [cities=" + cities + "]";
	}

	
}
