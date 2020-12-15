package arrayvisitors.visitors;

import arrayvisitors.adt.MyArrayI;
import arrayvisitors.adt.MyArrayListI;

public interface VisitorI{
	public void visit(MyArrayI element);
	public void visit(MyArrayListI element);


}