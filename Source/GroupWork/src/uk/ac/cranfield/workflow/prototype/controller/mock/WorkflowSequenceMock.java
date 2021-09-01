package uk.ac.cranfield.workflow.prototype.controller.mock;

import java.util.List;
import java.util.ListIterator;
import java.util.Observable;

import uk.ac.cranfield.workflow.prototype.controller.interfaces.WorkflowSequence;
import uk.ac.cranfield.workflow.prototype.model.interfaces.Module;


public class WorkflowSequenceMock extends Observable implements WorkflowSequence
{
    
    List<Module> modules;
    ListIterator<Module> iterator;
    
    @Override
    public int getNumberOfModules()
    {
        return modules.size();
    }
    
    @Override
    public void addModule(Module module)
    {
        modules.add(module);
        
    }
    
    @Override
    public void removeModule(Module module)
    {
        modules.remove(module);
        
    }
    
    @Override
    public void executeModule()
    {
        // TODO Auto-generated method stub
        
    }
    
    
}
