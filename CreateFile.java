package forJavaTest;

import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;

public class CreateFile {
    
    int x = 0;
    File newFile;
    Scanner sc;
    
    // Constructor
    public CreateFile() {
        System.out.println("Welcome from my program!" + "\r\n");
        
        // Create a Scanner object to read user input
        sc = new Scanner(System.in);
    }
    
    // user input 
    public void getUserInput() {
        // Create a Scanner object to read user input
        //Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter Number of lines from 1 to 230 to write in file : ");
        
        try {
            // Read user input
            x = sc.nextInt();
        } catch(InputMismatchException e) {
            System.out.println("Please enter only number.");
        }
        
        //if catch exception, prompt again user input
        // todo
        
        // Check Number Range
        // todo, checkNumber();
        
        if (x > 1) {
            System.out.println(x + " lines will write in file." + "\r\n");
        } else if (x == 1) {
            System.out.println(x + " line will write in file." + "\r\n");
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
        		System.out.println("File already exists.");
        		
        		// ask to override or not
        		// if yes, deleteFile, createNewFile and return true.
        		// if no, do nothing and return false.
//        		System.out.println("File already exists. Do you want to override file?(y/n)");
//        		String strReply = sc.nextLine();
//        		if (strReply == "y") {
//        			newFile.delete();
//        			
//        			// call itself to create new file
//        			outputFile();
//        		} else if (strReply == "n") {
//        			return false;
//        		} else {
//        			System.out.println("Please enter only y or n.");
//        			
//        			// request user input again. todo
//        		}
        		return false;
        	}
		} catch (IOException e) {
			System.out.println("An error occurred in output file.");
			e.printStackTrace();
			return false;
		} catch (InputMismatchException e) {
			System.out.println("Please enter only y or n.");
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
            
            System.out.println("Successfully wrote to the file.");
            
            // print number of characters in one line. ToDo
            //int noChars = Integer.parseInt(strFront) + Integer.parseInt(strEnd);
            //System.out.println("Number of characters in one line : " + Integer.toString(noChars));
            
          } catch (IOException e) {
            System.out.println("An error occurred in write file.");
            e.printStackTrace();
          }
    }
    
    private static final long MEGABYTE = 1024L * 1024L;

    public static long bytesToMegabytes(long bytes) {
        return bytes / MEGABYTE;
    }
    
    // main function
    public static void main(String []args) {
    	long beforeUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    	
        // call constructor
        CreateFile crtfile = new CreateFile();
        
        // call getUserInput
        crtfile.getUserInput();
        
        // call outputFile
        if (crtfile.outputFile()) {
        	// call writeFile
            crtfile.writeFile();
        };
        
        long afterUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        long actualMemUsed = afterUsedMem - beforeUsedMem;
        
        //System.out.println("beforeUsedMem in megabytes : " + bytesToMegabytes(beforeUsedMem) + ", afterUsedMem in megabytes : " + bytesToMegabytes(afterUsedMem));
        System.out.println("Used memory in megabytes : " + bytesToMegabytes(actualMemUsed) + "MB");
    }
}