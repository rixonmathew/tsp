package com.rixon.tsp.impl;

import java.util.List;

import com.rixon.tsp.City;
import com.rixon.tsp.TravellingSalesmanProblem;

public class ProblemBuilder {

	public static TravellingSalesmanProblem createProblem(List<City> cityList) {
		return new TSPBasic(cityList);
	}

}
