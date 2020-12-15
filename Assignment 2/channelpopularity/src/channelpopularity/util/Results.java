package channelpopularity.util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class Results implements FileDisplayInterface, StdoutDisplayInterface {
	/**
	 *
	 * The results that are to written are stored as linkedlist 
	 */

	private List<String> listOfSent = new LinkedList<String>();
	String outputFilePath;
	/**
	 * Get the output file path
	 * @param outputFilePath
	 */
	public Results(String outputFilePath) {
		this.outputFilePath = outputFilePath;
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
	public void writeToFile() 
	{

		FileWriter obj;
		try {
			obj = openFile(outputFilePath);

			ListIterator<String> listIterator = listOfSent.listIterator();
			while (listIterator.hasNext()) {

				obj.write(listIterator.next());
				obj.write("\n");
			}
			closeFile(obj);
		}
		catch (IOException e) {

			e.printStackTrace();
			System.exit(0);
		}
	}


	public void addToResult(String line) {
		listOfSent.add(line);
	}


	@Override
	public String toString() {
		return "Results [listOfSent=" + listOfSent + ", outputFilePath=" + outputFilePath + "]";
	}





}
