package uk.ac.cranfield.workflow.prototype.controller.mock;

import uk.ac.cranfield.workflow.prototype.controller.interfaces.ModuleDataValidator;


public class ModuleDataValidatorMock implements ModuleDataValidator
{
    
    @Override
    public boolean validate(String xmlPath)
    {
        if ("correct".compareToIgnoreCase(xmlPath) == 0)
            return true;
        
        return false;
    }
    
}
