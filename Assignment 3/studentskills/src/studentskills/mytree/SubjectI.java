package studentskills.mytree;

import studentskills.operation.Operations;

public interface SubjectI{
	public void registerObserver(ObserverI o);
	public void removeObserver(ObserverI o);
	public void notifyObservers(Operations operation);

}