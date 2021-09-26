package uk.ac.cranfield.workflow.prototype;

import uk.ac.cranfield.workflow.prototype.controller.interfaces.DatabaseManager;
import uk.ac.cranfield.workflow.prototype.controller.interfaces.WorkflowManager;
import uk.ac.cranfield.workflow.prototype.controller.interfaces.WorkflowSequence;
import uk.ac.cranfield.workflow.prototype.controller.mock.DatabaseManagerMock;
import uk.ac.cranfield.workflow.prototype.controller.mock.WorkflowManagerMock;
import uk.ac.cranfield.workflow.prototype.controller.mock.WorkflowSequenceMock;


public class Main
{
    
    public static void main(String[] args)
    {
        WorkflowManager workflowManager = new WorkflowManagerMock();
        WorkflowSequence workflowSequence = new WorkflowSequenceMock(workflowManager);
        DatabaseManager dataBaseManager = new DatabaseManagerMock(workflowManager);
    }
    
}
