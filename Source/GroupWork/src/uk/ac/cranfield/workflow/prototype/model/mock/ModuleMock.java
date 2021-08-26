package uk.ac.cranfield.workflow.prototype.model.mock;

import uk.ac.cranfield.workflow.prototype.model.interfaces.Module;


public class ModuleMock implements Module
{
    
    boolean inputResult;
    boolean outputResult;
    
    
    public ModuleMock(boolean input, boolean output)
    {
        inputResult = input;
        outputResult = output;
    }
    
    @Override
    public boolean validateInput()
    {
        return inputResult;
    }
    
    @Override
    public boolean validateOutput()
    {
        return outputResult;
    }
    
    @Override
    public boolean execute()
    {
        return true;
    }
    
}
