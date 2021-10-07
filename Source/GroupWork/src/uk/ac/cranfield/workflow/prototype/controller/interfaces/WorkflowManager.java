package uk.ac.cranfield.workflow.prototype.controller.interfaces;

import java.util.Observer;

import uk.ac.cranfield.workflow.prototype.model.interfaces.Module;


public interface WorkflowManager extends Observer
{
    
    // public void backup(StablePoint stablePoint);
    
    // public StablePoint recover();
    
    public void startSimulation(WorkflowSequence sequence);
    
    public void stopSimulation();
    
    public void restart();
    
    public void addModule(Module module);
    
    public void removeModule(Integer id);
    
    public void sendResult();
}
