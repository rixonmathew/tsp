package com.rixon.tsp.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.rixon.tsp.Path;
import com.rixon.tsp.TravellingSalesmanSolution;

public class TSSConcrete implements TravellingSalesmanSolution {
	
	private boolean solved = false;
	List<Path> allPaths = new ArrayList<Path>();
	Path shortestPath;
	long timeTakenToSolve;
		
	public TSSConcrete(){
		timeTakenToSolve = System.nanoTime();
	}
	
	public TSSConcrete(Path path)
	{
		this();
		addPath(path);
	}

	@Override
	public List<Path> getAllPaths() {
		return Collections.unmodifiableList(allPaths);
	}

	@Override
	public void addPath(Path path) {
		allPaths.add(path);
		shortestPath = path;
		for (Path p:allPaths)
		{
			if (p.getPathLength()<shortestPath.getPathLength())
			{
				shortestPath = p;
			}
		}
	}
	
	@Override
	public Path getShortestPath() {
		return shortestPath;
	}

	@Override
	public boolean isSolved() {
		return solved;
	}
	
	public void setSolved(boolean solved)
	{
		this.solved = solved;
		long currentTime = System.nanoTime();
		timeTakenToSolve = currentTime - timeTakenToSolve;
	}

	@Override
	public long getTimeTakenToSolve() {
		return timeTakenToSolve;
	}


}