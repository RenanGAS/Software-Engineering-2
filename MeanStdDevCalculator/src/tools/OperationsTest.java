package src.tools;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

class OperationsTest {
	
	double cliCommaCase_Average() {
		Operations operations = new Operations();
		LinkedList list = new LinkedList();
		int count = 0;
		
		while (count < 6){
            String input = "13,9";

            input = input.replace(",", ".");

            double element = Double.parseDouble(input);
            list = list.insert(list, element);
            count += 1;
        }
		
		return operations.average(list);
	}
	
	double cliDotCase_Average() {
		Operations operations = new Operations();
		LinkedList list = new LinkedList();
		int count = 0;
		
		while (count < 6){
            String input = "13.9";

            input = input.replace(",", ".");

            double element = Double.parseDouble(input);
            list = list.insert(list, element);
            count += 1;
        }
		
		return operations.average(list);
	}
	
	double cliEmptyCase_Average() {
		Operations operations = new Operations();
		LinkedList list = new LinkedList();
		
		return operations.average(list);
	}
	
	@SuppressWarnings("resource")
	double fileCommaCase_Average() throws FileNotFoundException {
		Operations operations = new Operations();
		LinkedList list = new LinkedList();
        Scanner scanner = null;
       
        File file = new File("./src/tests/test2.txt");

        scanner = new Scanner(file);
        
        while (scanner.hasNextLine()){
            String input = scanner.nextLine();

            if (input.isEmpty()){
                break;
            }

            input = input.replace(",", ".");

            double element = Double.parseDouble(input);
            list = list.insert(list, element);
        }
		
		return operations.average(list);
	}
	
	@SuppressWarnings("resource")
	double fileDotCase_Average() throws FileNotFoundException {
		Operations operations = new Operations();
		LinkedList list = new LinkedList();
        Scanner scanner = null;
       
        File file = new File("./src/tests/test3.txt");

        scanner = new Scanner(file);
        
        while (scanner.hasNextLine()){
            String input = scanner.nextLine();

            if (input.isEmpty()){
                break;
            }

            input = input.replace(",", ".");

            double element = Double.parseDouble(input);
            list = list.insert(list, element);
        }
		
		return operations.average(list);
	}
	
	@SuppressWarnings("resource")
	double fileEmptyCase_Average() throws FileNotFoundException {
		Operations operations = new Operations();
		LinkedList list = new LinkedList();
        Scanner scanner = null;
       
        File file = new File("./src/tests/test5.txt");

        scanner = new Scanner(file);
        
        while (scanner.hasNextLine()){
            String input = scanner.nextLine();

            if (input.isEmpty()){
                break;
            }

            input = input.replace(",", ".");

            double element = Double.parseDouble(input);
            list = list.insert(list, element);
        }
		
		return operations.average(list);
	}
	
	@SuppressWarnings("resource")
	double fileNotFoundCase_Average() throws FileNotFoundException {
		Operations operations = new Operations();
		LinkedList list = new LinkedList();
        Scanner scanner = null;
       
        File file = new File("./src/tests/test4.txt");

        scanner = new Scanner(file);
        
        while (scanner.hasNextLine()){
            String input = scanner.nextLine();

            if (input.isEmpty()){
                break;
            }

            input = input.replace(",", ".");

            double element = Double.parseDouble(input);
            list = list.insert(list, element);
        }
		
		return operations.average(list);
	}
	
	double cliCommaCase_Std() {
		Operations operations = new Operations();
		LinkedList list = new LinkedList();
		int count = 0;
		
		while (count < 6){
            String input = "13,9";

            input = input.replace(",", ".");

            double element = Double.parseDouble(input);
            list = list.insert(list, element);
            count += 1;
        }
		
		return operations.std(list);
	}
	
	double cliDotCase_Std() {
		Operations operations = new Operations();
		LinkedList list = new LinkedList();
		int count = 0;
		
		while (count < 6){
            String input = "13.9";

            input = input.replace(",", ".");

            double element = Double.parseDouble(input);
            list = list.insert(list, element);
            count += 1;
        }
		
		return operations.std(list);
	}
	
	double cliEmptyCase_Std() {
		Operations operations = new Operations();
		LinkedList list = new LinkedList();
		
		return operations.std(list);
	}
	
	@SuppressWarnings("resource")
	double fileCommaCase_Std() throws FileNotFoundException {
		Operations operations = new Operations();
		LinkedList list = new LinkedList();
        Scanner scanner = null;
       
        File file = new File("./src/tests/test2.txt");

        scanner = new Scanner(file);
        
        while (scanner.hasNextLine()){
            String input = scanner.nextLine();

            if (input.isEmpty()){
                break;
            }

            input = input.replace(",", ".");

            double element = Double.parseDouble(input);
            list = list.insert(list, element);
        }
		
		return operations.std(list);
	}
	
	@SuppressWarnings("resource")
	double fileDotCase_Std() throws FileNotFoundException {
		Operations operations = new Operations();
		LinkedList list = new LinkedList();
        Scanner scanner = null;
       
        File file = new File("./src/tests/test3.txt");

        scanner = new Scanner(file);
        
        while (scanner.hasNextLine()){
            String input = scanner.nextLine();

            if (input.isEmpty()){
                break;
            }

            input = input.replace(",", ".");

            double element = Double.parseDouble(input);
            list = list.insert(list, element);
        }
		
		return operations.std(list);
	}
	
	@SuppressWarnings("resource")
	double fileEmptyCase_Std() throws FileNotFoundException {
		Operations operations = new Operations();
		LinkedList list = new LinkedList();
        Scanner scanner = null;
       
        File file = new File("./src/tests/test5.txt");

        scanner = new Scanner(file);
        
        while (scanner.hasNextLine()){
            String input = scanner.nextLine();

            if (input.isEmpty()){
                break;
            }

            input = input.replace(",", ".");

            double element = Double.parseDouble(input);
            list = list.insert(list, element);
        }
		
		return operations.std(list);
	}
	
	@SuppressWarnings("resource")
	double fileNotFoundCase_Std() throws FileNotFoundException {
		Operations operations = new Operations();
		LinkedList list = new LinkedList();
        Scanner scanner = null;
       
        File file = new File("./src/tests/test4.txt");

        scanner = new Scanner(file);
        
        while (scanner.hasNextLine()){
            String input = scanner.nextLine();

            if (input.isEmpty()){
                break;
            }

            input = input.replace(",", ".");

            double element = Double.parseDouble(input);
            list = list.insert(list, element);
        }
		
		return operations.std(list);
	}

	@Test
	void testAverage() throws FileNotFoundException {
		assertEquals(13.9, cliCommaCase_Average());
		assertEquals(13.9, cliDotCase_Average());
		//assertEquals(-1, cliEmptyCase_Average());
		//assertEquals(60.32, fileCommaCase_Average());
		//assertEquals(60.32, fileDotCase_Average());
		//assertEquals(-1, fileEmptyCase_Average());
		Assertions.assertThrows(FileNotFoundException.class, new Executable() {

	        @Override
	        public void execute() throws Throwable {
	        	fileNotFoundCase_Average();
	        }
	    });
	}

	@Test
	void testStd() throws FileNotFoundException {
		assertEquals(0, cliCommaCase_Std());
		assertEquals(0, cliDotCase_Std());
		//assertEquals(-1, cliEmptyCase_Std());
		//assertEquals(59.06, fileCommaCase_Std());
		//assertEquals(59.06, fileDotCase_Std());
		//assertEquals(-1, fileEmptyCase_Std());
		Assertions.assertThrows(FileNotFoundException.class, new Executable() {

	        @Override
	        public void execute() throws Throwable {
	        	fileNotFoundCase_Std();
	        }
	    });
	}

}
