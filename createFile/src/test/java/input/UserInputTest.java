package input;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author winlaetheingi
 *
 */
class UserInputTest {
	// read output
	private final ByteArrayOutputStream out = new ByteArrayOutputStream();

	// Scanner object
	private Scanner sc;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		System.setOut(new PrintStream(out));
	}
	
	/**
	 * test getUserInput method
	 * normal case : test minimum value => when number of lines is "1"
	 * expected    : show success message
	 */
	@Test
	void testUserInput_Min() {
		ByteArrayInputStream in = new ByteArrayInputStream("1".getBytes());
		System.setIn(in);

		UserInput input = new UserInput();
		input.getUserInput(sc);

		// actual message include full string with user prompt message
		String actualMsg = out.toString();

		// expected message
		String expectedMsg = "1 line will write in file.";

		assertTrue(actualMsg.contains(expectedMsg));
	}

	/**
	 * test getUserInput method
	 * normal case : test maximum value => when number of lines is "229"
	 * expected    : show success message
	 */
	@Test
	void testUserInput_Max() {
		ByteArrayInputStream in = new ByteArrayInputStream("229".getBytes());
		System.setIn(in);

		UserInput input = new UserInput();
		input.getUserInput(sc);

		// actual message include full string with user prompt message
		String actualMsg = out.toString();

		// expected message
		String expectedMsg = "229 lines will write in file.";

		assertTrue(actualMsg.contains(expectedMsg));
	}

	/**
	 * test getUserInput method
	 * abnormal case : test number range => when number of lines is "<1"
	 * expected      : show error message
	 * other comment : if invalid data, will prompt user input again in actual program
	 */
	@Test
	void testUserInput_Zero() {
		ByteArrayInputStream in = new ByteArrayInputStream("0".getBytes());
		System.setIn(in);

		UserInput input = new UserInput();

		// throws NoSuchElementException, because of next input data doesn't exists
		assertThrows(NoSuchElementException.class, () -> input.getUserInput(sc));

		// actual message include full string with user prompt message
		String actualMsg = out.toString();

		// expected message
		String expectedMsg = "Invalid. Number of lines must be from 1 to 229!";

		assertTrue(actualMsg.contains(expectedMsg));
	}

	/**
	 * test getUserInput method
	 * abnormal case : test number range => when number of lines is ">229"
	 * expected      : show error message
	 * other comment : if invalid data, will prompt user input again in actual program
	 */
	@Test
	void testUserInput_230() {
		ByteArrayInputStream in = new ByteArrayInputStream("230".getBytes());
		System.setIn(in);

		UserInput input = new UserInput();

		// throws NoSuchElementException, because of next input data doesn't exists
		assertThrows(NoSuchElementException.class, () -> input.getUserInput(sc));

		// actual message include full string with user prompt message
		String actualMsg = out.toString();

		// expected message
		String expectedMsg = "Invalid. Number of lines must be from 1 to 229!";

		assertTrue(actualMsg.contains(expectedMsg));
	}

	/**
	 * test getUserInput method
	 * abnormal case : test wrong input data => when number of lines is not number
	 * expected      : show error message
	 * other comment : if invalid data, will prompt user input again in actual program
	 */
	@Test
	void testUserInput_Invalid() {
		ByteArrayInputStream in = new ByteArrayInputStream("ABCD".getBytes());
		System.setIn(in);

		UserInput input = new UserInput();

		// throws NoSuchElementException, because of next input data doesn't exists
		assertThrows(NoSuchElementException.class, () -> input.getUserInput(sc));

		// actual message include full string with user prompt message
		String actualMsg = out.toString();

		// expected message
		String expectedMsg = "Please enter only number.";

		assertTrue(actualMsg.contains(expectedMsg));
	}
}
