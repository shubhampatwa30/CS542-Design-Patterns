package arrayvisitors.visitors;
import java.util.Set;
import java.util.TreeSet;

import arrayvisitors.adt.MyArray;
import arrayvisitors.adt.MyArrayI;
import arrayvisitors.adt.MyArrayListI;
import arrayvisitors.util.MyLogger;
import arrayvisitors.util.MyLogger.DebugLevel;
import arrayvisitors.util.Results;

public class CommonIntsVisitor implements VisitorI {
	Results resultForCommonIntsOut;
	public CommonIntsVisitor(Results resultForCommonIntsOut) {
		this.resultForCommonIntsOut = resultForCommonIntsOut;
		MyLogger.getInstance().writeMessage("Entered CommonIntsOut Constructor", DebugLevel.CONSTRUCTOR);
	}
	/**
	 * Calculate the common integers in ADT
	 */
	@Override
	public void visit(MyArrayListI element) {
		Set<Integer> set1= new TreeSet<Integer>();
		Set<Integer> set2= new TreeSet<Integer>();
		MyArray instance1 = (MyArray) element.getArrayList().get(0);
		MyArray instance2 = (MyArray) element.getArrayList().get(1);
		for(int i: instance1.getArray()) {
			set1.add(i);
		}
		for(int i=0;i<instance1.getCurSize();i++) {
			set1.add(instance1.getArray()[i]);
		}

		for(int i=0;i<instance2.getCurSize();i++) {
			set2.add(instance2.getArray()[i]);
		}
		
		set1.retainAll(set2);
		this.resultForCommonIntsOut.addToResult(set1);
		MyLogger.getInstance().writeMessage("Entered commonintsVisitor. Found calculated elements and added to results: ", DebugLevel.COMMON_INTS_VISITOR);
	}
	@Override
	public void visit(MyArrayI element) {
		try{
			throw new IllegalArgumentException("Got into wrong method");
		}
		catch(IllegalArgumentException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
	public Results getResultForCommonIntsOut() {
		return resultForCommonIntsOut;
	}
	public void setResultForCommonIntsOut(Results resultForCommonIntsOut) {
		this.resultForCommonIntsOut = resultForCommonIntsOut;
	}
	@Override
	public String toString() {
		return "CommonIntsVisitor [resultForCommonIntsOut=" + resultForCommonIntsOut + ", getResultForCommonIntsOut()="
				+ getResultForCommonIntsOut() + "]";
	}




}