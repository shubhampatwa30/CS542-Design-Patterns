package studentskills.util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import studentskills.util.MyLogger.DebugLevel;

public  class Results implements FileDisplayInterface, StdoutDisplayInterface {

	private List<String> listOfSent = new LinkedList<String>();

	String outputFilePath;

	/**
	 * Get the output file path
	 * @param outputFilePath
	 */
	public Results(String outputFilePath) {
		this.outputFilePath = outputFilePath;
		MyLogger.writeMessage("Enetered Results Constructor", DebugLevel.CONSTRUCTOR);

	}

	/**
	 * Print to screen
	 */
	public void writeToStdOut() 
	{
		System.out.println("OUTPUT : ");
		ListIterator<String> listIterator = listOfSent.listIterator();
		while (listIterator.hasNext()) {
			System.out.println(listIterator.next());
		}

	}



	/**
	 * 
	 * Write to output file
	 */
	public void writeToFile() 
	{

		FileWriter obj;
		try {
			obj = new FileWriter(outputFilePath,false);


			ListIterator<String> listIterator = listOfSent.listIterator();
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
	 * 
	 * Add data to Results
	 * @param line
	 */
	public void addToResult(String line) {
		listOfSent.add(line);
	}








}
