package com.rixon.tsp.impl;

import java.util.HashMap;
import java.util.Map;

import com.rixon.tsp.Algorithm;

public class AlgorithmProvider {

	public final static String ALL_PERMUTATIONS = "All Permutations";
	public  final static String NEAREST_CITY_ONLY = "Neearest City Only";
	
	private static Map<String,Algorithm> algorithms = new HashMap<String, Algorithm>();
	
	public static Algorithm allPermutationAlgorithm() {
		// TODO Auto-generated method stub
		Algorithm allPermutations;
		if (algorithms.containsKey(ALL_PERMUTATIONS)) {
			allPermutations = algorithms.get(ALL_PERMUTATIONS);
		} else {
			allPermutations = new AllPermutationAlgorithm();
			algorithms.put(ALL_PERMUTATIONS, allPermutations);
		}
		return allPermutations;

	}
	
	public static Algorithm nearestCityOnly() {
		// TODO Auto-generated method stub
		Algorithm nearestCityOnly;
		if (algorithms.containsKey(NEAREST_CITY_ONLY)) {
			nearestCityOnly = algorithms.get(NEAREST_CITY_ONLY);
		} else {
			nearestCityOnly = new NearestCityOnlyAlgorithm();
			algorithms.put(NEAREST_CITY_ONLY, nearestCityOnly);
		}
		return nearestCityOnly;
	}
	
	public static Algorithm getNamedAlgorithm(String algorithmName) {
		if (algorithmName ==ALL_PERMUTATIONS ) {
			return allPermutationAlgorithm();
		} else if (algorithmName ==NEAREST_CITY_ONLY ){
			return nearestCityOnly();
		}
		return nearestCityOnly();
	}

}
