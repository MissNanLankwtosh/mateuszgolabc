package uk.ac.cranfield.workflow.prototype.controller.mock;

import java.util.LinkedList;

import uk.ac.cranfield.workflow.prototype.controller.interfaces.WorkflowQueue;
import uk.ac.cranfield.workflow.prototype.model.Simulation;


public class WorkflowQueueMock implements WorkflowQueue
{
    
    private LinkedList<Simulation> queue;
    
    public WorkflowQueueMock()
    {
        queue = new LinkedList<Simulation>();
    }
    
    @Override
    public boolean isEmpty()
    {
        return queue.isEmpty();
    }
    
    @Override
    public Simulation pop()
    {
        return queue.removeFirst();
    }
    
    @Override
    public void push(Simulation simulation)
    {
        queue.addLast(simulation);
    }
    
}
