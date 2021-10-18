package uk.ac.cranfield.workflow.prototype.model.mock;

import uk.ac.cranfield.workflow.prototype.controller.interfaces.ModuleDataValidator;
import uk.ac.cranfield.workflow.prototype.controller.mock.ModuleDataValidatorMock;
import uk.ac.cranfield.workflow.prototype.model.Simulation;
import uk.ac.cranfield.workflow.prototype.model.interfaces.Module;


public class ModuleMock implements Module
{
    
    private ModuleParameterMock parameter;
    private ModuleDataValidator inputValidator;
    private ModuleDataValidator outputValidator;
    private Integer id;
    private String output;
    private String input;
    private Simulation simulation;
    
    
    public ModuleMock(boolean input, boolean output)
    {
        this.input = (input) ? "correct" : "incorrect";
        this.output = (output) ? "correct" : "incorrect";
        
        inputValidator = new ModuleDataValidatorMock();
        outputValidator = new ModuleDataValidatorMock();
    }
    
    @Override
    public boolean validateInput()
    {
        return inputValidator.validate(input);
    }
    
    @Override
    public boolean validateOutput()
    {
        return outputValidator.validate(output);
    }
    
    @Override
    public boolean execute()
    {
        return true;
    }
    
    @Override
    public Integer getID()
    {
        return id;
    }
    
    @Override
    public String toString()
    {
        return id.toString();
    }
    
    @Override
    public void setParameters(Simulation simulation)
    {
        this.simulation = simulation;
        
    }
    
    
}
