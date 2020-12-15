package textdecorators.util;

public class Validations {
	public void validate(String inputFileName,String misspelledFileName, String keywordsFileName,String debugValue) {
		try {
			if(inputFileName.equals(misspelledFileName) ) {
				System.err.println("inputFileName & misspelledFileName have same path/names");
				throw new IllegalArgumentException(inputFileName);
			}
			if(misspelledFileName.equals(keywordsFileName) ) {
				System.err.println("misspelledFileName & keywordsFileName have same path/names");
				throw new IllegalArgumentException(keywordsFileName);
			}
			if(keywordsFileName.equals(inputFileName)) {
				System.err.println("keywordsFileName & inputFileName have same path/names");
				throw new IllegalArgumentException(inputFileName);
			}
			/**
			 * Check for debug value is an integer or not
			 */
			Integer.parseInt(debugValue);

		}
		catch(NumberFormatException e) {
			System.err.println("Given debug value is not an integer. Enter values from 0 to 4");
		}
		catch(IllegalArgumentException e){
			e.printStackTrace();
			System.exit(0);
		}
	}

}
