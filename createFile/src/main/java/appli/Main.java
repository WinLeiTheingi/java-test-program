package appli;

/**
 * Main Class(Start to run program)
 * 
 * Create text file with x number of lines.
 * Each line consists of a string that is unique with 100 characters long.
 * 
 * x will be prompt from user.
 * x must be any number from 1 to 229.
 * 
 * If file exists in target path, ask user to override or not.
 * 
 * @author winleitheingi
 * created in 2022/05/18
 * 
 */
import java.io.File;
import java.util.Scanner;

import file.AccessFile;
import file.OutputFile;
import input.UserInput;

public class Main {
	// user input for number of lines
	private static int numLines;

	// output file name
	private static final String OUTPUT_FILE_NAME = "outputData.txt";
	
	// megabyte
	private static final long MEGABYTE = 1024L * 1024L;

	// Scanner object
	private static Scanner sc;

	/**
	 * main method
	 * 
	 */
	public static void main(String[] args) {
		System.out.println("Welcome from my program!" + "\r\n");

		// to calculate memory usage, marks used memory before program starts
		long beforeUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

		// call UserInput
		UserInput user = new UserInput();
		user.getUserInput(sc);

		// call OutputFile
		// if true, continue to write file and read data.
		// else, ends program.
		OutputFile out = new OutputFile();
		if (out.output(sc)) {
			// call AccessFile
			AccessFile access = new AccessFile();
			access.writeFile();
			access.readFile();

			// used memory after program ends
			long afterUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

			// actual memory usage
			long actualMemUsed = afterUsedMem - beforeUsedMem;

			System.out.println("Program Used memory in megabytes : " + bytesToMegabytes(actualMemUsed) + "MB");
		}
	}

	/**
	 * bytesToMegabytes method
	 * Change bytes to megabytes
	 * 
	 */
	public static long bytesToMegabytes(long bytes) {
		return bytes / MEGABYTE;
	}

	/**
	 * getFilePath method
	 * return full path including file name
	 * 
	 */
	public static File getFilePath() {
		// output file path
		String home = System.getProperty("user.home");
		File newFile = new File(home + File.separator + OUTPUT_FILE_NAME);
		return newFile;
	}

	/**
	 * getNumLines method
	 * return number of lines
	 * 
	 */
	public int getNumLines() {
		return numLines;
	}

	/**
	 * setNumLines method
	 * set number of lines
	 * 
	 */
	public void setNumLines(int numLines) {
		Main.numLines = numLines;
	}
}
