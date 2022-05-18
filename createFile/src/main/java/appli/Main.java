package appli;

import java.io.File;
import java.util.Scanner;

import file.AccessFile;
import file.OutputFile;

public class Main {
	// user input for number of lines
	private int numLines;

	// output file name
	private static final String OUTPUT_FILE_NAME = "outputData.txt";
	
	// megabyte
	private static final long MEGABYTE = 1024L * 1024L;

	// Scanner object
	private static Scanner sc;

	public Main() {
	}
	
	/**
	 * bytesToMegabytes method
	 * Change bytes to megabytes
	 * 
	 */
	public static long bytesToMegabytes(long bytes) {
		return bytes / MEGABYTE;
	}

	public static void main(String[] args) {
		// to calculate memory usage, marks used memory before program starts
		long beforeUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

		System.out.println("Welcome from my program!" + "\r\n");
		
		// call getUserInput
		UserInput user = new UserInput();
		user.getUserInput(sc);
		
		// call outputFile
		// if true, continue to write file.
		// else, ends programs.
		OutputFile out = new OutputFile();
		if (out.output(sc)) {
			// call accessFile
			AccessFile access = new AccessFile();
			access.writeFile();
			access.readFile();
			
			// used memory after program ends
			long afterUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

			// actual memory usage
			long actualMemUsed = afterUsedMem - beforeUsedMem;

			System.out.println("Program Used memory in megabytes : " + bytesToMegabytes(actualMemUsed) + "MB");

		}

		// scanner closed
		sc.close();
	}

	public File getFilePath() {
		// output file path
		String home = System.getProperty("user.home");
		File newFile = new File(home + File.separator + OUTPUT_FILE_NAME);
		return newFile;
	}

	public int getNumLines() {
		return numLines;
	}

	public void setNumLines(int numLines) {
		this.numLines = numLines;
	}

}
