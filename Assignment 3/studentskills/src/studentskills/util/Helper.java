package studentskills.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.InvalidPathException;

import studentskills.mytree.TreeHelper;
import studentskills.util.MyLogger.DebugLevel;
public class Helper {

	String inputFilePath;
	String modifyFilePath;
	/**
	 * 
	 * Constructor
	 * @param inputFilePath
	 * @param modifyFilePath
	 */
	public Helper(String inputFilePath,String modifyFilePath) {
		this.inputFilePath = inputFilePath;
		this.modifyFilePath = modifyFilePath;
		MyLogger.writeMessage("Enetered Helper's Constructor", DebugLevel.CONSTRUCTOR);


	}


	/**
	 * Poll the input file and modify file
	 * 
	 * @param storeOutput1 : Results class' instance
	 * @param storeOutput2 : Results class' instance
	 * @param storeOutput3 : Results class' instance
	 * @param errorLog : ErrorLog class' instance
	 * @param DebugValue : Get the debugValue for log to be printed
	 * @return
	 */
	public TreeHelper pollInputFile(Results storeOutput1,Results storeOutput2, Results storeOutput3,ErrorLog errorLog,int DebugValue) {
		MyLogger.setDebugValue(DebugValue);
		TreeHelper treeHelper = new TreeHelper(storeOutput1,storeOutput2,storeOutput3,errorLog);
		try {
			FileProcessor fileProcessor = new FileProcessor(inputFilePath);

			while(true) {
				String line = fileProcessor.poll();
				MyLogger.writeMessage("Enetered input line processing : " + line, DebugLevel.INPUT_FILE_POLL);
				if(line==null) {
					break;
				}
				String[] parts = line.split(":|\\,");
				if(parts[0].length() !=4) {
					System.err.println("Bnumber is not equal to 4 or new line detected. ");
					throw new IllegalArgumentException();


				}
				int size = parts.length;
				if(size > 16 ) {
					System.err.println("Line has more parameters than expected");
					throw new IllegalArgumentException(line);

				}

				treeHelper.nodeKickStart(parts,size);

			}

			fileProcessor = new FileProcessor(modifyFilePath);
			while(true) {
				String line = fileProcessor.poll();
				MyLogger.writeMessage("Enetered input line processing : " + line, DebugLevel.MODIFY_FILE_POLL);
				if(line==null) {
					break;
				}

				String[] parts = line.split(":|\\,");

				if(parts[1].length() != 4) {
					System.err.println("Bnumber is not equal to 4 or new line detected.");
					throw new IllegalArgumentException(line);
				}

				int size = parts.length;
				if(size > 4 ) {
					System.err.println("Line has more parameters than expected");
					throw new IllegalArgumentException(line);
				}
				if(size<4){
					//System.out.println("Missing values for modification");
					errorLog.addToErrorLog("Missing modification value for : "+ line);

				}
				else {
					treeHelper.delegateToModifyNode(parts, size);
				}	
			}


		}
		catch(FileNotFoundException e) {

			e.printStackTrace();
			System.exit(0);
		}
		catch(InvalidPathException e) {
			e.printStackTrace();
			System.exit(0);
		}
		catch(IllegalArgumentException e) {

			e.printStackTrace();
			System.exit(0);
		}



		catch ( SecurityException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		catch(StringIndexOutOfBoundsException e) {
			e.printStackTrace();
			System.exit(0);
		}
		return treeHelper;




	}




}