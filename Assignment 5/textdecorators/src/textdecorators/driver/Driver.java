package textdecorators.driver;


import textdecorators.AbstractTextDecorator;
import textdecorators.KeywordDecorator;
import textdecorators.MostFrequentWordDecorator;
import textdecorators.SentenceDecorator;
import textdecorators.SpellCheckDecorator;
import textdecorators.inputdetails.InputDetails;
import textdecorators.util.Validations;
import textdecorators.util.FileDisplayInterface;
import textdecorators.util.MyLogger;
import textdecorators.util.StdoutDisplayInterface;
/**
 * @author Shubham Patwa
 *
 */
public class Driver {
	private static final int REQUIRED_NUMBER_OF_CMDLINE_ARGS = 5;

	public static void main(String[] args) throws Exception {

		/*
		 * As the build.xml specifies the arguments as input,output or metrics, in case the
		 * argument value is not given java takes the default value specified in
		 * build.xml. To avoid that, below condition is used
		 */
		if ((args.length != 5) || (args[0].equals("${input}")) || (args[1].equals("${misspelled}") || (args[2].equals("${keywords}") || (args[3].equals("${output}") || (args[4].equals("${debug}")))))) {
			System.err.printf("Error: Incorrect number of arguments. Program accepts %d arguments.", REQUIRED_NUMBER_OF_CMDLINE_ARGS);
			System.exit(0);
		}
		/**
		 * Validate the input parameters first
		 */
		Validations validator = new Validations();
		validator.validate(args[0], args[1], args[2],args[4]);
		/**
		 * Set debugger value. Debugger logs to "log.txt" file
		 * in the default directory.
		 */
		MyLogger.getInstance().setDebugValue(Integer.valueOf(args[4]));
		InputDetails inputD = new InputDetails(args[0], args[1], args[2],args[3]);
		/**
		 * Parse the inputs given
		 */
		inputD.parseInput();
		/**
		 * Set the decorators.
		 */
		AbstractTextDecorator sentenceDecorator = new SentenceDecorator(null, inputD);
		AbstractTextDecorator spellCheckDecorator = new SpellCheckDecorator(sentenceDecorator, inputD);
		AbstractTextDecorator keywordDecorator = new KeywordDecorator(spellCheckDecorator, inputD);
		AbstractTextDecorator mostFreqWordDecorator = new MostFrequentWordDecorator(keywordDecorator, inputD);

		/**
		 * Start Processing
		 */
		mostFreqWordDecorator.processInputDetails();

		/**
		 * Write to file and Write to StdOut
		 */
		((FileDisplayInterface) inputD).writeToFile();
		((StdoutDisplayInterface) inputD).writeToStdOut(); 



	}
}
