package studentskills.util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class ErrorLog implements FileDisplayInterface{

	private List<String> errorLog = new LinkedList<String>();

	String errorFilePath;


	/**
	 * Get the error file path
	 * @param errorFilePath
	 */
	public ErrorLog(String errorFilePath) {
		this.errorFilePath = errorFilePath;

	}
	/**
	 * 
	 * Write to output file
	 */
	public void writeToFile() 
	{

		FileWriter obj;
		try {
			obj = new FileWriter(errorFilePath,false);


			ListIterator<String> listIterator = errorLog.listIterator();
			while (listIterator.hasNext()) {

				obj.write(listIterator.next());
				obj.write("\n");
			}

			obj.close();
		}
		catch (IOException e) {

			e.printStackTrace();
			System.exit(0);
		}
	}

	/**
	 * Add data to ErrorLog
	 * @param line
	 */
	public void addToErrorLog(String line) {
		errorLog.add(line);
	}





}
