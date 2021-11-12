package uk.ac.cranfield.workflow.prototype.controller.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import uk.ac.cranfield.workflow.prototype.controller.WorkflowSequenceState;
import uk.ac.cranfield.workflow.prototype.controller.interfaces.WorkflowManager;
import uk.ac.cranfield.workflow.prototype.controller.interfaces.WorkflowSequence;
import uk.ac.cranfield.workflow.prototype.model.Simulation;
import uk.ac.cranfield.workflow.prototype.model.StablePoint;
import uk.ac.cranfield.workflow.prototype.model.interfaces.Module;
import uk.ac.cranfield.workflow.prototype.view.WorkflowSequenceView;


public class WorkflowSequenceImpl extends Observable implements WorkflowSequence
{
    
    private List<Module> modules;
    private Module currentModule;
    private boolean outputState;
    private boolean inputState;
    private WorkflowSequenceState state;
    private Integer iterationNumber;
    private WorkflowSequenceView view;
    private Integer iteration;
    
    public WorkflowSequenceImpl(WorkflowManager observer, WorkflowSequenceView view)
    {
        addObserver(observer);
        modules = new ArrayList<Module>();
        iterationNumber = 0;
        iteration = 1;
        this.view = view;
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
            if (modules.indexOf(module) == modules.size() - 1)
            {
                currentModule = modules.get(0);
            }
            else
            {
                // iterator.remove();
                currentModule = modules.get(modules.indexOf(currentModule) + 1);
                
            }
        }
        
        
        modules.remove(module);
        
        
    }
    
    @Override
    public void executeModule()
    {
        if (currentModule.execute() == false)
        {
            state = WorkflowSequenceState.ERROR;
            notifyObserver();
        }
        else
        {
            validateModuleOutput();
        }
    }
    
    @Override
    public void validateModuleInput()
    {
        inputState = currentModule.validateInput();
        state = WorkflowSequenceState.MODULE_INPUT_VALIDATION;
        
        notifyObserver();
    }
    
    
    @Override
    public void validateModuleOutput()
    {
        outputState = currentModule.validateOutput();
        state = WorkflowSequenceState.MODULE_OUTPUT_VALIDATION;
        notifyObserver();
    }
    
    
    /**
     * @return the outputState
     */
    @Override
    public final Boolean isOutputStateCorrect()
    {
        return outputState;
    }
    
    
    /**
     * @return the inputState
     */
    @Override
    public final Boolean isInputStateCorrect()
    {
        return inputState;
    }
    
    
    /**
     * @return the state
     */
    @Override
    public final WorkflowSequenceState getState()
    {
        return state;
    }
    
    @Override
    public void startSimulation(Simulation simulation)
    {
        view.printSimulationStarted();
        validateModuleInput();
        
    }
    
    @Override
    public StablePoint createStablePoint()
    {
        int prev = -1;
        int next = -1;
        
        int index = modules.indexOf(currentModule);
        
        if (index > 0)
            prev = modules.get(index - 1).getID();
        
        if (index < modules.size() - 1)
            next = modules.get(index + 1).getID();
        
        
        return new StablePoint(prev, next, "previousOutputFilePath", "nextInputFilePath", iterationNumber);
    }
    
    @Override
    public Module nextModule()
    {
        int index = modules.indexOf(currentModule);
        
        if (index == modules.size() - 1)
        {
            iteration++;
            if (iteration >= iterationNumber)
            {
                state = WorkflowSequenceState.SIMULATION_SUCCESS;
                notifyObserver();
                
                return null;
            }
            
            view.printSimulation(iteration);
            currentModule = modules.get(0);
        }
        else
        {
            
            currentModule = modules.get(index + 1);
        }
        
        
        validateModuleInput();
        
        return currentModule;
    }
    
    @Override
    public void recoverFromStablePoint(StablePoint stablePoint)
    {
        for (Module m : modules)
        {
            if (stablePoint.getId().equals(m.getID()))
            {
                m.execute();
                return;
            }
        }
    }
    
    @Override
    public Module getCurrentModule()
    {
        return currentModule;
    }
    
    private void notifyObserver()
    {
        setChanged();
        notifyObservers();
    }
}
