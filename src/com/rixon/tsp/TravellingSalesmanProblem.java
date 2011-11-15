package com.rixon.tsp;

import java.util.List;

public interface TravellingSalesmanProblem {

	TravellingSalesmanSolution solve();
	
	List<City> getCityList();

	void setSolutionAlgorithm(Algorithm allPermutations);
}
