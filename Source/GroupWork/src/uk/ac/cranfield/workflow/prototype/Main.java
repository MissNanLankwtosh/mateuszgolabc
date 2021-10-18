package uk.ac.cranfield.workflow.prototype;

import java.util.Arrays;

import uk.ac.cranfield.workflow.prototype.controller.implementation.WorkflowManagerImpl;
import uk.ac.cranfield.workflow.prototype.controller.implementation.WorkflowQueueImpl;
import uk.ac.cranfield.workflow.prototype.controller.implementation.WorkflowSequenceImpl;
import uk.ac.cranfield.workflow.prototype.controller.interfaces.DatabaseManager;
import uk.ac.cranfield.workflow.prototype.controller.interfaces.WorkflowManager;
import uk.ac.cranfield.workflow.prototype.controller.interfaces.WorkflowQueue;
import uk.ac.cranfield.workflow.prototype.controller.mock.DatabaseManagerMock;
import uk.ac.cranfield.workflow.prototype.model.Simulation;
import uk.ac.cranfield.workflow.prototype.model.mock.ModuleMock;
import uk.ac.cranfield.workflow.prototype.view.WorkflowManagerView;
import uk.ac.cranfield.workflow.prototype.view.WorkflowSequenceView;


public class Main
{
    
    private static String[] parameterFiles = {"param.dat", "param2.dat", "simulation1.xml"};
    
    public static void success()
    {
        Simulation simulation = new Simulation(1, Arrays.asList(parameterFiles), "simulation.xml");
        
        WorkflowQueue queue = new WorkflowQueueImpl();
        queue.push(simulation);
        
        // database manager
        DatabaseManager dataBaseManager = new DatabaseManagerMock();
        
        // workflow manager
        WorkflowManagerView workflowManagerView = new WorkflowManagerView();
        WorkflowManager workflowManager = new WorkflowManagerImpl(queue, dataBaseManager, workflowManagerView);
        
        // workflow sequence
        WorkflowSequenceView workflowSequenceView = new WorkflowSequenceView();
        WorkflowSequenceImpl workflowSequenceImpl = new WorkflowSequenceImpl(workflowManager, workflowSequenceView);
        
        // modules with correct input and output
        workflowSequenceImpl.addModule(new ModuleMock(true, true));
        workflowSequenceImpl.addModule(new ModuleMock(true, true));
        workflowSequenceImpl.addModule(new ModuleMock(true, true));
        
        workflowManager.startSimulation(workflowSequenceImpl);
    }
    
    public static void recoverySucceeded()
    {
        
    }
    
    
    public static void recoveryFailed()
    {
        
    }
    
    public static void main(String[] args)
    {
        success();
        
    }
}
