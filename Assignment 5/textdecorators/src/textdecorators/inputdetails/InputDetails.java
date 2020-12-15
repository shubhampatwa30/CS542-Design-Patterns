package textdecorators.inputdetails;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import textdecorators.util.FileDisplayInterface;
import textdecorators.util.ParseInput;
import textdecorators.util.StdoutDisplayInterface;

public class InputDetails implements FileDisplayInterface, StdoutDisplayInterface  {
	String inputFileName;
	String misspelledFileName;
	String keywordsFileName;
	String outputFileName;
	List<StringBuilder> sentences = new LinkedList<StringBuilder>();
	List<String> misspelled = new LinkedList<String>();
	List<String> keywords = new LinkedList<String>();

	public InputDetails(String inputFileName, String misspelledFileName, String keywordsFileName, String outputFileName) {
		this.inputFileName = inputFileName;
		this.misspelledFileName = misspelledFileName;
		this.keywordsFileName = keywordsFileName;
		this.outputFileName = outputFileName;
	}

	public void parseInput() {
		ParseInput parser = new ParseInput(inputFileName);
		addToSentences(parser);
		parser = new ParseInput(inputFileName);
		if(checkForDot(parser) == true) {
			sentences.remove(sentences.size() - 1);	
		}
		parser = new ParseInput(misspelledFileName);
		addToData(parser, misspelled);
		parser = new ParseInput(keywordsFileName);
		addToData(parser,keywords);
	}

	/**
	 * Check whether to include last sentence b	y checking whether last sentence has a dot
	 * @param parser
	 * @return
	 */
	public boolean checkForDot(ParseInput parser){
		StringBuilder s = new StringBuilder();
		parser.useDelimiterZ();
		while(true) {
			/**
			 * parse each line delimited by '.'
			 */
			String line = parser.parse();
			if(line!=null)
			{
				/**
				 * Build the string using string builder  
				 * Append each sentence to a list of string builders
				 */
				s.append(line);
			}
			else {
				break;
			}
		}
		if(s.charAt(s.length() -1 ) == '.' ) {
			return false;
		}
		return true;

	}



	public  void addToSentences(ParseInput parser) {
		// TODO Auto-generated method stub
		parser.useDelimiterDot();
		while(true) {
			/**
			 * parse each line delimited by '.'
			 */
			String line = parser.parse();
			if(line!=null)
			{
				/**
				 * Build the string using string builder 
				 * Without '.' 
				 * Append each sentence to a list of string builders
				 */
				StringBuilder s = new StringBuilder();
				s.append(line);
				this.sentences.add(s);
			}
			else {
				break;
			}
		}



	}

	private void addToData(ParseInput obj,List<String> sentences) {
		obj.useDelimiterSpace();
		while(true) {
			String line = obj.parse();
			if(line!=null)
			{
				/**
				 * Add the keywords/misspelled words to a list of Strings
				 */
				sentences.add(line);
			}
			else {
				break;
			}
		}
	}




	/**
	 * Getter setters
	 * @return
	 */

	public List<StringBuilder> getSentences() {
		return sentences;
	}

	public void setSentences(List<StringBuilder> sentences) {
		this.sentences = sentences;
	}

	public List<String> getMisspelled() {
		return misspelled;
	}

	public void setMisspelled(List<String> misspelled) {
		this.misspelled = misspelled;
	}

	public List<String> getKeywords() {
		return keywords;
	}

	public void setKeywords(ArrayList<String> keywords) {
		this.keywords = keywords;
	}

	@Override
	public void writeToStdOut() {
		// TODO Auto-generated method stub
		for(StringBuilder i : sentences) {
			System.out.print(i);
		}
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
	 * 
	 * Write to output file
	 */
	@Override
	public void writeToFile() 
	{

		FileWriter obj;
		try {
			obj = openFile(outputFileName);
			for(StringBuilder i : sentences) {
				obj.write(i.toString());
			}
			closeFile(obj);
		}
		catch (IOException e) {

			e.printStackTrace();
			System.exit(0);
		}
	}

	@Override
	public String toString() {
		return "InputDetails [inputFileName=" + inputFileName + ", misspelledFileName=" + misspelledFileName
				+ ", keywordsFileName=" + keywordsFileName + ", outputFileName=" + outputFileName + ", sentences="
				+ sentences + ", misspelled=" + misspelled + ", keywords=" + keywords + "]";
	}
}
