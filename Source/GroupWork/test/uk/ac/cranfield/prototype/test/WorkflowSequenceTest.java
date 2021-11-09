package uk.ac.cranfield.prototype.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import uk.ac.cranfield.workflow.prototype.controller.implementation.WorkflowManagerImpl;
import uk.ac.cranfield.workflow.prototype.controller.implementation.WorkflowQueueImpl;
import uk.ac.cranfield.workflow.prototype.controller.implementation.WorkflowSequenceImpl;
import uk.ac.cranfield.workflow.prototype.controller.interfaces.DatabaseManager;
import uk.ac.cranfield.workflow.prototype.controller.interfaces.WorkflowManager;
import uk.ac.cranfield.workflow.prototype.controller.interfaces.WorkflowQueue;
import uk.ac.cranfield.workflow.prototype.controller.interfaces.WorkflowSequence;
import uk.ac.cranfield.workflow.prototype.controller.mock.DatabaseManagerMock;
import uk.ac.cranfield.workflow.prototype.model.interfaces.Module;
import uk.ac.cranfield.workflow.prototype.model.mock.ModuleMock;
import uk.ac.cranfield.workflow.prototype.view.WorkflowManagerView;
import uk.ac.cranfield.workflow.prototype.view.WorkflowSequenceView;

public class WorkflowSequenceTest
{
    
    private WorkflowQueue queue;
    private DatabaseManager dbmanager;
    private WorkflowManagerView wfmview;
    private WorkflowSequenceView wfsview;
    private WorkflowManager manager;
    
    public WorkflowSequenceTest()
    {
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
        
        Module module = new ModuleMock(true, false, 2);
        sequence.addModule(module);
        assertEquals(sequence.getCurrentModule().getID(), module.getID());
        assertEquals(sequence.getNumberOfModules(), 1);
        
        Module module2 = new ModuleMock(true, true, 1);
        sequence.addModule(module2);
        assertEquals(sequence.getNumberOfModules(), 2);
    }
    
    @Test
    public void removeModuleTest()
    {
        WorkflowSequence sequence = new WorkflowSequenceImpl(manager, wfsview);
        
        Module module = new ModuleMock(true, false, 1);
        Module module2 = new ModuleMock(true, true, 2);
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
        
        sequence.addModule(new ModuleMock(true, true, 1));
        
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
        
        sequence.addModule(new ModuleMock(false, true, 1));
        
        sequence.validateModuleInput();
        assertFalse(sequence.isInputStateCorrect());
    }
    
    @Test
    public void invalidOutputModuleTest()
    {
        WorkflowSequence sequence = new WorkflowSequenceImpl(manager, wfsview);
        
        sequence.addModule(new ModuleMock(true, false, 1));
        
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

        Module module = new ModuleMock(true, false, 1);
        sequence.addModule(module);
        assertEquals(sequence.getCurrentModule().getID(), module.getID());
        
        Module module2 = new ModuleMock(true, true, 2);
        sequence.addModule(module2);
        sequence.nextModule();
        assertEquals(sequence.getCurrentModule().getID(), module2.getID());
    }
}
