package studentskills.mytree;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import studentskills.operation.Operations;
import studentskills.util.MyLogger;
import studentskills.util.MyLogger.DebugLevel;

public class StudentRecord implements SubjectI,ObserverI,Cloneable{

	private List<Object> observers;
	private String bnumber;
	private String firstName;
	private String lastName;
	private double gpa;
	private String major;
	private Set<String> treeSet = new TreeSet<String>();
	private int [] checkForChanges = new int[4];

	/**
	 * Constructor
	 * @param bnumber
	 * @param firstName
	 * @param lastName
	 * @param gpa
	 * @param major
	 * @param treeSet
	 */
	public StudentRecord(String bnumber, String firstName, String lastName, double gpa, String major, Set<String> treeSet) {

		this.bnumber = bnumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gpa = gpa;
		this.major = major;
		this.treeSet = treeSet;
		this.observers = new ArrayList<Object>();
		MyLogger.writeMessage("Enetered Student Record's Constructor", DebugLevel.CONSTRUCTOR);



	}

	/**
	 * Deep Clone the object
	 */
	protected Object clone() throws CloneNotSupportedException {
		StudentRecord cloned = (StudentRecord)super.clone();
		String bnumber = this.bnumber;

		String firstName = this.firstName;
		String lastName = this.lastName;
		double gpa = this.gpa;
		String major = this.major;
		Set<String> treeSet = new HashSet<String>();
		treeSet = this.treeSet;
		int [] checkForChanges = this.checkForChanges;
		List<Object> observers = new ArrayList<Object>();
		cloned.setBnumber(bnumber);
		cloned.setFirstName(firstName);
		cloned.setLastName(lastName);
		cloned.setGpa(gpa);
		cloned.setMajor(major);
		cloned.setHashSet(treeSet);
		cloned.setObservers(observers);
		cloned.setCheckForChanges(checkForChanges);
		return cloned;
	}

	/**
	 * SubjectI functions
	 */
	@Override
	public void registerObserver(ObserverI  o) {
		// TODO Auto-generated method stub
		observers.add(o);

	}

	@Override
	public void removeObserver(ObserverI o) {
		// TODO Auto-generated method stub
		int i = observers.indexOf(o);
		if (i >= 0) {
			observers.remove(i);
		}
	}

	/**
	 * Notify all the observers
	 */
	@Override
	public void notifyObservers(Operations operation) {

		for (int i = 0; i < observers.size(); i++) {
			ObserverI observer = (ObserverI)observers.get(i);
			observer.update(operation,this.checkForChanges , this.getFirstName(), this.lastName, this.gpa, this.major, this.treeSet);
		}

	}

	/**
	 * Set Parameters for insert based call
	 * @param bnumber
	 * @param firstName
	 * @param lastName
	 * @param gpa
	 * @param major
	 * @param treeSet
	 */
	public void setParameters( String firstName, String lastName, double gpa, String major, Set<String> treeSet) 
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.gpa = gpa;
		this.major = major;
		this.treeSet.addAll(treeSet);

	}
	/**
	 * 
	 * Update function for all the observers
	 */
	@Override
	public void update(Operations operation,int [] checkForChanges , String firstName, String lastName, double gpa, String major, Set<String> treeSet) {
		// TODO Auto-generated method stub
		if(operation == Operations.INSERT) {
			this.firstName = firstName;
			this.lastName = lastName;
			this.gpa = gpa;
			this.major = major;
			this.treeSet = treeSet;
		}
		if(operation == Operations.MODIFY) {

			if(checkForChanges[0] == 1) {
				this.firstName = firstName;
			}
			if(checkForChanges[1] == 1) {
				this.lastName = lastName;
			}
			if(checkForChanges[2] == 1) {
				this.major = major;
			}
			if(checkForChanges[3] == 1) {
				this.treeSet = treeSet;
			}


		}

	}
	/**
	 * Getter Setter methods
	 * @return
	 */
	public int[] getCheckForChanges() {
		return checkForChanges;
	}

	public void setCheckForChanges(int[] checkForChanges) {
		this.checkForChanges = checkForChanges;
	}

	public String getBnumber() {
		return bnumber;
	}

	public List<Object> getObservers() {
		return observers;
	}

	public void setObservers(List<Object> observers) {
		this.observers = observers;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public double getGpa() {
		return gpa;
	}

	public String getMajor() {
		return major;
	}

	public Set<String> getTreeSet() {
		return treeSet;
	}

	public void setBnumber(String bnumber) {
		this.bnumber = bnumber;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setGpa(double gpa) {
		this.gpa = gpa;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public void setHashSet(Set<String> treeSet) {
		this.treeSet = treeSet;
	}

	/**
	 * Using toString method for print all the nodes inorder
	 */
	@Override
	public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append(bnumber + ":");

		Iterator<String> it = treeSet.iterator();
		sb.append(it.next());
		while(it.hasNext()){
			sb.append("," + it.next());}
		String s = sb.toString();
		return s;
	}

}