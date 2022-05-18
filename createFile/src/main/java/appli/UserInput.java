package appli;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInput {

	/**
	 * getUserInput method
	 * Ask user "number of lines" to write in file.
	 * 
	 * If input data is valid, continue to next process.
	 * If input data is invalid, prompt user input again until valid.
	 * 
	 */
	public void getUserInput(Scanner sc) {
		// Create a Scanner object to read user input(number of lines)
		sc = new Scanner(System.in);

		// prompt user input
		System.out.print("Enter Number of lines from 1 to 229 to write in file : ");

		try {
			Main mainJava = new Main();
			// Read user input as integer
			mainJava.setNumLines(sc.nextInt());

			// Check Number Range
			if (!checkNumber(mainJava.getNumLines())) {
				getUserInput(sc);
			}
		} catch (InputMismatchException e) {
			System.out.println("Please enter only number." + "\r\n");
			getUserInput(sc);
		}
	}

	/**
	 * checkNumber Method
	 * Check number range, whether input number is between 1 to 229 or not.
	 * 
	 * If valid range, return true.
	 * If out of range, return false.
	 * 
	 */
	public boolean checkNumber(int num) {
		// if number is between 1 to 229, show comment and return true
		if (num == 1) {
			System.out.println(num + " line will write in file." + "\r\n");
			return true;
		} else if (num > 1 && num <= 229) {
			System.out.println(num + " lines will write in file." + "\r\n");
			return true;
		} else {
			System.out.println("Invalid. Number of lines must be from 1 to 229!" + "\r\n");
			return false;
		}
	}
}
