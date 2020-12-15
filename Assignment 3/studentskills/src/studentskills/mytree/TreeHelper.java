package studentskills.mytree;

import java.util.Set;
import java.util.TreeSet;

import studentskills.operation.Operations;
import studentskills.util.ErrorLog;
import studentskills.util.MyLogger;
import studentskills.util.Results;
import studentskills.util.MyLogger.DebugLevel;

public class TreeHelper{
	AVLTree<String, StudentRecord> replica0;
	AVLTree<String, StudentRecord> replica1;
	AVLTree<String, StudentRecord> replica2;
	ErrorLog errorLog ;
	Set<String> treeSet = null;
	/**
	 * Constructor
	 * 
	 * @param storeOutput1 : Results class instance
	 * @param storeOutput2 : Results class instance
	 * @param storeOutput3 : Results class instance
	 * @param errorLog : ErrorLog class instance
	 */
	public TreeHelper(Results storeOutput1, Results storeOutput2, Results storeOutput3,ErrorLog errorLog) {

		this.replica0 = new AVLTree<String, StudentRecord>(storeOutput1);
		this.replica1 = new AVLTree<String, StudentRecord>(storeOutput2);
		this.replica2 = new AVLTree<String, StudentRecord>(storeOutput3);
		this.errorLog = errorLog;
		MyLogger.writeMessage("Enetered Tree Helper Constructor", DebugLevel.CONSTRUCTOR);
	}

	/**
	 * Function for insert the data received from the input line.
	 * Check whether the insert call is new or reinsert type
	 * 
	 * @param parts : Data from the line splitted into parts
	 * @param size : Size of parts
	 */
	public void nodeKickStart(String[] parts,int size) {

		treeSet = new TreeSet<String>();

		for(int i=5; i<size;i++ ) {
			treeSet.add(parts[i]);
		}

		StudentRecord foundNode = replica0.lookup(parts[0]);
		/**
		 * Newly Insert call
		 */
		if(foundNode  == null){		
			StudentRecord replicaNode0 = new StudentRecord(parts[0],parts[1],parts[2],Double.valueOf(parts[3]),parts[4],treeSet);
			MyLogger.writeMessage("INSERT MODE : " + parts[0] + parts[1] + parts[2], DebugLevel.INSERT);
			try {
				StudentRecord replicaNode1 = (StudentRecord) replicaNode0.clone();
				StudentRecord replicaNode2 = (StudentRecord) replicaNode0.clone();
				replicaNode0.registerObserver(replicaNode1);
				replicaNode0.registerObserver(replicaNode2);
				replicaNode1.registerObserver(replicaNode0);
				replicaNode1.registerObserver(replicaNode2);
				replicaNode2.registerObserver(replicaNode0);
				replicaNode2.registerObserver(replicaNode1);
				replica0.insert(replicaNode0.getBnumber(), replicaNode0);
				replica1.insert(replicaNode1.getBnumber(), replicaNode1);
				replica2.insert(replicaNode2.getBnumber(), replicaNode2);	



			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		/**
		 * Reinsert call
		 */
		else {
			MyLogger.writeMessage("RE INSERT MODE : " + parts[0] + parts[1] + parts[2], DebugLevel.RE_INSERT);
			foundNode.setParameters(parts[1], parts[2], Double.valueOf(parts[3]), parts[4], treeSet);
			foundNode.notifyObservers(Operations.INSERT);

		}



	}




	/**
	 * Modify call from modifyFile
	 * @param parts : Data from each line splitted into parts
	 * @param size : Size of parts
	 */
	public void delegateToModifyNode(String[] parts, int size) {


		String toBeSearched = parts[2];
		String toBeModified = parts[3];
		MyLogger.writeMessage("MODIFY NODE : " + parts[0] + parts[1] + parts[2] + parts[3], DebugLevel.MODIFY);
		AVLTree<String, StudentRecord> replicaTreeMain = getReplicaTree(parts[0]);
		if(replicaTreeMain != null) {
			StudentRecord node = replicaTreeMain.lookup(parts[1]);
			if(node != null) {
				this.modifyNode(node, toBeSearched, toBeModified);
			}
			else {

				this.errorLog.addToErrorLog("Bnumber doesnot exists for its values to be modified  : " + parts[1]);

			}
		}
		else {

			this.errorLog.addToErrorLog("This tree does not exists for line: " + parts[0] + " with Bnumber :" + parts[1]);
		}



	}

	/**
	 * Get the replica Tree Id from the line
	 * @param replicaTreeId
	 * @return
	 */
	public AVLTree<String, StudentRecord> getReplicaTree(String replicaTreeId) {

		if(replicaTreeId.equals("0")) {
			return replica0;
		}
		else if(replicaTreeId.equals("1")) {
			return replica1;
		}
		else if(replicaTreeId.equals("2")) {
			return replica2;
		}
		else {

			return null;

		}

	}

	public void modifyNode(StudentRecord node, String toBeSearched,String toBeModified) {
		int changeFlag = 0;
		if(node.getFirstName().equals(toBeSearched))
		{	node.setFirstName(toBeModified);
		node.getCheckForChanges()[0] =1;
		changeFlag=1;

		}
		if(node.getLastName().equals(toBeSearched))
		{	node.setLastName(toBeModified);
		node.getCheckForChanges()[1] =1;
		changeFlag =1;

		}
		else if(node.getMajor().equals(toBeSearched))
		{	node.setMajor(toBeModified);
		node.getCheckForChanges()[2] =1;
		changeFlag=1;


		}


		for(String skills : node.getTreeSet()) {

			if(skills.equals(toBeSearched)) {
				node.getTreeSet().remove(toBeSearched);
				node.getTreeSet().add(toBeModified);
				node.getCheckForChanges()[3] =1;
				changeFlag=1;
				break;
			}


		}

		if(changeFlag == 1) {
			node.notifyObservers(Operations.MODIFY);
			for(int i=0;i<4;i++) {
				node.getCheckForChanges()[i] = 0;
			}
		}
		else {
			///log error
			this.errorLog.addToErrorLog("Original Value to be modified not found : '" + toBeSearched + "' for Bnumber : "+ node.getBnumber());
			changeFlag = 0;
		}


	}
	public void printAllNodes() {

		replica0.printNodes();
		replica1.printNodes();
		replica2.printNodes();

	}

}
