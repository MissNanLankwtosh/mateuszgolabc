package uk.ac.cranfield.workflow.prototype.view;

public class WorkflowSequenceView {

	public void printRecoveryStarted() {
		
		System.out.println("Recovery started.");
	}
	
	public void printRecoveryCompleted() {
		
		System.out.println("Recovery completed.");
	}
	
	public void printError(String errorName) {
		
		System.out.println("Error occured in WorkflowSequence! Type: " + errorName + ".");
	}
}
