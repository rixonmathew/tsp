package com.rixon.tsp;

import java.util.List;

public interface ProblemFacade {
	
	void addCity(City city);
	
	List<City> getAllCities();
	
	TravellingSalesmanSolution  solve();
	
	TravellingSalesmanSolution  solve(Algorithm algorithm);

}
