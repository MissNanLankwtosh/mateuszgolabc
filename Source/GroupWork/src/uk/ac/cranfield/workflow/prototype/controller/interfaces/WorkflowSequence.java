package uk.ac.cranfield.workflow.prototype.controller.interfaces;

import uk.ac.cranfield.workflow.prototype.model.Simulation;
import uk.ac.cranfield.workflow.prototype.model.StablePoint;
import uk.ac.cranfield.workflow.prototype.model.interfaces.Module;


public interface WorkflowSequence
{
    
    public int getNumberOfModules();
    
    public void addModule(Module module);
    
    public void removeModule(Module module);
    
    public void executeModule();
    
    public void validateModuleInput();
    
    public void validateModuleOutput();
    
    public void setStartingParameters(Simulation simulation);
    
    public StablePoint createStablePoint();
}
