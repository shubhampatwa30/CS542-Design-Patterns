package textdecorators.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.InvalidPathException;
import java.util.Scanner;
/**
 * File processor/parser
 * @author Shubham
 *
 */
public class ParseInput {
	String inputFileName;
	Scanner scanner;
	long size;
	public ParseInput(String inputFileName) {
		this.inputFileName = inputFileName;
		File file = new File(inputFileName);
		this.initialise(file);

	}

	/**
	 * Whether to separate by delimiter '.'
	 * Required for separating sentences
	 */
	public void useDelimiterDot() {
		scanner.useDelimiter("[.]");
	}
	public void useDelimiterSpace() {
		scanner.useDelimiter("[\\s]+");
	}
	public void useDelimiterZ() {
		scanner.useDelimiter("\\Z");
	}



	/**
	 * Parsing the file
	 * @return
	 */
	public String parse() {		
		String sentence = null;
		try {
			if (scanner.hasNext()) {
				sentence = scanner.next();
				/**
				 * Boundary condition check whether it has only these characters
				 */
				if(!sentence.matches("[a-zA-Z0-9\\.,\\s]*")) {
					throw new IllegalArgumentException(sentence);
				}
			}
			else {
				scanner.close();
				return null;
			}

		} catch (InvalidPathException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		}
		catch(IllegalArgumentException e) {
			System.err.println("String does not match the set [a-zA-Z0-9\\.,\\s]");
			e.printStackTrace();
			System.exit(0);
		}
		return sentence; 


	}

	/**
	 * Validations
	 * @param file
	 */
	public void initialise(File file) {
		try {
			/**
			 * Validate whether file exists or not and whether it is empty
			 */

			if(!file.exists()) {
				System.err.println("File does not exists");
				throw new FileNotFoundException(file.getName());
			}
			if(file.length() == 0) {
				System.err.println("File is Empty");
				throw new IllegalArgumentException(file.getName());
			}
			scanner = new Scanner(file);
			size = file.length();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		} catch(IllegalArgumentException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	@Override
	public String toString() {
		return "ParseInput [scanner=" + scanner + "]";
	}
}
