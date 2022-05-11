/**
 * CreateFileTest.java
 */
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author winleitheingi
 *
 */
class CreateFileTest {

	private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    private static final InputStream stdin = System.in;
    private static final PrintStream stdout = System.out;
    
	// output file path
	private static File newFile;
	
	// output file name
	private static final String OUTPUT_FILE_NAME = "outputData.txt";
    
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
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		System.setIn(stdin);
		System.setOut(stdout);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		System.setOut(new PrintStream(out));
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * test main method
	 * normal case
	 */
	@Test
	void testMain() {
		//String[] args = null;
		//CreateFile.main(args);
		
		// check success. todo
	}
	
	/**
	 * test getUserInput method
	 * normal case : test minimum value, when number of lines is "1"
	 * expected    : show success message
	 */
	@Test
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
	 * normal case : test maximum value, when number of lines is "229"
	 * expected    : show success message
	 */
	@Test
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
	 * abnormal case : test number range, when number of lines is "<1"
	 * expected      : show error message
	 */
	@Test
	void testUserInput_Zero() {
		String userInput = "0" + System.getProperty("line.separator") + "1" + System.getProperty("line.separator");
				
		ByteArrayInputStream in = new ByteArrayInputStream(userInput.getBytes());
		System.setIn(in);
		
		try {
			CreateFile creFile = new CreateFile();
			creFile.getUserInput();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// actual message include full string with user prompt message
			String actualMsg = out.toString();
			
			// expected message
			String expectedMsg = "Invalid. Number of lines must be from 1 to 229!";
			
			assertTrue(actualMsg.contains(expectedMsg));
		}
		
		//todo. accept multiple user input or stop next user input
		//also need to add >229 case
	}
	
	/**
	 * test getUserInput method
	 * abnormal case : test "input mismatch exception", when number of lines is not number
	 * expected      : show error message
	 */
	@Test
	void testUserInput_Invalid() {
		ByteArrayInputStream in = new ByteArrayInputStream("ABCD".getBytes());
		System.setIn(in);
		
		CreateFile creFile = new CreateFile();
		creFile.getUserInput();

		// actual message include full string with user prompt message
		String actualMsg = out.toString();

		// expected message
		String expectedMsg = "Please enter only number.";

	    assertTrue(actualMsg.contains(expectedMsg));
	    
		//todo. accept multiple user input or stop next user input
	    //think to test exception or not
	}
	
	/**
	 * test outputFile method
	 * normal case    : test create new file
	 * test condition : test when file doesn't exists in target path
	 * expected       : show success message and check file exists or not
	 */
	@Test
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
		// if file does not exists, will throw exception
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
	void testOutputFile_OverrideNo() {
		ByteArrayInputStream in = new ByteArrayInputStream("n".getBytes());
		System.setIn(in);
		
		CreateFile creFile = new CreateFile();
		creFile.outputFile();
		
		// actual message include full string with user prompt message
		String actualMsg = out.toString();
		
		// expected message
		String expectedMsg = "Program ends. Please change your file name and start again";
		
		assertTrue(actualMsg.contains(expectedMsg));
	}

	/**
	 * test outputFile method
	 * abnormal case    : test create new file
	 * test condition : test when file exists in target path, type invalid input
	 * expected       : if invalid input, show alert message
	 * other comment  : new file will not be created
	 */
//	@Test
//	void testOutputFile_InvalidInput() {
//		ByteArrayInputStream in = new ByteArrayInputStream("AB".getBytes());
//		System.setIn(in);
//		
//		CreateFile creFile = new CreateFile();
//		creFile.outputFile();
//		
//		// actual message include full string with user prompt message
//		String actualMsg = out.toString();
//		
//		// expected message
//		String expectedMsg = "Please enter only y or n.";
//		
//		assertTrue(actualMsg.contains(expectedMsg));
//	}
	
	
	/**
	 * test writeFile method
	 * normal case    : test write file
	 * test condition : test when file exists in target path
	 * expected       : show success message
	 */
	@Test
	void testWriteFile() {
		CreateFile creFile = new CreateFile();
		creFile.writeFile();
		
		// actual message include full string with user prompt message
		String actualMsg = out.toString();
		
		// expected message
		String expectedMsg = "Successfully wrote to the file.";
		
		assertTrue(actualMsg.contains(expectedMsg));
		
		// to do. x need to pass
	}
}
