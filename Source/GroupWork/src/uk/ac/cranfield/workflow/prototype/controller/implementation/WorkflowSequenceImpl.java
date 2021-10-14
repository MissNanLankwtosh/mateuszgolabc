package uk.ac.cranfield.workflow.prototype.controller.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Observable;
import java.util.Observer;

import uk.ac.cranfield.workflow.prototype.controller.WorkflowSequenceState;
import uk.ac.cranfield.workflow.prototype.controller.interfaces.WorkflowSequence;
import uk.ac.cranfield.workflow.prototype.model.Simulation;
import uk.ac.cranfield.workflow.prototype.model.StablePoint;
import uk.ac.cranfield.workflow.prototype.model.interfaces.Module;


public class WorkflowSequenceImpl extends Observable implements WorkflowSequence
{
    
    private List<Module> modules;
    private ListIterator<Module> iterator;
    private Module currentModule;
    private boolean outputState;
    private boolean inputState;
    private WorkflowSequenceState state;
    private Integer iterationNumber;
    
    public WorkflowSequenceImpl(Observer observer)
    {
        addObserver(observer);
        modules = new ArrayList<Module>();
        iterator = modules.listIterator();
        iterationNumber = 0;
    }
    
    @Override
    public int getNumberOfModules()
    {
        return modules.size();
    }
    
    @Override
    public void addModule(Module module)
    {
        
        if (modules.isEmpty())
        {
            modules.add(module);
            currentModule = modules.get(0);
        }
        else
        {
            modules.add(module);
        }
    }
    
    @Override
    public void removeModule(Module module)
    {
        if (currentModule.equals(module))
        {
            if (!iterator.hasNext())
            {
                iterator = modules.listIterator(0);
                currentModule = modules.get(0);
            }
            else
            {
                currentModule = iterator.next();
            }
        }
        modules.remove(module);
    }
    
    @Override
    public void executeModule()
    {
        
        currentModule.execute();
    }
    
    @Override
    public void validateModuleInput()
    {
        inputState = currentModule.validateInput();
        notifyObservers();
    }
    
    @Override
    public void validateModuleOutput()
    {
        outputState = currentModule.validateOutput();
        notifyObservers();
    }
    
    
    /**
     * @return the outputState
     */
    public final boolean isOutputStateCorrect()
    {
        return outputState;
    }
    
    
    /**
     * @return the inputState
     */
    public final boolean isInputStateCorrect()
    {
        return inputState;
    }
    
    
    /**
     * @return the state
     */
    public final WorkflowSequenceState getState()
    {
        return state;
    }
    
    @Override
    public void setStartingParameters(Simulation simulation)
    {
        // TODO : implement
        
    }
    
    @Override
    public StablePoint createStablePoint()
    {
        int prev = -1;
        int next = -1;
        
        if (iterator.hasPrevious())
            prev = iterator.previous().getID();
        if (iterator.hasNext())
            next = iterator.next().getID();
        
        return new StablePoint(prev, next, "previousOutputFilePath", "nextInputFilePath", iterationNumber);
    }
    
    @Override
    public void nextModule()
    {
        if (!iterator.hasNext())
        {
            iterator = modules.listIterator(0);
            currentModule = modules.get(0);
        }
        else
        {
            currentModule = iterator.next();
        }
        
    }
}
