package uk.ac.cranfield.workflow.prototype;

import java.util.Arrays;

import uk.ac.cranfield.workflow.prototype.controller.implementation.WorkflowSequenceImpl;
import uk.ac.cranfield.workflow.prototype.controller.interfaces.DatabaseManager;
import uk.ac.cranfield.workflow.prototype.controller.interfaces.WorkflowManager;
import uk.ac.cranfield.workflow.prototype.controller.interfaces.WorkflowQueue;
import uk.ac.cranfield.workflow.prototype.controller.mock.DatabaseManagerMock;
import uk.ac.cranfield.workflow.prototype.controller.mock.WorkflowManagerMock;
import uk.ac.cranfield.workflow.prototype.controller.mock.WorkflowQueueMock;
import uk.ac.cranfield.workflow.prototype.model.Simulation;
import uk.ac.cranfield.workflow.prototype.model.mock.ModuleMock;


public class Main
{
    
    private static String[] parameterFiles = {"param.dat", "param2.dat", "simulation1.xml"};
    
    public static void main(String[] args)
    {
        Simulation simulation = new Simulation(1, Arrays.asList(parameterFiles), "simulation.xml");
        
        WorkflowQueue queue = new WorkflowQueueMock();
        queue.push(simulation);
        
        
        DatabaseManager dataBaseManager = new DatabaseManagerMock();
        WorkflowManager workflowManager = new WorkflowManagerMock(queue, dataBaseManager);
        
        
        WorkflowSequenceImpl workflowSequenceImpl = new WorkflowSequenceImpl(workflowManager);
        workflowSequenceImpl.addModule(new ModuleMock());
        workflowSequenceImpl.addModule(new ModuleMock());
        workflowSequenceImpl.addModule(new ModuleMock());
        
        workflowManager.startSimulation(workflowSequenceImpl);
        
    }
}
