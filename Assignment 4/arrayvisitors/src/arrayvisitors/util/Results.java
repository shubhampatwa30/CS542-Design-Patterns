package arrayvisitors.util;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

import arrayvisitors.util.MyLogger.DebugLevel;

public  class Results implements FileDisplayInterface, StdoutDisplayInterface {
	private Set<Integer> set= new TreeSet<Integer>();
	String outputFilePath;
	/**
	 * Get the output file path
	 * @param outputFilePath
	 */
	public Results(String outputFilePath) {
		this.outputFilePath = outputFilePath;
		MyLogger.getInstance().writeMessage("Entered Results Constructor", DebugLevel.CONSTRUCTOR);
	}
	/**
	 * Print to screen
	 */
	public void writeToStdOut() 
	{
		System.out.println("OUTPUT : ");
		
		for( int i:set ) {
			if(i>9) {
			System.out.println(i);
			}
			else {
				System.out.println("0"+i);
			}
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
			for(int i: set) {
				if(i>9) {
				obj.write(String.valueOf(i));
				obj.write("\n");
				}
				else {
					obj.write("0"+String.valueOf(i));
					obj.write("\n");
				}
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
	public void addToResult(Set<Integer> set) {
		this.set = set;
	}
}
