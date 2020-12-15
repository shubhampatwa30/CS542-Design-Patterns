package arrayvisitors.adt;
import java.util.Arrays;
import arrayvisitors.util.MyLogger;
import arrayvisitors.util.MyLogger.DebugLevel;
import arrayvisitors.visitors.VisitorI;

public class MyArray implements MyArrayI{
	private int[] array;
	private int size = 10;
	private int curSize =0 ;
	/**
	 * Explicit value constructor
	 * @param array
	 */
	public MyArray(int[] array) {
		this.array = array;
	}
	/**
	 * Empty Value constructor
	 */
	public MyArray() {
		array = new int[10];
		MyLogger.getInstance().writeMessage("Entered MyArray Constructor", DebugLevel.CONSTRUCTOR);
	}
	/**
	 * Add data to ADT
	 */
	public void addToData(int element) {
		if(curSize == size) {
			this.size = (int) (size * 1.5);
			int[] newArray = Arrays.copyOf(array,size);
			this.array = newArray;
		}
		array[curSize] = element;
		curSize +=1;

	}
	@Override
	public void accept(VisitorI visitor) {
		// TODO Auto-generated method stub
		visitor.visit(this);
	}
	/**
	 * Setter and getter methods
	 * @return
	 */
	public int[] getArray() {
		return array;
	}
	public void setArray(int[] array) {
		this.array = array;
	}
	public void finalize() {
		//Empty finalize method
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getCurSize() {
		return curSize;
	}
	public void setCurSize(int curSize) {
		this.curSize = curSize;
	}
	/**
	 * Deep Clone the object
	 */
	protected Object clone() throws CloneNotSupportedException {
		MyArray cloned = (MyArray)super.clone();
		int[] array = this.array;

		int size = this.size;
		int curSize = this.curSize;
		cloned.setArray(array);
		cloned.setSize(size);
		cloned.setCurSize(curSize);

		return cloned;
	}
	/**
	 * ToString Method	
	 */
	@Override
	public String toString() {
		return "MyArray [array=" + Arrays.toString(array) + ", size=" + size + ", curSize=" + curSize + ", getArray()="
				+ Arrays.toString(getArray()) + ", getSize()=" + getSize() + ", getCurSize()=" + getCurSize() + "]";
	}

}