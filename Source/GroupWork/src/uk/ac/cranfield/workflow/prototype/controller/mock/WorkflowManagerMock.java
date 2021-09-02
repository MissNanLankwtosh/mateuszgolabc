package uk.ac.cranfield.workflow.prototype.controller.mock;

import java.util.Observable;
import java.util.Observer;

import uk.ac.cranfield.workflow.prototype.controller.interfaces.WorkflowManager;
import uk.ac.cranfield.workflow.prototype.model.StablePoint;


public class WorkflowManagerMock implements Observer, WorkflowManager
{
    
    // private ObservableValue v;
    
    @Override
    public void backup(StablePoint stablePoint)
    {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public StablePoint recover()
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public void update(Observable o, Object arg)
    {
        if (o.equals(arg))
        {
            
        }
    }
}
