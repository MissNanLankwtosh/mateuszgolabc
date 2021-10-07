package uk.ac.cranfield.workflow.prototype;

import uk.ac.cranfield.workflow.prototype.controller.implementation.WorkflowSequenceImpl;
import uk.ac.cranfield.workflow.prototype.controller.interfaces.DatabaseManager;
import uk.ac.cranfield.workflow.prototype.controller.interfaces.WorkflowManager;
import uk.ac.cranfield.workflow.prototype.controller.interfaces.WorkflowQueue;
import uk.ac.cranfield.workflow.prototype.controller.mock.DatabaseManagerMock;
import uk.ac.cranfield.workflow.prototype.controller.mock.WorkflowManagerMock;
import uk.ac.cranfield.workflow.prototype.controller.mock.WorkflowQueueMock;


public class Main
{
    
    public static void main(String[] args)
    {
        // Simulation simulation = new Simulation(scientistID, parameterFiles, xmlName)
        
        WorkflowQueue queue = new WorkflowQueueMock();
        // queue.push(simulation)
        
        
        DatabaseManager dataBaseManager = new DatabaseManagerMock();
        WorkflowManager workflowManager = new WorkflowManagerMock(queue, dataBaseManager);
        WorkflowSequenceImpl workflowSequenceImpl = new WorkflowSequenceImpl(workflowManager);
        
        
        workflowManager.startSimulation(workflowSequenceImpl);
        
    }
}
