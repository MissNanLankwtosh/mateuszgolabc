package uk.ac.cranfield.prototype.test;

import static org.junit.Assert.*;

import org.junit.Test;

import uk.ac.cranfield.workflow.prototype.controller.implementation.*;
import uk.ac.cranfield.workflow.prototype.controller.interfaces.*;
import uk.ac.cranfield.workflow.prototype.controller.mock.*;
import uk.ac.cranfield.workflow.prototype.model.interfaces.Module;
import uk.ac.cranfield.workflow.prototype.model.mock.ModuleMock;
import uk.ac.cranfield.workflow.prototype.view.*;

public class WorkflowSequenceTest {

	private WorkflowQueue queue;
	private DatabaseManager dbmanager;
	private WorkflowManagerView wfmview;
	private WorkflowSequenceView wfsview;
	private WorkflowManager manager;
	
	public WorkflowSequenceTest() {
    	queue = new WorkflowQueueImpl();
    	dbmanager = new DatabaseManagerMock();
    	wfmview = new WorkflowManagerView();
    	wfsview = new WorkflowSequenceView();
        manager = new WorkflowManagerImpl(queue, dbmanager, wfmview);
	}
	
    @Test
    public void addModuleTest()
    {
        WorkflowSequence sequence = new WorkflowSequenceImpl(manager, wfsview);
        assertEquals(sequence.getNumberOfModules(), 0);
        
        Module module = new ModuleMock(true, false);  
        sequence.addModule(module);
        assertEquals(sequence.getCurrentModule().getID(), module.getID());
        assertEquals(sequence.getNumberOfModules(), 1);
        
        Module module2 = new ModuleMock(true, true);
        sequence.addModule(module2);
        assertEquals(sequence.getNumberOfModules(), 2);
    }

    @Test
    public void removeModuleTest()
    {
        WorkflowSequence sequence = new WorkflowSequenceImpl(manager, wfsview);
        
        Module module = new ModuleMock(true, false);
        Module module2 = new ModuleMock(true, true);
        sequence.addModule(module);
        assertEquals(sequence.getNumberOfModules(), 1);
        
        sequence.addModule(module2);
        assertEquals(sequence.getNumberOfModules(), 2);
        
        sequence.removeModule(module);
        assertEquals(sequence.getNumberOfModules(), 1);
    }    
    
    @Test
    public void validModuleTest()
    {
        WorkflowSequence sequence = new WorkflowSequenceImpl(manager, wfsview);
      
        sequence.addModule(new ModuleMock(true, true));
        
        sequence.validateModuleInput();
        assertTrue(sequence.isInputStateCorrect());
  
        sequence.executeModule();
        sequence.validateModuleOutput();
        assertTrue(sequence.isOutputStateCorrect());
    }
 
    @Test
    public void invalidInputModuleTest()
    {
        WorkflowSequence sequence = new WorkflowSequenceImpl(manager, wfsview);
      
        sequence.addModule(new ModuleMock(false, true));
        
        sequence.validateModuleInput();
        assertFalse(sequence.isInputStateCorrect());
    }
    
    @Test
    public void invalidOutputModuleTest()
    {
        WorkflowSequence sequence = new WorkflowSequenceImpl(manager, wfsview);
      
        sequence.addModule(new ModuleMock(true, false));
        
        sequence.validateModuleInput();
        assertTrue(sequence.isInputStateCorrect());
  
        sequence.executeModule();
        sequence.validateModuleOutput();
        assertFalse(sequence.isOutputStateCorrect());
    }
    
    @Test
    public void nextModuleTest()
    {
        WorkflowSequence sequence = new WorkflowSequenceImpl(manager, wfsview);
        assertEquals(sequence.getNumberOfModules(), 0);
        
        Module module = new ModuleMock(true, false);  
        sequence.addModule(module);
        
        Module module2 = new ModuleMock(true, true);
        sequence.addModule(module2);
        sequence.nextModule();
        assertEquals(sequence.getCurrentModule().getID(), module2.getID());
    }   
}
