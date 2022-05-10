package forJavaTest;

import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.util.InputMismatchException;
import java.io.IOException;
import java.io.FileNotFoundException;

public class CreateFile {
    
    int x = 0;
    File newFile;
    private static final long MEGABYTE = 1024L * 1024L;
    
    // Constructor
    public CreateFile() {
        System.out.println("Welcome from my program!" + "\r\n");
    }
    
	/*
	 * user input
	 * if valid number, return true.
	 * if invalid number, return false.
	 * return "false" will prompt again user input. 
	 */
    public boolean getUserInput() {
        // Create a Scanner object to read user input
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter Number of lines from 1 to 299 to write in file : ");
        
		try {
			// Read user input
			x = sc.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("Please enter only number." + "\r\n");
			return false;
		}
        
        // Check Number Range
		if (!checkNumber(x)) {;
			return false;
		}
        
        if (x > 1) {
            System.out.println(x + " lines will write in file." + "\r\n");
        } else if (x == 1) {
            System.out.println(x + " line will write in file." + "\r\n");
        }
        return true;
    }
    
    // Check Number Range
    public boolean checkNumber(int num) {
    	
    	//check whether number is between 1 to 299 or not
    	if (num >= 1 && num <= 299) {
    		return true;
    	} else {
    		System.out.println("Invalid. Number of lines must be from 1 to 299!" + "\r\n");
        	return false;
    	}
    }
    
    // output File
    public boolean outputFile() {
    	String home = System.getProperty("user.home");
    	
        try {
        	newFile = new File(home + File.separator + "outputData.txt");
        	
        	if (newFile.createNewFile()) {
        		System.out.println("File created in \"" + newFile + "\"" + "\r\n");
        		return true;
        	} else {
        		// if file exists, ask to override or not
        		// if yes, deleteFile, createNewFile and return true.
        		// if no, do nothing and return false.
				System.out.println(
						"File already exists in \"" + newFile + "\"" + "\r\n" + "Do you want to override file?(y/n)");
  		
                // Create a Scanner object to read user input
                Scanner sc = new Scanner(System.in);

                String strReply = sc.nextLine();
        		if (strReply.equals("y")) {
        			newFile.delete();
        		} else if (strReply.equals("n")) {
        			System.out.println("Program ends. Please change your file name and start again!" + "\r\n");
        			return false;
        		}
        		else {
        			System.out.println("Please enter only y or n." + "\r\n");
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
    
    // write file
    public void writeFile() {
        try {
            FileWriter myWriter = new FileWriter(newFile);
            String strFront = "";
            String strEnd = "";
            
            // loop for number of lines
            for (int i = 1; i <= x; i++) {
            	strFront = "";
            	strEnd = "";
            	
            	if (i < 10) {
            		strFront = "00";
            	} else if (i < 100) {
            		strFront = "0";
            	}
            	strFront = "Line No." + strFront + i + ",";
            	
            	// loop for 100 character string in one line
            	for (int j = 1; j <= 100 - strFront.length(); j++) {
            		strEnd += "A";
            	}
            	myWriter.write(strFront + strEnd + "\r\n");
            }
            myWriter.close();
            
            System.out.println("Successfully wrote to the file." + "\r\n");
            
          } catch (IOException e) {
            System.out.println("An error occurred in write file.");
            e.printStackTrace();
          }
    }
    
    // read file
    public void readFile() {
        // read file and print number of characters in one line. (Read only first line)
    	try {
    	      Scanner myReader = new Scanner(newFile);
    	      
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

    public static long bytesToMegabytes(long bytes) {
        return bytes / MEGABYTE;
    }
    
    // main function
    public static void main(String []args) {
    	long beforeUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    	
        // call constructor
        CreateFile crtfile = new CreateFile();
    	
        // call getUserInput. loop until right input
        while (!crtfile.getUserInput()) {
        	crtfile.getUserInput();
        }
        
        // call outputFile
        if (crtfile.outputFile()) {
        	// call writeFile
            crtfile.writeFile();
            
            // call readFile
            crtfile.readFile();
        };
        
        long afterUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        long actualMemUsed = afterUsedMem - beforeUsedMem;
        
        System.out.println("Program Used memory in megabytes : " + bytesToMegabytes(actualMemUsed) + "MB");
    }
}