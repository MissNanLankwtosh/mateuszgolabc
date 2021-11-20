package uk.ac.cranfield.prototype.test;

import static org.junit.Assert.*;

import org.junit.Test;

import uk.ac.cranfield.workflow.prototype.controller.implementation.WorkflowManagerImpl;
import uk.ac.cranfield.workflow.prototype.controller.implementation.WorkflowQueueImpl;
import uk.ac.cranfield.workflow.prototype.controller.implementation.WorkflowSequenceImpl;
import uk.ac.cranfield.workflow.prototype.controller.interfaces.DatabaseManager;
import uk.ac.cranfield.workflow.prototype.controller.interfaces.WorkflowManager;
import uk.ac.cranfield.workflow.prototype.controller.interfaces.WorkflowQueue;
import uk.ac.cranfield.workflow.prototype.controller.interfaces.WorkflowSequence;
import uk.ac.cranfield.workflow.prototype.controller.mock.DatabaseManagerMock;
import uk.ac.cranfield.workflow.prototype.model.Simulation;
import uk.ac.cranfield.workflow.prototype.model.interfaces.Module;
import uk.ac.cranfield.workflow.prototype.model.mock.ModuleMock;
import uk.ac.cranfield.workflow.prototype.view.WorkflowManagerView;
import uk.ac.cranfield.workflow.prototype.view.WorkflowSequenceView;


public class WorkflowManagerTest
{
	
	private WorkflowQueue queue;
	private DatabaseManager dbmanager;
	private WorkflowManagerView wfmview;
	private WorkflowSequenceView wfsview;
	private WorkflowSequence wfseq;
	private WorkflowManager wfmanager;
	
	public WorkflowManagerTest() {
    	queue = new WorkflowQueueImpl();
    	dbmanager = new DatabaseManagerMock();
    	wfmview = new WorkflowManagerView();
    	wfsview = new WorkflowSequenceView();
	}
	
    @Test
    public void validSimulationTest()
    {
    	wfmanager = new WorkflowManagerImpl(queue, dbmanager, wfmview);
       	wfseq = new WorkflowSequenceImpl(wfmanager, wfsview);
       	
       	Simulation sim = new Simulation(1, null, "File.XML", 2);
       	queue.push(sim);
       	
       	Module module = new ModuleMock(true, true, 1);
        Module module2 = new ModuleMock(true, true, 2);
       	
       	wfseq.addModule(module);
        wfseq.addModule(module2);
       	
       	wfmanager.startSimulation(wfseq);
     	assertTrue(wfmanager.sendResult());
    }
	
    @Test
    public void invalidSimulationTest()
    {
    	wfmanager = new WorkflowManagerImpl(queue, dbmanager, wfmview);
       	wfseq = new WorkflowSequenceImpl(wfmanager, wfsview);
       	
       	Simulation sim = new Simulation(1, null, "File.XML", 2);
       	queue.push(sim);
       	
       	Module module = new ModuleMock(false, true, 1);
       	wfseq.addModule(module);
       	
       	wfmanager.startSimulation(wfseq);
     	assertFalse(wfmanager.sendResult());
    }
    
}
