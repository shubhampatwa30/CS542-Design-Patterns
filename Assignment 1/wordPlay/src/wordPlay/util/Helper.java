package wordPlay.util;
import java.io.File;
import java.util.LinkedList;
import wordPlay.handler.WordRotator;
import wordPlay.metrics.MetricsCalculator;
/**
 * This Class is the helper class.
 * The driver class calls this class' object 
 * which then performs all the required operations. 
 * @author Shubham
 *
 */
public class Helper {
	
	private String inputFilePath;
	private String outputFilePath;
	private String metricFilePath;
	private LinkedList<String> processedString = new LinkedList<String>();
	/**
	 * This constructs and stores all the required file paths. 
	 * @param inputFilePath : Input File Path
	 * @param outputFilePath : Output File Path
	 * @param metricFilePath : Metrics File Path
	 */
	public Helper(String inputFilePath,String outputFilePath, String metricFilePath)
	{
	this.inputFilePath = inputFilePath;
	this.outputFilePath = outputFilePath;
	this.metricFilePath = metricFilePath;
	}
	@Override
	public String toString() {
		return "Helper [inputFilePath=" + inputFilePath + ", outputFilePath=" + outputFilePath
				+ ", metricFilePath=" + metricFilePath + ", processedString=" + processedString + "]";
	}
	/**
	 * This is the helper method which does the required operations.
	 * Processing(wordRotating) is delegated to WordRotator Class
	 * The Parameters required for the calculating the metrics 
	 * are given to the metrics class for calculations. 
	 * @return : the Results class' object which Helper class' 
	 * uses for storing the words(linkedlist) and metrics
	 * @throws Exception 
	 */
	public Results helper() {	
		WordRotator rotate = new WordRotator();
		FileProcessor inputfile= null;
		Results storeInResult = new Results(this.outputFilePath,this.metricFilePath);
		int INDEX= 1;
		float SENT_COUNT = 0;
		float TOTAL_WORDS = 0;
		float TOTAL_CHARACTERS =0 ;
		try {
			File f = new File(inputFilePath);
			/**
			 * Boundary Check for missing input file
			 * 
			 */
			if(f.exists() == false) {
				throw new Exception("Missing input file");
			}
			/**
			 * Boundary Check for empty input file
			 * 
			 */
			if(f.length()==0){
				throw new Exception("Empty input file");
			}
			
			inputfile = new FileProcessor(inputFilePath);
			/**
			 * Start looping through the words
			 */
			while(true) {	
			String s = 	inputfile.poll();
			if(s!=null) {
				TOTAL_WORDS++;
				int LENGTH = s.length();
				int ROTATION_INDEX;
				/**
				 * Check for empty line. 				 * 
				 */
				if(s.length() == 0) {
					throw new Exception("Empty line in input file");
				}
				if(s.charAt(LENGTH - 1) == '.'){
					/**
					 * Boundary Check for String in [a-zA-Z0-9] by removing the period
					 */
					if(s.substring(0,LENGTH-1).matches("^[a-zA-Z0-9]+$")) {
					TOTAL_CHARACTERS += LENGTH-1;
				ROTATION_INDEX =(LENGTH-1) -  (INDEX%(LENGTH-1));
				processedString = rotate.Rotate(s, ROTATION_INDEX, LENGTH-1, 1);
				storeInResult.Store(processedString);
				SENT_COUNT++;
				INDEX =0;
				
					
				}else {
					throw new Exception("Words contain characters other than [a-zA-Z0-9]");
				}
					}
				else {
					/**
					 * Boundary Check for String in [a-zA-Z0-9]
					 */
					if(s.matches("^[a-zA-Z0-9]+$")) {
					TOTAL_CHARACTERS += LENGTH;
					ROTATION_INDEX =LENGTH -  (INDEX%LENGTH);
					processedString = rotate.Rotate(s, ROTATION_INDEX, LENGTH, 0);
					storeInResult.Store(processedString);
					
				}else {
					throw new Exception("Words contain characters other than [a-zA-Z0-9]");

				}
					}
				INDEX++;
			
				}
			else {
				break;
			}
			}
			/**
			 * Delegate to metrics class for calculation
			 */
			MetricsCalculator metricobj = new MetricsCalculator(TOTAL_CHARACTERS, TOTAL_WORDS, SENT_COUNT);
			/**
			 * Store the calculated metrics in Results class
			 */
			storeInResult.setAVG_NUM_WORDS_PER_SENTENCE(metricobj.getAVG_NUM_WORDS_PER_SENTENCE());
			storeInResult.setAVG_WORD_LENGTH(metricobj.getAVG_WORD_LENGTH());
		}	

		/**
		 * For all other exceptions
		 */
		catch (Exception ex) 
		{
	    ex.printStackTrace();
	    System.exit(0);
	    
		}
		
		finally {
		
		}
		
		return storeInResult;
		
	}
	
	}
	
		
	
	
	

