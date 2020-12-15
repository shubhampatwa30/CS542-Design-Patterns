package arrayvisitors.adt;
import arrayvisitors.visitors.Element;

public interface MyArrayI extends Element{
	
	public void addToData(int element);
	public int getSize();
	public int getCurSize();
	public int[] getArray();
	
	
}