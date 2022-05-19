package file;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

/**
 * @author winlaetheingi
 *
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OutputFileTest {
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
	 * test outputFile method
	 * normal case    : test create new file
	 * test condition : test when file doesn't exists in target path
	 * expected       : show success message and check file exists or not
	 */
	@Test
	@Order(1)
	void testOutputFile_Normal() {
		OutputFile outFile = new OutputFile();
		outFile.output(sc);

		// actual message include full string with user prompt message
		String actualMsg = out.toString();

		// expected message
		String expectedMsg = "File created";

		assertTrue(actualMsg.contains(expectedMsg));

		// check file exists or not
		// if file exists do nothing
		// if file does not exists, will show exception
		AccessFile access = new AccessFile();
		access.readFile();
	}

	/**
	 * test outputFile method
	 * normal case    : test create new file
	 * test condition : test when file exists in target path, override yes
	 * expected       : if file exists, show confirm message
	 * other comment  : new file will be created
	 */
	@Test
	@Order(2)
	void testOutputFile_OverrideYes() {
		ByteArrayInputStream in = new ByteArrayInputStream("y".getBytes());
		System.setIn(in);

		OutputFile outFile = new OutputFile();
		outFile.output(sc);

		// actual message include full string with user prompt message
		String actualMsg = out.toString();

		// expected message
		String expectedMsg1 = "File already exists";
		String expectedMsg2 = "Do you want to override file?(y/n)";

		assertTrue(actualMsg.contains(expectedMsg1));
		assertTrue(actualMsg.contains(expectedMsg2));
	}

	/**
	 * test outputFile method
	 * normal case    : test create new file
	 * test condition : test when file exists in target path, override no
	 * expected       : if override no, show alert message
	 * other comment  : new file will not be created
	 */
	@Test
	@Order(3)
	void testOutputFile_OverrideNo() {
		ByteArrayInputStream in = new ByteArrayInputStream("n".getBytes());
		System.setIn(in);

		OutputFile outFile = new OutputFile();
		outFile.output(sc);

		// actual message include full string with user prompt message
		String actualMsg = out.toString();

		// expected message
		String expectedMsg = "Program ends. Please change your file name and start again!";

		assertTrue(actualMsg.contains(expectedMsg));
	}

	/**
	 * test outputFile method
	 * abnormal case    : test create new file
	 * test condition : test when file exists in target path, type invalid input
	 * expected       : if invalid input, show error message
	 * other comment  : new file will not be created, will prompt user input again in actual program
	 */
	@Test
	@Order(4)
	void testOutputFile_InvalidInput() {
		ByteArrayInputStream in = new ByteArrayInputStream("123".getBytes());
		System.setIn(in);

		OutputFile outFile = new OutputFile();

		// throws NoSuchElementException, because of next input data doesn't exists
		assertThrows(NoSuchElementException.class, () -> outFile.output(sc));

		// actual message include full string with user prompt message
		String actualMsg = out.toString();

		// expected message
		String expectedMsg = "Please enter only y or n.";

		assertTrue(actualMsg.contains(expectedMsg));
	}
}
