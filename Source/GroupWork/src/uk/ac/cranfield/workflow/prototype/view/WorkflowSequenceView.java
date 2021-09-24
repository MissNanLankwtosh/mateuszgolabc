package uk.ac.cranfield.workflow.prototype.view;

public class WorkflowSequenceView {

	void printRecoveryStarted() {
		
		System.out.println("Recovery started.");
	}
	
	void printRecoveryCompleted() {
		
		System.out.println("Recovery completed.");
	}
	
	void printError(String errorName) {
		
		System.out.println("Error occured in WorkflowSequence! Type: " + errorName + ".");
	}
}
