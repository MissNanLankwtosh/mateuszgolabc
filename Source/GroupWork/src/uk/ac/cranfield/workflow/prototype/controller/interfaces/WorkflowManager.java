package uk.ac.cranfield.workflow.prototype.controller.interfaces;

import java.util.Observer;


public interface WorkflowManager extends Observer
{
    
    public void startSimulation(WorkflowSequence sequence);
    
    
    public boolean sendResult(boolean result);
}
