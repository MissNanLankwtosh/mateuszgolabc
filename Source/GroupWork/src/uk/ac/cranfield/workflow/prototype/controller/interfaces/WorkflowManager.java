package uk.ac.cranfield.workflow.prototype.controller.interfaces;

import java.util.Observer;

import uk.ac.cranfield.workflow.prototype.model.StablePoint;


public interface WorkflowManager extends Observer
{
    
    public void backup(StablePoint stablePoint);
    
    public StablePoint recover();
}
