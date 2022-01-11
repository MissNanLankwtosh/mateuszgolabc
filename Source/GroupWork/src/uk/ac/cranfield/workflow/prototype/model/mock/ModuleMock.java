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
    private Boolean autofix;
    private String name;
    
    
    public ModuleMock(boolean input, boolean output, int id, String name, boolean autofix)
    {
        this.input = (input) ? "correct" : "incorrect";
        this.output = (output) ? "correct" : "incorrect";
        this.autofix = autofix;
        
        inputValidator = new ModuleDataValidatorMock();
        outputValidator = new ModuleDataValidatorMock();
        
        this.id = id;
        this.name = name;
    }
    
    public ModuleMock(boolean input, boolean output, int id, String name)
    {
        this.input = (input) ? "correct" : "incorrect";
        this.output = (output) ? "correct" : "incorrect";
        this.autofix = false;
        
        inputValidator = new ModuleDataValidatorMock();
        outputValidator = new ModuleDataValidatorMock();
        
        this.id = id;
        this.name = name;
    }
    
    @Override
    public boolean validateInput() throws InterruptedException
    {
        try
        {
            return inputValidator.validate(input);
        }
        finally
        {
            if (autofix)
                input = "correct";
        }
    }
    
    @Override
    public boolean validateOutput() throws InterruptedException
    {
        try
        {
            return outputValidator.validate(output);
        }
        finally
        {
            if (autofix)
                output = "correct";
        }
    }
    
    @Override
    public boolean execute() throws InterruptedException
    {
        Thread.sleep(2000);
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
        return name;
    }
    
    @Override
    public void setParameters(Simulation simulation)
    {
        this.simulation = simulation;
        
    }
    
    
}
