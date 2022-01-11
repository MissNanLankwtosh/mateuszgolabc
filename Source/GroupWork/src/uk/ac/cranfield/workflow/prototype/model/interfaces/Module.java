package uk.ac.cranfield.workflow.prototype.model.interfaces;

import uk.ac.cranfield.workflow.prototype.model.Simulation;


public interface Module
{
    
    public boolean validateInput() throws InterruptedException;
    
    public boolean validateOutput() throws InterruptedException;
    
    public boolean execute() throws InterruptedException;
    
    public Integer getID();
    
    public void setParameters(Simulation simulation);
}
