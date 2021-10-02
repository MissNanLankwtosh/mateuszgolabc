package uk.ac.cranfield.prototype.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import uk.ac.cranfield.workflow.prototype.controller.interfaces.WorkflowQueue;
import uk.ac.cranfield.workflow.prototype.controller.mock.WorkflowQueueMock;
import uk.ac.cranfield.workflow.prototype.model.Simulation;


public class WorkflowQueueTest
{
    
    @Test
    public void queueTest()
    {
        Simulation s = new Simulation(1, null, null);
        Simulation s2 = new Simulation(2, null, null);
        
        WorkflowQueue queue = new WorkflowQueueMock();
        queue.push(s);
        queue.push(s2);
        
        assertEquals(queue.isEmpty(), false);
        assertEquals(queue.pop().getScientistID(), 1);
        assertEquals(queue.pop().getScientistID(), 2);
        assertEquals(queue.isEmpty(), true);
    }
    
}
