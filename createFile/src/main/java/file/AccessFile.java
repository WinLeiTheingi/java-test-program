package file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import appli.Main;

public class AccessFile {
	File newFile;
	
	public AccessFile() {
		newFile = Main.getFilePath();
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
			Main mainJava = new Main();

			// loop for number of lines
			for (int i = 1; i <= mainJava.getNumLines(); i++) {
				strFront = "";
				strEnd = "";

				// prepare unique string to write in file
				if (i < 10) {
					strFront = "00";
				} else if (i < 100) {
					strFront = "0";
				}
				strFront = "Line No." + strFront + i + ",";

				// fill up to 100 characters
				strEnd = "1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678";

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
	 * Read text file and print number of lines and number of characters in one line.
	 *  (Read only first line for character count)
	 * 
	 */
	public void readFile() {
		try {
			// Read line count of file
			Path file = Paths.get(newFile.toString());
			long count = Files.lines(file).count();
			System.out.println("Number of lines in file : " + count);

			// Read character count in one line
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
		} catch (IOException e) {
			System.out.println("An error occurred in read file.");
			e.printStackTrace();
		}
	}

}
