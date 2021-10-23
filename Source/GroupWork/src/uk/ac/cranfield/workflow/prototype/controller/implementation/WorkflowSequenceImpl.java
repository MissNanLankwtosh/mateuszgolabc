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
import uk.ac.cranfield.workflow.prototype.view.WorkflowSequenceView;


public class WorkflowSequenceImpl extends Observable implements WorkflowSequence
{
    
    private List<Module> modules;
    private ListIterator<Module> iterator;
    private Module currentModule;
    private boolean outputState;
    private boolean inputState;
    private WorkflowSequenceState state;
    private Integer iterationNumber;
    private WorkflowSequenceView view;
    
    public WorkflowSequenceImpl(Observer observer, WorkflowSequenceView view)
    {
        addObserver(observer);
        modules = new ArrayList<Module>();
        iterator = modules.listIterator();
        iterationNumber = 0;
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
        
        for (int i = 0; i < simulation.getNumberOfIterations(); i++)
        {
            // view.printIteration }
            // for()
        }
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
}
