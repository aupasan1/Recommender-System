package recommenderSystem.prediction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.List;
import java.util.HashMap;

/**
 * @author Abhishek Niranjan Upasani
 * @version 1.0
 * @since 2016-11-28
 * 
 */
public class ModelBuilder {
	
	Map<Integer, List<Integer>> map;
	double [][] similarity;
	
	/**
	 * Constructor of the Model Builder class
	 */
	public ModelBuilder() {
		map = new HashMap<Integer, List<Integer>>();
		similarity = new double[1683][1683];
	}
	
	/**
	 * Finds the cosine similarity between two items
	 * 
	 * @param input
	 * @param average
	 * @param rows
	 * @param cols
	 */
	public void findCosineSimilarity(int[][] input, float[] average, int rows, int cols) {
		
		double dotProduct = 0.0;
		double normA = 0.0;
		double normB = 0.0;
		double isSimilar = 0.0;
		
		for(int i = 1; i < cols; i++) {
			for(int j = 1; j < cols; j++) {
				for(int k = 1; k < rows; k++) {
					
					//Check if both the items are rated by the user
					if(input[k][i] !=0 && input[k][j] != 0) {
						dotProduct += (input[k][i] - average[k]) * (input[k][j] - average[k]);
						normA += Math.pow((input[k][i] - average[k]), 2);
						normB += Math.pow((input[k][j] - average[k]), 2);
					}
				}
				
				// Apply the adjacent cosine similarity formula
				isSimilar = dotProduct/(Math.sqrt(normA) * Math.sqrt(normB));
				
				//Check if the similarity is greater than threshold value
				if(isSimilar >= 0.3) {
					addItem(map, i, j);
				}
				
				//Add the value to the similarity matrix
				similarity[i][j] = isSimilar;
				dotProduct = 0.0;
				normA = 0.0;
				normB = 0.0;
				isSimilar = 0.0;
			}
		}
	}

	/**
	 * 
	 * Adds item2 to similarity list of item1
	 * 
	 * @param map
	 * @param item1
	 * @param item2
	 */
	private void addItem(Map<Integer, List<Integer>> map, Integer item1, Integer item2) {
		
		if(map.containsKey(item1)) {
			map.get(item1).add(item2);
		} else {
			List <Integer> list = new ArrayList<Integer>();
			list.add(item2);
			map.put(item1, list);
		}
	}

	/**
	 * 
	 * Finds the prediction for a user i and item j
	 * 
	 * @param input
	 * @param output
	 * @param rows
	 * @param cols
	 */
	public void predictValues(int[][] input, int[][] output, int rows, int cols) {
		
		double missingValue = 0.0;
		int finalValue = 0;
		
		List<Integer> list;
		for (int i = 1; i < rows; i++) {
			for (int j = 1; j < cols; j++) {
				
				//Predict the value only if zero. Otherwise, assign the input value
				if(input[i][j] == 0) {
					
					// Get the similar items for item j
					list = map.get(j);
					
					// Check if item list is null. Assign 1 if list is null
					if (list != null) {
						missingValue = calculateWeightedSum(input, i, j, list);
						finalValue = (int) Math.round(missingValue);
						
						//Round up the predicted values
						if (finalValue < 1) {
							output[i][j] = 1;
						}
						else if (finalValue > 5) {
							output[i][j] = 5;
						}
						else {
							output[i][j] = finalValue;
						}
					} else {
						output[i][j] = 1;
					}
				} else {
					output[i][j] = input[i][j];
				} 
			}
		}
	}

	/**
	 * 
	 * Calculates the weighted sum for user i and item j
	 * 
	 * @param input
	 * @param i
	 * @param j
	 * @param list
	 * @return
	 */
	private double calculateWeightedSum(int[][] input, int i, int j, List<Integer> list) {

		double numerator = 0.0;
		double denominator = 0.0;
		double value = 0.0;
		int item = 0;
		
		for (int k = 0; k < list.size(); k++) {
			item = list.get(k);
			
			//Calculate the weighted sum only if the user has rated the item
			if (input[i][item] != 0) {
				
				//Multiply similarity value of given item with rating
				numerator += ((similarity[j][item]) * (input[i][item]));

				// Calculate summation of absolute value of similarities
				denominator += Math.abs(similarity[j][item]);
			}
		}
		
		// Apply the weighted sum formula
		value = numerator / denominator;
		
		return value;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ModelBuilder [map=" + map + ", similarity="
				+ Arrays.toString(similarity) + "]";
	}
}