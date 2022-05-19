package file;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import input.UserInput;

/**
 * @author winlaetheingi
 *
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AccessFileTest {
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
	 * test writeFile method
	 * normal case    : test write file
	 * test condition : test when file exists in target path
	 * expected       : show success message
	 */
	@Test
	@Order(1)
	void testWriteFile() {
		ByteArrayInputStream in = new ByteArrayInputStream("220".getBytes());
		System.setIn(in);
		
		UserInput input = new UserInput();
		input.getUserInput(sc);

		AccessFile access = new AccessFile();
		access.writeFile();

		// actual message include full string with user prompt message
		String actualMsg = out.toString();

		// expected message
		String expectedMsg = "Successfully wrote to the file.";

		assertTrue(actualMsg.contains(expectedMsg));
	}

	/**
	 * test readFile method
	 * normal case    : test read file
	 * test condition : test after testWriteFile method is called
	 * expected       : show success message
	 */
	@Test
	@Order(2)
	void testReadFile() {
		AccessFile access = new AccessFile();
		access.readFile();

		// actual message include full string with user prompt message
		String actualMsg = out.toString();

		// expected message
		String expectedMsg1 = "Number of lines in file : 220";
		String expectedMsg2 = "Number of characters in one line : 100";

		assertTrue(actualMsg.contains(expectedMsg1));
		assertTrue(actualMsg.contains(expectedMsg2));
	}
}
