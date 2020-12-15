package textdecorators.util;

import java.io.FileWriter;
import java.io.IOException;

public class MyLogger{
	public static MyLogger myLogger_instance = null;
	/**
	 * Create a singleton Logger
	 * @return
	 */
	public static MyLogger getInstance() {
		if(myLogger_instance == null) {
			myLogger_instance = new MyLogger();
		}
		return myLogger_instance;
	}


	public  enum DebugLevel {MOST_FREQ_WORD_DECORATOR,KEYWORD_DECORATOR,SPELL_CHECK_DECORATOR,SENTENCE_DECORATOR, NONE
	};

	private  DebugLevel debugLevel;

	public  void setDebugValue (int levelIn) {
		if(levelIn!=0) {
			intiateFile();
		}
		switch (levelIn) {
		case 4: debugLevel = DebugLevel.MOST_FREQ_WORD_DECORATOR; break;
		case 3: debugLevel = DebugLevel.KEYWORD_DECORATOR; break;
		case 2: debugLevel = DebugLevel.SPELL_CHECK_DECORATOR; break;
		case 1: debugLevel = DebugLevel.SENTENCE_DECORATOR; break;
		default: debugLevel = DebugLevel.NONE; break;
		}
	}

	/**
	 * This replaces a already existing file with a new file
	 * Since, the debugger is going to append to the same existing file later,
	 * we might want to first create a new file after all.
	 * @param file
	 */
	private void intiateFile() {
		// TODO Auto-generated method stub
		FileWriter obj;
		try {
			obj = new FileWriter("log.txt",false);
			obj.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void writeMessage (String  message,DebugLevel levelIn ) {
		if (levelIn == debugLevel)
			//System.out.println(message);
			writeToFile(message);
	}

	public String toString() {
		return "The debug level has been set to the following " + debugLevel;
	}
	/**
	 * This method opens the file for writing
	 * @param filePath : To be written 
	 * @return object of the fileWriter class
	 * @throws IOException : 
	 */
	public FileWriter openFile(String filePath) throws IOException {
		FileWriter obj = new FileWriter(filePath,true);
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
	public void writeToFile(String message) 
	{
		FileWriter obj;
		try {
			obj = openFile("log.txt");
			obj.write(message);
			obj.write("\n");
			closeFile(obj);
		}
		catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

}