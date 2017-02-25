package recommenderSystem.util;

/**
 * @author Abhishek Niranjan Upasani
 * @version 1.0
 * @since 2016-11-28
 * 
 */
public interface FileProcessorInterface {
	
	/**
	 * 
	 * Reads a line from input file
	 * @return 
	 */
	public String readFromFile();
	
	/**
	 * Writes a line to output file
	 * @param lineIn
	 */
	public void writeToFile(String lineIn);
	
	/**
	 * Closes any open files
	 */
	public void closeFile();
}
