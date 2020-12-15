package arrayvisitors.adt;
import java.util.List;
import arrayvisitors.visitors.Element;
public interface MyArrayListI extends Element{
	public void addToData(Element element);
	public List<Element> getArrayList();
	public int getSize();
}