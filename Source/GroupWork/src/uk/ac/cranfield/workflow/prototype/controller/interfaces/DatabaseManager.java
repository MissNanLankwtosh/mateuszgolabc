package uk.ac.cranfield.workflow.prototype.controller.interfaces;

import uk.ac.cranfield.workflow.prototype.model.StablePoint;


public interface DatabaseManager
{
    
    public void connect();
    
    public void disconnect();
    
    public void insertStablePoint(StablePoint stablePoint);
    
    public void removeStablePoint(StablePoint stablePoint);
    
    public StablePoint getLastStablePoint();
    
    public Integer getNumberOfLastBackups();
    
    public StablePoint getNextStablePoint();
}
