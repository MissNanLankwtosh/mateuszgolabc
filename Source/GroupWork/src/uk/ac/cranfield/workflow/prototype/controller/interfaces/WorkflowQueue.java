package uk.ac.cranfield.workflow.prototype.controller.interfaces;

import uk.ac.cranfield.workflow.prototype.model.Simulation;


public interface WorkflowQueue
{
    
    public boolean isEmpty();
    
    public Simulation pop();
    
    public void push(Simulation simulation);
}
