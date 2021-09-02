package uk.ac.cranfield.workflow.prototype.model.mock;

import uk.ac.cranfield.workflow.prototype.controller.interfaces.ModuleDataValidator;
import uk.ac.cranfield.workflow.prototype.controller.mock.ModuleDataValidatorMock;
import uk.ac.cranfield.workflow.prototype.model.interfaces.Module;


public class ModuleMock implements Module
{
    
    private ModuleDataValidator inputValidator;
    private ModuleDataValidator outputValidator;
    
    
    public ModuleMock()
    {
        inputValidator = new ModuleDataValidatorMock();
        outputValidator = new ModuleDataValidatorMock();
    }
    
    @Override
    public boolean validateInput()
    {
        return inputValidator.validate();
    }
    
    @Override
    public boolean validateOutput()
    {
        return outputValidator.validate();
    }
    
    @Override
    public boolean execute()
    {
        return true;
    }
    
}
