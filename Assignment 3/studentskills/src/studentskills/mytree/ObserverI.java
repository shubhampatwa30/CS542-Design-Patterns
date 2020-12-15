package studentskills.mytree;

import java.util.Set;

import studentskills.operation.Operations;

public interface ObserverI{

	/**
	 * 
	 * Update function for all the observers
	 */
	public void update(Operations operation, int[] checkForChanges, String firstName, String lastName, double gpa,
			String major, Set<String> treeSet);


}