package studentskills.driver;

import studentskills.mytree.TreeHelper;
import studentskills.util.ErrorLog;
import studentskills.util.FileDisplayInterface;
import studentskills.util.Helper;
import studentskills.util.Results;
import studentskills.util.StdoutDisplayInterface;

/**
 * @author Shubham Patwa
 *
 */
public class Driver {
	private static final int REQUIRED_NUMBER_OF_CMDLINE_ARGS = 7;

	public static void main(String[] args) throws Exception {

		/*
		 * As the build.xml specifies the arguments as input,output or metrics, in case the
		 * argument value is not given java takes the default value specified in
		 * build.xml. To avoid that, below condition is used
		 */
		if ((args.length != 7 ) ){ 
			System.err.printf("Error: Incorrect number of arguments. Program accepts %d arguments.", REQUIRED_NUMBER_OF_CMDLINE_ARGS);
			System.exit(0);
		}

		/**
		 * Run the helper class to start polling.
		 * Create results instance and pass to corresponding trees
		 * Print to dislay and write to file
		 */
		Helper obj = new Helper(args[0],args[1]);
		Results storeOutput1 = new Results(args[2]);
		Results storeOutput2 = new Results(args[3]);
		Results storeOutput3 = new Results(args[4]);
		ErrorLog errorLog = new ErrorLog(args[5]);

		TreeHelper treeHelper = obj.pollInputFile(storeOutput1,storeOutput2,storeOutput3,errorLog,Integer.valueOf(args[6]));
		treeHelper.printAllNodes();
		FileDisplayInterface fileWrite = storeOutput1;
		StdoutDisplayInterface printToStdOut = storeOutput1;
		fileWrite.writeToFile();
		printToStdOut.writeToStdOut();
		fileWrite = storeOutput2;
		printToStdOut = storeOutput2;
		fileWrite.writeToFile();
		printToStdOut.writeToStdOut();
		fileWrite = storeOutput3;
		printToStdOut = storeOutput3;
		fileWrite.writeToFile();
		printToStdOut.writeToStdOut();
		fileWrite = errorLog;
		fileWrite.writeToFile();


	}
}
