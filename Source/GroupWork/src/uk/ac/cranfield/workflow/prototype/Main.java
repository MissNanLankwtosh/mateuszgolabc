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
import uk.ac.cranfield.workflow.prototype.model.StablePoint;
import uk.ac.cranfield.workflow.prototype.model.interfaces.Module;
import uk.ac.cranfield.workflow.prototype.model.mock.ModuleMock;
import uk.ac.cranfield.workflow.prototype.view.WorkflowManagerView;
import uk.ac.cranfield.workflow.prototype.view.WorkflowSequenceView;


public class Main
{
    
    private static String[] parameterFiles = {"param.dat", "param2.dat", "simulation1.xml"};
    
    public static void success()
    {
        Simulation simulation = new Simulation(1, Arrays.asList(parameterFiles), "simulation.xml", 5);
        
        WorkflowQueue queue = new WorkflowQueueImpl();
        queue.push(simulation);
        
        // database manager
        DatabaseManager dataBaseManager = new DatabaseManagerMock();
        dataBaseManager.insertStablePoint(new StablePoint(1, 2, "pathOut", "pathIn", 1));
        dataBaseManager.insertStablePoint(new StablePoint(2, 3, "pathOut", "pathIn", 2));
        dataBaseManager.insertStablePoint(new StablePoint(3, 4, "pathOut", "pathIn", 3));
        
        // workflow manager
        WorkflowManagerView workflowManagerView = new WorkflowManagerView();
        WorkflowManager workflowManager = new WorkflowManagerImpl(queue, dataBaseManager, workflowManagerView);
        
        // workflow sequence
        WorkflowSequenceView workflowSequenceView = new WorkflowSequenceView();
        WorkflowSequenceImpl workflowSequenceImpl = new WorkflowSequenceImpl(workflowManager, workflowSequenceView);
        
        
        // modules with correct input and output
        workflowSequenceImpl.addModule(new ModuleMock(true, true, 1));
        workflowSequenceImpl.addModule(new ModuleMock(true, true, 2));
        workflowSequenceImpl.addModule(new ModuleMock(true, true, 3));
        workflowSequenceImpl.addModule(new ModuleMock(true, true, 4));
        
        workflowManager.startSimulation(workflowSequenceImpl);
    }
    
    public static void recoverySucceeded()
    {
        Simulation simulation = new Simulation(1, Arrays.asList(parameterFiles), "simulation.xml", 5);
        
        WorkflowQueue queue = new WorkflowQueueImpl();
        queue.push(simulation);
        
        // database manager
        DatabaseManager dataBaseManager = new DatabaseManagerMock();
        dataBaseManager.insertStablePoint(new StablePoint(1, 2, "pathOut", "pathIn", 1));
        
        
        // workflow manager
        WorkflowManagerView workflowManagerView = new WorkflowManagerView();
        WorkflowManager workflowManager = new WorkflowManagerImpl(queue, dataBaseManager, workflowManagerView);
        
        
        // workflow sequence
        WorkflowSequenceView workflowSequenceView = new WorkflowSequenceView();
        WorkflowSequenceImpl workflowSequenceImpl = new WorkflowSequenceImpl(workflowManager, workflowSequenceView);
        
        
        Module module = new ModuleMock(false, true, 1);
        workflowSequenceImpl.addModule(module);
        
        workflowManager.startSimulation(workflowSequenceImpl);
        
    }
    
    
    public static void recoveryFailed()
    {
        
    }
    
    public static void main(String[] args)
    {
        // success();
        recoverySucceeded();
        
    }
}
