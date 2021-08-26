package uk.ac.cranfield.workflow.prototype.controller.interfaces;

import uk.ac.cranfield.workflow.prototype.model.StablePoint;


public interface WorkflowManager
{
    
    public void backup(StablePoint stablePoint);
    
    public StablePoint recover();
}
