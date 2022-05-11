import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

/**
 * Test CreateFie.java
 * 
 * @author winleitheingi
 * created in 2022/05/10
 *
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CreateFileTest {
	// output file path
	private static File newFile;
	
	// output file name
	private static final String OUTPUT_FILE_NAME = "outputData.txt";
	
	// read output
	private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		String home = System.getProperty("user.home");
		newFile = new File(home + File.separator + OUTPUT_FILE_NAME);
	}

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
	@Order(1)
	void testUserInput_Min() {
		ByteArrayInputStream in = new ByteArrayInputStream("1".getBytes());
		System.setIn(in);
		
		CreateFile creFile = new CreateFile();
		creFile.getUserInput();
		
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
	@Order(2)
	void testUserInput_Max() {
		ByteArrayInputStream in = new ByteArrayInputStream("229".getBytes());
		System.setIn(in);
		
		CreateFile creFile = new CreateFile();
		creFile.getUserInput();
		
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
	@Order(3)
	void testUserInput_Zero() throws Exception {
		ByteArrayInputStream in = new ByteArrayInputStream("0".getBytes());
		System.setIn(in);
		
		try {
			CreateFile creFile = new CreateFile();
			creFile.getUserInput();
		} catch (Exception e) {
			// will show NoSuchElementException, because of next input data doesn't exists
			e.printStackTrace();
		}
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
	@Order(4)
	void testUserInput_230() {
		ByteArrayInputStream in = new ByteArrayInputStream("230".getBytes());
		System.setIn(in);
		
		try {
			CreateFile creFile = new CreateFile();
			creFile.getUserInput();
		} catch (Exception e) {
			// will show NoSuchElementException, because of next input data doesn't exists
			e.printStackTrace();
		}
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
	@Order(5)
	void testUserInput_Invalid() {
		ByteArrayInputStream in = new ByteArrayInputStream("ABCD".getBytes());
		System.setIn(in);
		
		try {
			CreateFile creFile = new CreateFile();
			creFile.getUserInput();
		} catch (Exception e) {
			// will show NoSuchElementException, because of next input data doesn't exists
			e.printStackTrace();
		}

		// actual message include full string with user prompt message
		String actualMsg = out.toString();

		// expected message
		String expectedMsg = "Please enter only number.";

	    assertTrue(actualMsg.contains(expectedMsg));
	}
	
	/**
	 * test outputFile method
	 * normal case    : test create new file
	 * test condition : test when file doesn't exists in target path
	 * expected       : show success message and check file exists or not
	 */
	@Test
	@Order(6)
	void testOutputFile_Normal() {
		CreateFile creFile = new CreateFile();
		creFile.outputFile();
		
		// actual message include full string with user prompt message
		String actualMsg = out.toString();
		
		// expected message
		String expectedMsg = "File created";
		
		assertTrue(actualMsg.contains(expectedMsg));
		
		// check file exists or not
		// if file exists do nothing
		// if file does not exists, will show exception
		creFile.readFile();
	}
	
	/**
	 * test outputFile method
	 * normal case    : test create new file
	 * test condition : test when file exists in target path, override yes
	 * expected       : if file exists, show confirm message
	 * other comment  : new file will be created
	 */
	@Test
	@Order(7)
	void testOutputFile_OverrideYes() {
		ByteArrayInputStream in = new ByteArrayInputStream("y".getBytes());
		System.setIn(in);
		
		CreateFile creFile = new CreateFile();
		creFile.outputFile();
		
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
	@Order(8)
	void testOutputFile_OverrideNo() {
		ByteArrayInputStream in = new ByteArrayInputStream("n".getBytes());
		System.setIn(in);
		
		CreateFile creFile = new CreateFile();
		creFile.outputFile();
		
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
	@Order(9)
	void testOutputFile_InvalidInput() {
		ByteArrayInputStream in = new ByteArrayInputStream("123".getBytes());
		System.setIn(in);
		
		try {
			CreateFile creFile = new CreateFile();
			creFile.outputFile();
		} catch (Exception e) {
			// will show NoSuchElementException, because of next input data doesn't exists
			e.printStackTrace();
		}
		
		// actual message include full string with user prompt message
		String actualMsg = out.toString();
		
		// expected message
		String expectedMsg = "Please enter only y or n.";
		
		assertTrue(actualMsg.contains(expectedMsg));
	}
	
	/**
	 * test writeFile method
	 * normal case    : test write file
	 * test condition : test when file exists in target path
	 * expected       : show success message
	 */
	@Test
	@Order(10)
	void testWriteFile() {
		ByteArrayInputStream in = new ByteArrayInputStream("220".getBytes());
		System.setIn(in);
		
		CreateFile creFile = new CreateFile();
		creFile.getUserInput();
		creFile.writeFile();
		
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
	@Order(11)
	void testReadFile() {
		CreateFile creFile = new CreateFile();
		creFile.readFile();
		
		// actual message include full string with user prompt message
		String actualMsg = out.toString();
		
		// expected message
		String expectedMsg1 = "Number of lines in file : 220";
		String expectedMsg2 = "Number of characters in one line : 100";
		
		assertTrue(actualMsg.contains(expectedMsg1));
		assertTrue(actualMsg.contains(expectedMsg2));
	}
}
