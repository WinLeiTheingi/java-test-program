package input;

import java.util.InputMismatchException;
import java.util.Scanner;

import appli.Main;

/**
 * UserInput Class
 * 
 * Ask user input and check data is valid or not
 * 
 * @author winleitheingi
 * created in 2022/05/18
 * 
 */
public class UserInput {
	// user input for number of lines
	private int x;

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
			// Read user input as integer
			x = sc.nextInt();

			// Check Number Range
			if (!checkNumber()) {
				getUserInput(sc);
			} else {
				// set Number of lines
				Main mainJava = new Main();
				mainJava.setNumLines(x);
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
	public boolean checkNumber() {
		// if number is between 1 to 229, show comment and return true
		if (x == 1) {
			System.out.println(x + " line will write in file." + "\r\n");
			return true;
		} else if (x > 1 && x <= 229) {
			System.out.println(x + " lines will write in file." + "\r\n");
			return true;
		} else {
			System.out.println("Invalid. Number of lines must be from 1 to 229!" + "\r\n");
			return false;
		}
	}
}
