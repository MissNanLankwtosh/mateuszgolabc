package uk.ac.cranfield.workflow.prototype.controller.mock;

import java.util.Observable;

import uk.ac.cranfield.workflow.prototype.controller.interfaces.DatabaseManager;
import uk.ac.cranfield.workflow.prototype.controller.interfaces.WorkflowManager;
import uk.ac.cranfield.workflow.prototype.controller.interfaces.WorkflowSequence;
import uk.ac.cranfield.workflow.prototype.model.StablePoint;
import uk.ac.cranfield.workflow.prototype.view.WorkflowManagerView;


public class WorkflowManagerMock implements WorkflowManager
{
    
    private WorkflowManagerView view;
    
    public WorkflowManagerMock()
    {
        view = new WorkflowManagerView();
    }
    
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
            if (o instanceof WorkflowSequence)
            {
                // view.
            }
            else if (o instanceof DatabaseManager)
            {
                
            }
        }
    }
}
