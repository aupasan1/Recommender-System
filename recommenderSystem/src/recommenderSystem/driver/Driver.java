package recommenderSystem.driver;

import recommenderSystem.prediction.Initializer;
import recommenderSystem.prediction.ModelBuilder;
import recommenderSystem.util.FileProcessor;
import recommenderSystem.util.FileProcessorInterface;

/**
 * @author Abhishek Niranjan Upasani
 * @version 1.0
 * @since 2016-11-28
 * 
 */
public class Driver {

	private static String inputFile;
	private static String outputFile;
	private static FileProcessorInterface fp = null;
	private static Initializer init = null;
	private static int rows;
	private static int cols;
	private static int[][] input, output;
	private static float[] average;
	private static ModelBuilder mb;
	
	/**
	 * Driver code to run the program
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Validate the arguments
		if(!validate(args)) {
			System.err.println("Usage: java Driver input.txt output.txt");
			System.exit(1);
		} else {
			
			initialize();
			
			// Calculate the running time of the algorithm
			long startTime = System.currentTimeMillis();
						
			// Read input from file and initialize data structures
			init.read(fp, input);
			init.findAverage(input, average, rows, cols);
			
			// Build the model
			mb.findCosineSimilarity(input, average, rows, cols);
			mb.predictValues(input, output, rows, cols);
			
			// Write output to the file
			init.write(fp, output, rows, cols);

			// Close the files
			fp.closeFile();
			
			//Calculate total time time
			long finishTime = System.currentTimeMillis();
			long total_time = finishTime - startTime;
			System.out.println("Total time is " + String.valueOf(total_time) + " ms");
		}	
	}
	
	/**
	 * Initializes all the required data structures 
	 */
	private static void initialize() {
		rows = 944;
		cols = 1683;
		input = new int[rows][cols];
		output = new int[rows][cols];
		average = new float[rows];
		fp = new FileProcessor(inputFile, outputFile);
		init = new Initializer();
		mb = new ModelBuilder();
	}

	/**
	 * Validates the command line arguments
	 * 
	 * @param args
	 * @return flag
	 */
	private static boolean validate(String[] args) {
		
		boolean flag = true;
		
		//Validate the command line arguments
		if(args.length != 2) {
			flag = false;
		} else {
			//Read the command line arguments
			inputFile = args[0];
			outputFile = args[1];
		}
		return flag;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Driver []";
	}
}