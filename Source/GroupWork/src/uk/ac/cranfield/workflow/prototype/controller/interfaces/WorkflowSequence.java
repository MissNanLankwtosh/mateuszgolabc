package uk.ac.cranfield.workflow.prototype.controller.interfaces;

import uk.ac.cranfield.workflow.prototype.controller.WorkflowSequenceState;
import uk.ac.cranfield.workflow.prototype.model.Simulation;
import uk.ac.cranfield.workflow.prototype.model.StablePoint;
import uk.ac.cranfield.workflow.prototype.model.interfaces.Module;


public interface WorkflowSequence
{
    
    public int getNumberOfModules();
    
    public void addModule(Module module);
    
    public void removeModule(Module module);
    
    public void executeModule();
    
    public Module nextModule();
    
    public void startSimulation(Simulation simulation);
    
    public StablePoint createStablePoint();
    
    public Boolean isOutputStateCorrect();
    
    public Boolean isInputStateCorrect();
    
    public WorkflowSequenceState getState();
    
    public void recoverFromStablePoint(StablePoint stablePoint);
    
    public Module getCurrentModule();
    
    public void validateModuleOutput();
    
    public void validateModuleInput();
    
}
