package channelpopularity.driver;

import channelpopularity.util.Helper;
import channelpopularity.util.Results;

/**
 * @author Shubham Patwa
 *
 */
public class Driver {
	private static final int REQUIRED_NUMBER_OF_CMDLINE_ARGS = 2;

	public static void main(String[] args) throws Exception {

		/*
		 * As the build.xml specifies the arguments as input,output or metrics, in case the
		 * argument value is not given java takes the default value specified in
		 * build.xml. To avoid that, below condition is used
		 */
		if ((args.length != 2) || (args[0].equals("${input}")) || (args[1].equals("${output}"))) {
			System.err.printf("Error: Incorrect number of arguments. Program accepts %d arguments.", REQUIRED_NUMBER_OF_CMDLINE_ARGS);
			System.exit(0);
		}

		/**
		 * Run the helper class to start polling and implementing
		 * methods
		 */
		Helper obj = new Helper(args[0],args[1]);
		/**
		 * Helper return a results class object
		 */
		Results store = obj.help();
		/**
		 * Output:
		 * write to stdout()
		 * write to file()
		 */
		store.writeToStdOut();
		store.writeToFile();

	}
}
