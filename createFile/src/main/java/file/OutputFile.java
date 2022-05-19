package file;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import appli.Main;

public class OutputFile {

	/**
	 * output Method
	 * Create new text file.
	 * 
	 * If same file exists in target path, ask user to override or not.
	 * If yes, delete file, create new file and return true.
	 * If no, do nothing and return false.
	 * 
	 */
	public boolean output(Scanner sc) {
		// Create a Scanner object to read user input(y/n)
		sc = new Scanner(System.in);
		
		try {
			File newFile = Main.getFilePath();
			// create new file
			if (newFile.createNewFile()) {
				System.out.println("File created in \"" + newFile + "\"" + ".\r\n");
				return true;
			} else {
				// same file exists in target path
				System.out.println(
						"File already exists in \"" + newFile + "\"." + "\r\n" + "Do you want to override file?(y/n)");

				while (true) {
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
				output(sc);
				return true;
			}
		} catch (IOException e) {
			System.out.println("An error occurred in output file.");
			e.printStackTrace();
			return false;
		} finally {
			// scanner closed
			sc.close();
		}
	}
}
