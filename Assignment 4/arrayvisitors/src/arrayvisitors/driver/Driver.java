package arrayvisitors.driver;
import arrayvisitors.adt.MyArray;
import arrayvisitors.adt.MyArrayList;
import arrayvisitors.adt.MyArrayListI;
import arrayvisitors.util.FileDisplayInterface;
import arrayvisitors.util.FileProcessor;
import arrayvisitors.util.MyLogger;
import arrayvisitors.util.Results;
import arrayvisitors.util.StdoutDisplayInterface;
import arrayvisitors.util.Validations;
import arrayvisitors.visitors.CommonIntsVisitor;
import arrayvisitors.visitors.Element;
import arrayvisitors.visitors.MissingIntsVisitor;
import arrayvisitors.visitors.PopulateMyArrayVisitor;
import arrayvisitors.visitors.VisitorI;

/**
 * @author Shubham Patwa
 *
 */
public class Driver {
	private static final int REQUIRED_NUMBER_OF_CMDLINE_ARGS = 6;

	public static void main(String[] args) throws Exception {

		/*
		 * As the build.xml specifies the arguments as input,output or metrics, in case the
		 * argument value is not given java takes the default value specified in
		 * build.xml. To avoid that, below condition is used
		 */
		if ((args.length != 6 ) ){ 
			System.err.printf("Error: Incorrect number of arguments. Program accepts %d arguments.", REQUIRED_NUMBER_OF_CMDLINE_ARGS);
			System.err.println("Enter debug value as:");
			System.err.println("Case default: NONE");
			System.err.println("Case 1: CONSTRUCTOR");
			System.err.println("Case 2: POPULATE_MYARRAY_VISITOR");
			System.err.println("Case 3: COMMON_INTS_VISITOR");
			System.err.println("Case 4: MISSING_INTS_VISITOR");
			System.exit(0);
		}

		/**
		 * The driver should do the following:
			Create required instances of Results.
			Create instance of FileProcessor.
			Create instances of the visitors.
			Create two instances of MyArray.
			Use the PopulateMyArrayVisitor to populate two instances of MyArray.
			Apply CommonIntsVisitor to determine common ints in ADTs stored in MyArrayList.
			Apply MissingIntsVisitor separately to each of the MyArray instances.
			Call appropriate methods in Result instances to print the output of each of the visitors.

		 */

		//Some Validations
		Validations validator = new Validations();
		validator.validate(args[0], args[1], args[2], args[3]);
		//Set debugger value
		MyLogger.getInstance().setDebugValue(Integer.valueOf(args[5]));
		//Create required instances of Results.
		FileDisplayInterface resultForFile;
		StdoutDisplayInterface resultForStdout;
		Results resultForCommonIntsOut = new Results(args[2]);
		Results resultForMissingIntsOut1= new Results(args[3]);
		Results resultForMissingIntsOut2= new Results(args[4]);
		//Create instance of FileProcessor.
		FileProcessor file1 = new FileProcessor(args[0]);
		FileProcessor file2 = new FileProcessor(args[1]);
		//Create instances of the visitors.
		VisitorI populateMyData = new PopulateMyArrayVisitor();
		PopulateMyArrayVisitor forSupportPopulate = (PopulateMyArrayVisitor)populateMyData;
		VisitorI commonIntsOutVisitor = new CommonIntsVisitor(resultForCommonIntsOut);
		VisitorI missingIntsOutVisitor = new MissingIntsVisitor();
		MissingIntsVisitor supportForMissingInts = (MissingIntsVisitor)missingIntsOutVisitor;
		//Create two instances of MyArray.
		Element myArrayInstance1 = new MyArray();
		Element myArrayInstance2 = new MyArray();
		MyArrayListI myArrayList = new MyArrayList();
		myArrayList.addToData(myArrayInstance1);
		myArrayList.addToData(myArrayInstance2);
		//Use the PopulateMyArrayVisitor to populate two instances of MyArray.	
		forSupportPopulate.setFile(file1);
		myArrayInstance1.accept(populateMyData);
		forSupportPopulate.setFile(file2);
		myArrayInstance2.accept(populateMyData);
		//Apply CommonIntsVisitor to determine common ints in ADTs stored in MyArrayList.
		myArrayList.accept(commonIntsOutVisitor);
		//Apply MissingIntsVisitor separately to each of the MyArray instances.
		supportForMissingInts.setResultForMissingIntsOut(resultForMissingIntsOut1);
		myArrayInstance1.accept(missingIntsOutVisitor);
		supportForMissingInts.setResultForMissingIntsOut(resultForMissingIntsOut2);
		myArrayInstance2.accept(missingIntsOutVisitor);
		//Call appropriate methods in Result instances to print the output of each of the visitors.
		resultForFile = resultForCommonIntsOut;
		resultForStdout = resultForCommonIntsOut;
		resultForFile.writeToFile();
		resultForStdout.writeToStdOut();
		resultForFile =resultForMissingIntsOut1;
		resultForStdout = resultForMissingIntsOut1;
		resultForFile.writeToFile();
		resultForStdout.writeToStdOut();
		resultForFile =resultForMissingIntsOut2;
		resultForStdout = resultForMissingIntsOut2;
		resultForFile.writeToFile();
		resultForStdout.writeToStdOut();
	}
}
