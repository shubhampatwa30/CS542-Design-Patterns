package wordPlay.driver;
import wordPlay.util.FileDisplayInterface;
import wordPlay.util.Helper;
import wordPlay.util.Results;
import wordPlay.util.StdoutDisplayInterface;
/**
 * @author John Doe
 *
 */
public class Driver {
	public static void main(String[] args){

		/*
		 * As the build.xml specifies the arguments as input,output or metrics,
		 * in case the argument value is not given java takes the 
		 * default value in build.xml. To avoid that, below condition is used 
		 */
	
		
		if ((args.length != 3) || (args[0].equals("${input}")) || (args[1].equals("${output}")) || (args[2].equals("${metrics}"))) {
			System.err.println("Error: Incorrect number of arguments. Program accepts 3 arguments.");
			System.exit(0);
		}
		/**
		 * Create object of helper class by passing all the file paths
		 */
		Helper obj = new Helper(args[0],args[1],args[2]);
		/**
		 * The helper class returns, that means it has completed processing the file and returns a Results class object file
		 * Store that to run methods of that object
		 */
		Results result = obj.helper();
		/**
		 * Run methods 
		 */
		FileDisplayInterface filewrite = result;
		StdoutDisplayInterface printToStdout = result;
		printToStdout.writeToStdOut();
		filewrite.writeToFile();
		
		
	}
}
