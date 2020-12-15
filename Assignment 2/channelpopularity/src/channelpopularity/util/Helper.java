package channelpopularity.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.Arrays;
import java.util.List;

import channelpopularity.context.ChannelContext;
import channelpopularity.operation.Operation;
import channelpopularity.state.StateName;
import channelpopularity.state.factory.SimpleStateFactoryI;
import channelpopularity.exceptions.InputFileEmptyException;
public class Helper {


	String inputFilePath;
	String outputFilePath;
	/**
	 * Constructor
	 * @param inputFilePath
	 * @param outputFilePath
	 */
	public Helper(String inputFilePath,String outputFilePath) {
		this.inputFilePath = inputFilePath;
		this.outputFilePath = outputFilePath;
	}

	@Override
	public String toString() {
		return "Helper [inputFilePath=" + inputFilePath + ", outputFilePath=" + outputFilePath + "]";
	}

	/** Help Function . Does all the necessary work such polling and sending to the right method in context class
	 * @return Results object to the driver
	 * @throws Exception
	 */
	public Results help() throws Exception {
		try {
			SimpleStateFactoryI simpleStateFactoryIn = null;
			List<StateName> enumList = Arrays.asList(StateName.class.getEnumConstants());
			Results store = new Results(outputFilePath);
			ChannelContext c = new ChannelContext(simpleStateFactoryIn,enumList , store);
			FileProcessor fileprocess = new FileProcessor(inputFilePath);
			File f = new File(inputFilePath);
			if(f.length()==0){
				throw new InputFileEmptyException(inputFilePath);
			}

			String[] parts = null;  
			while(true)

			{	String line= fileprocess.poll();
			if(line == null) {
				break;
			}
			else {
				switch(Operation.valueOf(line.substring(0, 3))){
				case ADD:
					if(line.matches("ADD_VIDEO::.+$")) {
						c.addVideo(line.substring(11));
						}
					else {
						throw new IllegalArgumentException(line);
					}
					break;
				case MET:
					if(line.matches("METRICS__.+::\\[VIEWS=[0-9-.]?[0-9.]*,LIKES=[0-9.-]?[0-9.]*,DISLIKES=[0-9-.]?[0-9.]*\\]")) {
						parts = line.split("::\\[");
						String[] v = null;
						v = parts[0].split("__");
						String videoName = v[1];
						v = parts[1].split("[=,\\]]");
						c.metrics(store, videoName,Integer.valueOf(v[1]),Integer.valueOf(v[3]),Integer.valueOf(v[5]));
						
					}
					else {
						throw new IllegalArgumentException(line);
					}
					break;
				case AD_:
					if(line.matches("AD_REQUEST__.+::LEN=[0-9.-]?[0-9.]*$")){
						parts = line.split("::L");
						String[] v = null;
						v = parts[0].split("__");
						String videoName = v[1];
						v = parts[1].split("=");
						c.adRequest(videoName,Integer.valueOf(v[1]));}
					else {
						throw new IllegalArgumentException(line);
					}
					break;
				case REM :
					if(line.matches("REMOVE_VIDEO::.+$")) {
						c.removeVideo(line.substring(14));
					}
					else{
						throw new IllegalArgumentException(line);
					}
					break;

				}

			}
			}


			return store;
		} catch (InvalidPathException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		}catch(InputFileEmptyException e) {
			e.printStackTrace();
			System.exit(0);
		}
		catch(NumberFormatException e) {
			System.err.println("Values for views, likes, dislikes or ad length are not integers");
			e.printStackTrace();
			System.exit(0);
		}
		catch(IllegalArgumentException e) {
			System.err.println("Line in the input file does not follow specified format");
			e.printStackTrace();
			System.exit(0);
		}
		catch(StringIndexOutOfBoundsException e) {
			System.err.println("New Line detected. Input file does not follow specified format");
			e.printStackTrace();
			System.exit(0);
		}
		catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		}

		return null;





	}


}
