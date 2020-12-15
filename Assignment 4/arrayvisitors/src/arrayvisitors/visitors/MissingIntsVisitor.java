package arrayvisitors.visitors;

import java.util.Set;
import java.util.TreeSet;

import arrayvisitors.adt.MyArrayI;
import arrayvisitors.adt.MyArrayListI;
import arrayvisitors.util.MyLogger;
import arrayvisitors.util.MyLogger.DebugLevel;
import arrayvisitors.util.Results;

public class MissingIntsVisitor implements VisitorI{
	Results resultForMissingIntsOut;
	public MissingIntsVisitor() {
		MyLogger.getInstance().writeMessage("Entered MissingIntsOut Constructor", DebugLevel.CONSTRUCTOR);
	}
	/**
	 * Calculate the missing Integers using the visit method
	 */
	@Override
	public void visit(MyArrayI element) {
		Set<Integer> set= new TreeSet<Integer>();
		Set<Integer> set2= new TreeSet<Integer>();
		
		for(int i=0;i< element.getCurSize();i++) {
			set.add(element.getArray()[i]);
		}
		
		for(int i =0 ;i<100 ; i++) {
			if(!set.contains(i)) {
				set2.add(i);
			}
		}

		resultForMissingIntsOut.addToResult(set2);
		MyLogger.getInstance().writeMessage("Entered MissingintsVisitor. Found missing elements and added to results: ", DebugLevel.MISSING_INTS_VISITOR);

	}
	@Override
	public void visit(MyArrayListI element) {
		try{

		}
		catch(IllegalArgumentException e) {
			e.printStackTrace();
			System.exit(0);
		}

	}
	public Results getResultForMissingIntsOut() {
		return resultForMissingIntsOut;
	}
	public void setResultForMissingIntsOut(Results resultForMissingIntsOut) {
		this.resultForMissingIntsOut = resultForMissingIntsOut;
	}
	@Override
	public String toString() {
		return "MissingIntsVisitor [resultForMissingIntsOut=" + resultForMissingIntsOut
				+ ", getResultForMissingIntsOut()=" + getResultForMissingIntsOut() + "]";
	}



}