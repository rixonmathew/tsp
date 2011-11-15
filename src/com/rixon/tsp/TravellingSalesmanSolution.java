package com.rixon.tsp;

import java.util.List;

public interface TravellingSalesmanSolution {

	List<Path> getAllPaths();

	void addPath(Path path);
	
	Path getShortestPath();
	
	boolean isSolved();
	
	void setSolved(boolean solved);
	
	long getTimeTakenToSolve();
	
	
}
