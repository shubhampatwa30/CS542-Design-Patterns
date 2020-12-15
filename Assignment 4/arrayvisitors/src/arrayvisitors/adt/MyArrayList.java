package arrayvisitors.adt;
import java.util.ArrayList;
import java.util.List;

import arrayvisitors.util.MyLogger;
import arrayvisitors.util.MyLogger.DebugLevel;
import arrayvisitors.visitors.Element;
import arrayvisitors.visitors.VisitorI;

public class MyArrayList implements MyArrayListI{
	private List<Element> arrayList = new ArrayList<Element>();

	/**
	 * Explicit value constructor
	 * @param arrayList
	 */
	public MyArrayList(List<Element> arrayList) {
		this.arrayList = arrayList;
	}
	/**
	 * Empty value constructor
	 */
	public MyArrayList() {
		MyLogger.getInstance().writeMessage("Entered MyArrayList Constructor", DebugLevel.CONSTRUCTOR);
	}
	@Override
	public void accept(VisitorI visitor) {
		// TODO Auto-generated method stub
		visitor.visit(this);
	}
	@Override
	public void addToData(Element element) {
		// TODO Auto-generated method stub
		arrayList.add(element);
	}

	/**
	 * Getter and setter methods
	 * @return
	 */
	public List<Element> getArrayList() {
		return arrayList;
	}
	public void setArrayList(List<Element> arrayList) {
		this.arrayList = arrayList;
	}

	public int getSize() {
		return this.arrayList.size();
	}
	/**
	 * Deep clone the object
	 */
	protected Object clone() throws CloneNotSupportedException {
		MyArrayList cloned = (MyArrayList)super.clone();
		List<Element> arrayList = this.arrayList;
		cloned.setArrayList(arrayList);
		return cloned;
	}
	/**
	 * Override finalize method
	 */
	public void finalize() {

	}
	/**
	 * ToString method
	 */
	@Override
	public String toString() {
		return "MyArrayList [arrayList=" + arrayList + ", getArrayList()=" + getArrayList() + ", getSize()=" + getSize()
		+ "]";
	}



}