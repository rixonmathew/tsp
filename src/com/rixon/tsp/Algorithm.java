package com.rixon.tsp;

public interface Algorithm {
	
	//TODO setting State on strategy will be a problem.
	void setProblemDetails(TravellingSalesmanProblem problem);
	
	TravellingSalesmanSolution solve();
	
	String getAlgorithmName();
}
