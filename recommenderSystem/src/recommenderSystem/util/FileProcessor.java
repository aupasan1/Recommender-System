package recommenderSystem.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author Abhishek Niranjan Upasani
 * @version 1.0
 * @since 2016-11-28
 * 
 */
public class FileProcessor implements FileProcessorInterface {

	private BufferedReader br;
	private BufferedWriter bw;
	
	/**
	 * Constructor of the File Processor class
	 */
	public FileProcessor(String inputFileIn, String outputFileIn) {
		try {
			br = new BufferedReader(new FileReader(inputFileIn));
			bw =new BufferedWriter(new FileWriter(outputFileIn));
		} catch (FileNotFoundException e) {
			System.err.println("Unable to open file '" + inputFileIn + "'");
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Unable to write to file '" + outputFileIn + "'");
			System.exit(1);
		} finally {
		
		}

	}
	
	/* (non-Javadoc)
	 * @see recommenderSystem.util.FileProcessorInterface#readFromFile()
	 */
	@Override
	public String readFromFile() {
		String line = null;
		try {
			line = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		} finally {
		}		
		return line;

	}

	/* (non-Javadoc)
	 * @see recommenderSystem.util.FileProcessorInterface#writeToFile(java.lang.String)
	 */
	@Override
	public void writeToFile(String lineIn) {
		
		try {
			bw.write(lineIn);
			bw.newLine();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		} finally {
		}
	}

	/* (non-Javadoc)
	 * @see recommenderSystem.util.FileProcessorInterface#closeFile()
	 */
	public void closeFile() {
		try {
			if (br != null) {
				br.close();
			}
			if (bw != null) {
				bw.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		} finally {
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FileProcessor [br=" + br + ", bw=" + bw + "]";
	}
}