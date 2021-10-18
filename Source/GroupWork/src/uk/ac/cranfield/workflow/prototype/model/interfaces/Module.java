package uk.ac.cranfield.workflow.prototype.model.interfaces;

import uk.ac.cranfield.workflow.prototype.model.Simulation;


public interface Module
{
    
    public boolean validateInput();
    
    public boolean validateOutput();
    
    public boolean execute();
    
    public Integer getID();
    
    public void setParameters(Simulation simulation);
}
