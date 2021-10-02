package uk.ac.cranfield.workflow.prototype;

import uk.ac.cranfield.workflow.prototype.controller.interfaces.DatabaseManager;
import uk.ac.cranfield.workflow.prototype.controller.interfaces.WorkflowManager;
import uk.ac.cranfield.workflow.prototype.controller.interfaces.WorkflowQueue;
import uk.ac.cranfield.workflow.prototype.controller.interfaces.WorkflowSequence;
import uk.ac.cranfield.workflow.prototype.controller.mock.DatabaseManagerMock;
import uk.ac.cranfield.workflow.prototype.controller.mock.WorkflowManagerMock;
import uk.ac.cranfield.workflow.prototype.controller.mock.WorkflowQueueMock;
import uk.ac.cranfield.workflow.prototype.controller.mock.WorkflowSequenceMock;


public class Main
{
    
    public static void main(String[] args)
    {
        WorkflowQueue queue = new WorkflowQueueMock();
        DatabaseManager dataBaseManager = new DatabaseManagerMock();
        WorkflowManager workflowManager = new WorkflowManagerMock(queue, dataBaseManager);
        WorkflowSequence workflowSequence = new WorkflowSequenceMock(workflowManager);
        
        
    }
}
