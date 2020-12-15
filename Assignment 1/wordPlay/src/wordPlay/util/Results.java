package wordPlay.util;
import java.io.FileWriter; 
import java.io.IOException;
import java.util.LinkedList;
import java.util.ListIterator;
/**
 * This class implements Interfaces FileDisplay and StdOutDisplay
 * Results class stores the processed words in the form of linkedlist
 *  (processedString) that it received from WordRotator->Helper.
 * It also stores the two metrics that it gets from metrics class.
 */
public class Results implements FileDisplayInterface, StdoutDisplayInterface {
	
	 
	private LinkedList<String> listOfWords = new LinkedList<String>();
	private float AVG_WORD_LENGTH;
	private float AVG_NUM_WORDS_PER_SENTENCE;
	private String outputFilePath;
	private String metricFilePath;
	
	@Override
	public String toString() {
		return "Results [listOfWords=" + listOfWords + ", AVG_WORD_LENGTH=" + AVG_WORD_LENGTH
				+ ", AVG_NUM_WORDS_PER_SENTENCE=" + AVG_NUM_WORDS_PER_SENTENCE + ", outputFilePath=" + outputFilePath
				+ ", metricFilePath=" + metricFilePath + "]";
	}

	/**
	  * This constructs the class with output file path and metrics file path
	  * @param outputFilePath : output file path
	  * @param metricFilePath : metrics file path
	  */
	public Results(String outputFilePath , String metricFilePath) {
		this.outputFilePath = outputFilePath;
		this.metricFilePath = metricFilePath;
	}
	
     /**
      * This method writes to StdOutDisplay. 
      */
	public void writeToStdOut() 
	{
		System.out.println("OUTPUT : ");
		ListIterator<String> listIterator = listOfWords.listIterator();
		while (listIterator.hasNext()) {
			System.out.print(listIterator.next());
		}
		System.out.println("METRICS:");
		System.out.println("AVG_NUM_WORDS_PER_SENTENCE:" + AVG_NUM_WORDS_PER_SENTENCE);
		System.out.println("AVG_WORD_LENGTH:" + AVG_WORD_LENGTH);
	}
	
	/**
	 * This method opens the file for writing
	 * @param filePath : To be written 
	 * @return object of the fileWriter class
	 * @throws IOException : 
	 */
	public FileWriter openFile(String filePath) throws IOException {
			FileWriter obj = new FileWriter(filePath,false);
			return obj;
			
	}
	/**
	 * This method closes the file after writing is completed
	 * @param obj
	 * @throws IOException
	 */
	public void closeFile(FileWriter obj) throws IOException{
		obj.close();
	}
	
	/**
	 * This method writes to file using the parameters 
	 * we got from the constructor
	 * : outputFilePath and metricFilePath
	 */
	public void writeToFile() 
	{
	try {
		FileWriter obj;
		obj = openFile(outputFilePath);
		ListIterator<String> listIterator = listOfWords.listIterator();
		while (listIterator.hasNext()) {
			obj.write(listIterator.next());
		}
		closeFile(obj);
	    
		obj = openFile(metricFilePath);
		obj.write("AVG_NUM_WORDS_PER_SENTENCE:" + AVG_NUM_WORDS_PER_SENTENCE);
		obj.write("\nAVG_WORD_LENGTH:" + AVG_WORD_LENGTH);
		closeFile(obj);
	 
	    }
	
	catch(Exception e) 
		{
		System.err.println("Write to file failed!");
		}
	finally 
		{
		
		}
	
	}
	/**
	 * This method stores all the words character by character
	 *  that it gets from the WordRotator class.
	 * @param s : processed Char 's' 
	 */
	public void Store(LinkedList<String> l) {
		listOfWords.addAll(l);
		
	}
	/**
	 * Setter method for AVG_WORD_LENGTH that we get from metrics class
	 * @param AVG_WORD_LENGTH 
	 */
	public void setAVG_WORD_LENGTH(float AVG_WORD_LENGTH) {
		this.AVG_WORD_LENGTH = AVG_WORD_LENGTH;
	}

	/**
	 * Setter method for AVG_NUM_WORDS_PER_SENTENCE 
	 * that we get from metrics class
	 * @param AVG_NUM_WORDS_PER_SENTENCE
	 */
	public void setAVG_NUM_WORDS_PER_SENTENCE(float AVG_NUM_WORDS_PER_SENTENCE) {
		this.AVG_NUM_WORDS_PER_SENTENCE = AVG_NUM_WORDS_PER_SENTENCE;
	}
	/**
	 * Getter method for ListOfWords
	 * @return
	 */
	public LinkedList<String> getListOfWords() {
		return listOfWords;
	}
	/**
	 * Setter Method for listOfWords
	 * @param listOfWords
	 */
	public void setListOfWords(LinkedList<String> listOfWords) {
		this.listOfWords = listOfWords;
	}
	/**
	 * Getter method for AVG_WORD_LENGTH
	 * @return
	 */
	public float getAVG_WORD_LENGTH() {
		return AVG_WORD_LENGTH;
	}
	/**
	 * Getter method for AVG_NUM_OF_WORDS_PER_SENTENCE
	 * @return
	 */
	public float getAVG_NUM_WORDS_PER_SENTENCE() {
		return AVG_NUM_WORDS_PER_SENTENCE;
	}

	
	
	
	
	
	


	
}
