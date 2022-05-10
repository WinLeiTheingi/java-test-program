package appli;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Create text file with x number of lines.
 * Each line consists of a string that is unique with 100 characters long.
 * 
 * x will be prompt from user.
 * x must be any number from 1 to 299.
 * 
 * If file exists in target path, ask user to override or not.
 * 
 * @author winleitheingi
 * created in 2022/05/09
 * 
 */
public class CreateFile {
	// user input for number of lines
	private int x = 0;

	// output file path
	private File newFile;

	// output file name
	private static final String OUTPUT_FILE_NAME = "outputData.txt";

	// megabyte
	private static final long MEGABYTE = 1024L * 1024L;
	
	// Scanner object
	private static Scanner sc;

	/**
	 * Constructor
	 * 
	 */
	public CreateFile() {
		System.out.println("Welcome from my program!" + "\r\n");
	}

	/**
	 * getUserInput method
	 * Ask user "number of lines" to write in file.
	 * 
	 * If input data is valid, continue to write file.
	 * If input data is invalid, prompt user input again until valid.
	 * 
	 */
	public void getUserInput() {
		// Create a Scanner object to read user input(number of lines)
		sc = new Scanner(System.in);

		System.out.println("Enter Number of lines from 1 to 299 to write in file : ");
		
		try {
			// Read user input as integer
			x = sc.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("Please enter only number." + "\r\n");
			getUserInput();
			return;
		}

		// Check Number Range
		if (!checkNumber(x)) {
			getUserInput();
			return;
		}

		// Write comment number of lines to write in file
		if (x > 1) {
			System.out.println(x + " lines will write in file." + "\r\n");
		} else if (x == 1) {
			System.out.println(x + " line will write in file." + "\r\n");
		}
	}

	/**
	 * checkNumber Method
	 * Check number range, whether input number is between 1 to 299 or not.
	 * 
	 * If valid range, return true.
	 * If out of range, return false.
	 * 
	 */
	public boolean checkNumber(int num) {

		// if number is between 1 to 299, return true
		if (num >= 1 && num <= 299) {
			return true;
		} else {
			System.out.println("Invalid. Number of lines must be from 1 to 299!" + "\r\n");
			return false;
		}
	}

	/**
	 * outputFile Method
	 * Create new text file.
	 * 
	 * If same file exists in target path, ask user to override or not.
	 * If yes, delete file, create new file and return true.
	 * If no, do nothing and return false.
	 * 
	 */
	public boolean outputFile() {
		String home = System.getProperty("user.home");

		try {
			newFile = new File(home + File.separator + OUTPUT_FILE_NAME);

			// create new file
			if (newFile.createNewFile()) {
				System.out.println("File created in \"" + newFile + "\"" + "\r\n");
				return true;
			} else {
				// same file exist in target path
				System.out.println(
						"File already exists in \"" + newFile + "\"." + "\r\n" + "Do you want to override file?(y/n)");

				// Create a Scanner object to read user input(y/n)
				sc = new Scanner(System.in);
				
				while(true) {
					String strReply = sc.nextLine();
					
					if (strReply.equals("y")) {
						newFile.delete();
						break;
					} else if (strReply.equals("n")) {
						System.out.println("Program ends. Please change your file name and start again!" + "\r\n");
						return false;
					} else {
						System.out.println("Please enter only y or n.");
					}
					
				}
				// call itself to create new file
				outputFile();
				return true;
			}
		} catch (IOException e) {
			System.out.println("An error occurred in output file.");
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * writeFile method
	 * Write text file with x number of lines, 100 characters string in one line.
	 * Prepare unique string with line number.
	 * 
	 */
	public void writeFile() {
		try {
			FileWriter myWriter = new FileWriter(newFile);
			String strFront = ""; // line no.
			String strEnd = ""; // left string to fill up to 100 characters

			// loop for number of lines
			for (int i = 1; i <= x; i++) {
				strFront = "";
				strEnd = "";

				// prepare unique string to write in file
				if (i < 10) {
					strFront = "00";
				} else if (i < 100) {
					strFront = "0";
				}
				strFront = "Line No." + strFront + i + ",";

				// loop for 100 characters string in one line
				for (int j = 1; j <= 100 - strFront.length(); j++) {
					strEnd += "A";
				}
				
				// write for one line
				myWriter.write(strFront + strEnd + "\r\n");
			}
			myWriter.close();

			System.out.println("Successfully wrote to the file." + "\r\n");

		} catch (IOException e) {
			System.out.println("An error occurred in write file.");
			e.printStackTrace();
		}
	}

	/**
	 * readFile method
	 * Read text file and print number of characters in one line. (Read only first line)
	 * 
	 */
	public void readFile() {
		try {
			Scanner myReader = new Scanner(newFile);

			// if data exists in text file, read first line and print length.
			if (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				System.out.println("Number of characters in one line : " + data.length() + "\r\n");
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred in read file.");
			e.printStackTrace();
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
	 * main method
	 * 
	 */
	public static void main(String[] args) {
		// to calculate memory usage, marks used memory before program starts
		long beforeUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

		// call constructor
		CreateFile crtfile = new CreateFile();

		// call getUserInput
		crtfile.getUserInput();

		// call outputFile
		// if true, continue.
		// else, ends programs. 
		if (crtfile.outputFile()) {
			// call writeFile
			crtfile.writeFile();

			// call readFile
			crtfile.readFile();
		}
		// scanner closed
		sc.close();

		// used memory after program ends
		long afterUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		
		// actual memory usage
		long actualMemUsed = afterUsedMem - beforeUsedMem;

		System.out.println("Program Used memory in megabytes : " + bytesToMegabytes(actualMemUsed) + "MB");
	}
}