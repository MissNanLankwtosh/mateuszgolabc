package uk.ac.cranfield.workflow.prototype.model.interfaces;


public interface Module
{
    
    public boolean validateInput();
    
    public boolean validateOutput();
    
    public boolean execute();
    
    public Integer getID();
}
