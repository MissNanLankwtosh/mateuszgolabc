package uk.ac.cranfield.workflow.prototype.controller.mock;

import java.util.Observable;

import uk.ac.cranfield.workflow.prototype.controller.interfaces.DatabaseManager;
import uk.ac.cranfield.workflow.prototype.controller.interfaces.WorkflowManager;
import uk.ac.cranfield.workflow.prototype.controller.interfaces.WorkflowQueue;
import uk.ac.cranfield.workflow.prototype.controller.interfaces.WorkflowSequence;
import uk.ac.cranfield.workflow.prototype.model.interfaces.Module;
import uk.ac.cranfield.workflow.prototype.view.WorkflowManagerView;


public class WorkflowManagerMock implements WorkflowManager
{
    
    private WorkflowQueue queue;
    private DatabaseManager database;
    private WorkflowManagerView view;
    
    public WorkflowManagerMock(WorkflowQueue queue, DatabaseManager database)
    {
        this.view = new WorkflowManagerView();
        this.queue = queue;
        this.database = database;
    }
    
    
    @Override
    public void update(Observable o, Object arg)
    {
        if (o.equals(arg))
        {
            if (o instanceof WorkflowSequence)
            {
                WorkflowSequenceMock sequence = (WorkflowSequenceMock) o;
                switch (sequence.getState()) {
                    case MODULE_INPUT_VALIDATION:
                        break;
                    case MODULE_OUTPUT_VALIDATION:
                        break;
                    case ERROR:
                        break;
                    case START_NEW_SIMULATION:
                        if (!queue.isEmpty())
                        {
                            database.insertStablePoint(sequence.createStablePoint());
                            sequence.setStartingParameters(queue.pop());
                        }
                        else
                        {
                            // view.emptyQuwe
                        }
                        break;
                    case SIMULATION_RECOVERY_RESTART:
                        break;
                
                }
            }
            else if (o instanceof DatabaseManager)
            {
                
            }
        }
    }
    
    @Override
    public void startSimulation()
    {
        
        
    }
    
    @Override
    public void stopSimulation()
    {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void restart()
    {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void addModule(Module module)
    {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void removeModule(Integer id)
    {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void sendResult()
    {
        // TODO Auto-generated method stub
        
    }
}
