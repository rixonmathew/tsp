package com.rixon.tsp;


import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.rixon.tsp.impl.AlgorithmProvider;
import com.rixon.tsp.impl.AlgorithmUtils;
import com.rixon.tsp.impl.ProblemBuilder;
import com.rixon.tsp.impl.XYCity.CityBuilder;

public class TravellingSalesmanProblemVerifier {

	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void validateSolutionForBasicAlgorithm() 
	{
		List<City> cityList = getMockListOfCities(40);
		assertNotNull(cityList);
		assertTrue("Atleast two cities should exist",cityList.size()>1);
		TravellingSalesmanProblem problem = createTravellingSalesmanProblem(cityList);
		assertNotNull(problem);		
		TravellingSalesmanSolution solution = problem.solve();
		assertNotNull(solution);
		validatSmallestPath(solution);
	}

	@Test
	public void validateSolutionForAllPermutations()
	{
		int cityCount = 10;
		List<City> cityList = getMockListOfCities(cityCount);
		assertNotNull(cityList);
		TravellingSalesmanProblem problem = createTravellingSalesmanProblem(cityList);
		assertNotNull(problem);

		Algorithm allPermutations = AlgorithmProvider.allPermutationAlgorithm(); 
		problem.setSolutionAlgorithm(allPermutations);
		TravellingSalesmanSolution solution = problem.solve();
		validatePathCount(solution,cityCount);
		
		//Solve using nearestCityAlgo and see if the path is least or not
		System.out.println("Solving the same problem using nearestCity Algorithm");
		problem.setSolutionAlgorithm(AlgorithmProvider.nearestCityOnly());
		solution = problem.solve();
		System.out.println(solution.getShortestPath());
	}


	
	private void validatePathCount(TravellingSalesmanSolution solution,int cityCount) {
		List<Path> allPath = solution.getAllPaths();
		long solutionCount = AlgorithmUtils.factorial(cityCount-2);
		assertEquals("Number of paths is not correct",solutionCount,allPath.size());
		
		//just print all paths and their length
		for(Path p:allPath) {
			System.out.println("Path length:["+p.getPathLength()+"]");
			System.out.println(p);
		}
		System.out.println("Time taken to solve (ms) :"+(double)solution.getTimeTakenToSolve()*1e-6);
	}

	private void validatSmallestPath(TravellingSalesmanSolution solution) {
		List<Path> allPaths = solution.getAllPaths();
		assertNotNull(allPaths);
		assertTrue("Atleast one path should exist",allPaths.size()>0);
		Path shortestPath = solution.getShortestPath();		
		assertNotNull(shortestPath);
		System.out.println("Time taken to solve (ms) :"+(double)solution.getTimeTakenToSolve()*1e-6);
		System.out.println("Shortest path is :"+shortestPath);
	}

	private TravellingSalesmanProblem createTravellingSalesmanProblem(
			List<City> cityList) {
		TravellingSalesmanProblem problem = ProblemBuilder.createProblem(cityList);
		return problem;
	}

	private List<City> getMockListOfCities(int cityCount) {
		List<City> cities = new ArrayList<City>();
		CityBuilder cityBuilder = new CityBuilder();
		cities.add(cityBuilder.setName("A").setStart(true).setEnd(false).setX(0).setY(0).build());
		cities.add(cityBuilder.setName("C").setStart(false).setEnd(true).setX(400).setY(400).build());
		cityCount-=2;
		for(int i=1;i<=cityCount;i++)
			cities.add(getCityWithRandomCoordinates(cityBuilder, "B"+i, false, false));
		return cities;
	}
	
	private City getCityWithRandomCoordinates(CityBuilder cityBuilder,String cityName,boolean start,boolean end)
	{
		int x,y;
		x = (int) (Math.random()*200.00d);
		y = (int) (Math.random()*200.00d);
		return cityBuilder.setName(cityName).setStart(start).setEnd(end).setX(x).setY(y).build();
	}
	
	@Test
	public void testPermutations(){
//		permutation("1234");
		List<String> cityList = new ArrayList();
		cityList.add("A1");
		cityList.add("B1");
		cityList.add("B2");
//		cityList.add("B3");
//		cityList.add("B4");
//		cityList.add("C1");
		List<int[]> allPermutations =  AlgorithmUtils.getPermutations(cityList.size());
		{
			int i=1;
			for(int[] indices:allPermutations) {
				System.out.println("Permutation # :"+i++);
				for (int j=0;j<indices.length;j++)
					System.out.print(cityList.get(indices[j])+",");
				System.out.println("");
			}
		}
	}
	
	
//	private List<int[]> getPermutations(List cityList) {
//		List<int[]> permutations = new ArrayList<int[]>();
//		int[] cityIndexes = new int[cityList.size()];
//		for (int i=0;i<cityList.size();i++)
//			cityIndexes[i] = i;
//		listAllPermutations(permutations,cityIndexes);
//		return permutations;
//		
//	}
//	
//	private void listAllPermutations(List<int[]> permutations,int[] cityIndexes)
//	{
//		int emptyArray[] = new int[0];
//		computePermutations(permutations,emptyArray,cityIndexes);
//		
//	}
//	
//
//	private void computePermutations(List<int[]> permutations,int[] emptyArray, int[] cityIndexes) {
//		int n = cityIndexes.length;
//		if (n==0) 
//			permutations.add(emptyArray);
//		else {
//			for (int i=0;i<n;i++){
//				int emptyArray2[] = Arrays.copyOf(emptyArray, emptyArray.length+1);
//				emptyArray2[emptyArray.length] = cityIndexes[i];
//				int cityIndexes2[] = new int[n-1];
//				int t=0;
//				for (int j=0;j<n;j++) {
//					  if(j!=i)
//						  cityIndexes2[t++] = cityIndexes[j]; 
//				}
//				computePermutations(permutations,emptyArray2,cityIndexes2);
//			}
//		}
//	}

//	public  static void permutation(String str) { 
//	    permutation("", str); 
//	 }
//
//	 private static void permutation(String prefix, String str) {
//	    int n = str.length();
//	    if (n == 0) System.out.println(prefix);
//	    else {
//	        for (int i = 0; i < n; i++)
//	           permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i+1, n));
//	    }
//
//	}
//	 
//	 
//	 private static void permutation2(List prefix, List str) {
//		 	int n = str.size();
//		    if (n == 0)   printList(prefix);
//		    else {
//		        for (int i = 0; i < n; i++){
//		        	List prefix2 = new ArrayList(prefix);
//		        	prefix2.add(str.get(i));
//		        	List str2 = new ArrayList();
//		        	for (int j=0;j<n;j++) {
//		        		if (j!=i)
//		        			str2.add(str.get(j));
//		        	}
//		        	permutation2(prefix2, str2);	
//		        }
//		    }
//
//		}
//	 
//	private static void printList(List list){
//		 int size = list.size();
//		 for (int i=0;i<size;i++){
//			 System.out.print(list.get(i)+",");
//		 }
//		 System.out.println("");
//	 }

}