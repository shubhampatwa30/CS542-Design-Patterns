package arrayvisitors.visitors;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.InvalidPathException;

import arrayvisitors.adt.MyArrayI;
import arrayvisitors.adt.MyArrayListI;
import arrayvisitors.util.FileProcessor;
import arrayvisitors.util.MyLogger;
import arrayvisitors.util.MyLogger.DebugLevel;

public class PopulateMyArrayVisitor implements VisitorI{
	FileProcessor file;
	public PopulateMyArrayVisitor() {
		MyLogger.getInstance().writeMessage("Entered PopulateMyArray Constructor", DebugLevel.CONSTRUCTOR);
	}
	/**
	 * Populate the ADT using Visit Method
	 */
	public void visit(MyArrayI element) {
		try {
			while(true) {
				String line = file.poll();
				if(line == null) {
					break;
				}
				if(line.length() != 2) {				
					throw new IllegalArgumentException("Not a two digit integer: " + line);
				}
				element.addToData(Integer.valueOf(line));
				MyLogger.getInstance().writeMessage("Entered populating array using PopulateMyArray. Added line data to MyArray: "+line, DebugLevel.POPULATE_MYARRAY_VISITOR);
			}
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
			System.exit(0);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		catch(InvalidPathException e) {
			e.printStackTrace();
			System.exit(0);
		}
		catch(SecurityException e) {
			e.printStackTrace();
			System.exit(0);
		}
		catch(NumberFormatException e) {
			System.err.println("Line in input file does not contain integer");
			e.printStackTrace();
			System.exit(0);
		}
		catch(IllegalArgumentException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	public FileProcessor getFile() {
		return file;
	}

	public void setFile(FileProcessor file) {
		this.file = file;
	}

	@Override
	public void visit(MyArrayListI element) {
		try{
			throw new IllegalArgumentException("Got into wrong method");
		}
		catch(IllegalArgumentException e) {
			e.printStackTrace();
			System.exit(0);
		}

	}
	@Override
	public String toString() {
		return "PopulateMyArrayVisitor [file=" + file + ", getFile()=" + getFile() + "]";
	}









}