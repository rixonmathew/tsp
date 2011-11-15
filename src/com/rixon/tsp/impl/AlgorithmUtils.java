package com.rixon.tsp.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AlgorithmUtils {

	public static List<int[]> getPermutations(int number) {
		List<int[]> allPermutations = new ArrayList<int[]>();
		int[] inputNumbers = new int[number];
		for (int i=0;i<number;i++)
			inputNumbers[i] = i;
		listAllPermutations(allPermutations,inputNumbers);
		return allPermutations;
		
	}
	
	private static void listAllPermutations(List<int[]> allPermutations,int[] inputNumbers)
	{
		int emptyArray[] = new int[0];
		computePermutations(allPermutations,emptyArray,inputNumbers);
		
	}
	

	private static void computePermutations(List<int[]> allPermutations,int[] fixed, int[] variablePool) {
		int n = variablePool.length;
		if (n==0) 
			allPermutations.add(fixed);
		else {
			for (int i=0;i<n;i++){
				int fixed2[] = Arrays.copyOf(fixed, fixed.length+1);
				fixed2[fixed.length] = variablePool[i];
				int variablePool2[] = new int[n-1];
				int t=0;
				for (int j=0;j<n;j++) {
					  if(j!=i)
						  variablePool2[t++] = variablePool[j]; 
				}
				computePermutations(allPermutations,fixed2,variablePool2);
			}
		}
	}

	public static  long factorial(long number) {
		if ( number <=1) return 1;
		if ( number ==2) return 2;
		return number* factorial(number-1);
	}
}
