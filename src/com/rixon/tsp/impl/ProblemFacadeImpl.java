package com.rixon.tsp.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.rixon.tsp.Algorithm;
import com.rixon.tsp.City;
import com.rixon.tsp.ProblemFacade;
import com.rixon.tsp.TravellingSalesmanProblem;
import com.rixon.tsp.TravellingSalesmanSolution;

public class ProblemFacadeImpl implements ProblemFacade {

	List<City> cityList;
	
	public ProblemFacadeImpl() {
		cityList = new ArrayList<City>();
	}
	
	@Override
	public void addCity(City city) {
		cityList.add(city);
	}

	@Override
	public List<City> getAllCities() {
		return Collections.unmodifiableList(cityList);
	}

	@Override
	public TravellingSalesmanSolution solve() {
		TravellingSalesmanProblem problem = ProblemBuilder.createProblem(cityList);
		return problem.solve();
	}

	@Override
	public TravellingSalesmanSolution solve(Algorithm algorithm) {
		TravellingSalesmanProblem problem = ProblemBuilder.createProblem(cityList);
		problem.setSolutionAlgorithm(algorithm);
		return problem.solve();
	}

	
}
