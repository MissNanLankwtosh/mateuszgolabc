package uk.ac.cranfield.workflow.prototype.controller.mock;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

import uk.ac.cranfield.workflow.prototype.controller.interfaces.DatabaseManager;
import uk.ac.cranfield.workflow.prototype.model.StablePoint;

/*
 * DatabaseManager class
 * It is using LinkedList for simulation.
 */
public class DatabaseManagerMock implements DatabaseManager
{
    
    private Connection c; // it is not necessary if we only simulating database
    private String pathToServer, userName, userPassword;
    private int numberOfLastBackups;
    private StablePoint currentBackup;
    private LinkedList<StablePoint> stablePoints = new LinkedList<StablePoint>();
    
    public DatabaseManagerMock()
    {
        
    }
    
    /*
     * Constructor
     * Usage:
     * path = "jdbc://localhost/..."
     * userName = "admin"
     * userPassword = "admin"
     * DatabaseManager(path, userName, userPassword);
     */
    public DatabaseManagerMock(String pathToS, String userN, String userP)
    {
        
        stablePoints = new LinkedList<StablePoint>();
        this.pathToServer = pathToS; // it is not necessary if we only simulating database
        this.userName = userN; // it is not necessary if we only simulating database
        this.userPassword = userP; // it is not necessary if we only simulating database
    }
    
    
    /*
     * =============================================================================
     * THE FOLLOWING PARTS ARE THE PART-IMPLEMENTATIONS OF REAL DATABASE ENVIRONMENT
     * Are they necessary? I dont think so cause it's just a mock, LinkedList-type implementation
     * is enough for simulating the database environment
     */
    @Override
    public void connect()
    {
        try
        {
            c = DriverManager.getConnection(pathToServer, userName, userPassword);
            
        }
        catch (SQLException err)
        {
            System.out.println(err.getMessage());
        }
    }
    
    public void disConnect()
    {
        try
        {
            c.close();
        }
        catch (SQLException err)
        {
            System.out.println(err.getMessage());
        }
    }
    
    public void insert(StablePoint sp)
    {
        stablePoints.addLast(sp);
        
        if (stablePoints.size() > numberOfLastBackups)
        {
            stablePoints.removeFirst();
        }
    }
    
    @Override
    public StablePoint getLastStablePoint()
    {
        return stablePoints.getLast(); // catch NoSuchElementException
    }
    
    @Override
    public Integer getNumberOfLastBackups()
    {
        
        return stablePoints.size();
    }
    
    @Override
    public void insertStablePoint(StablePoint stablePoint)
    {
        if (stablePoints.size() == numberOfLastBackups)
        {
            stablePoints.removeFirst();
        }
        
        stablePoints.addLast(stablePoint);
        currentBackup = stablePoint;
        
    }
    
    @Override
    public void removeStablePoint(StablePoint stablePoint)
    {
        if (currentBackup.equals(stablePoint))
        {
            stablePoints.remove(stablePoint);
            currentBackup = stablePoints.peekLast();
        }
        
        stablePoints.remove(stablePoint);
    }
    
    @Override
    public StablePoint getNextStablePoint()
    {
        // TODO : implement this method
        return currentBackup;
    }
    
    @Override
    public void disconnect()
    {
        // TODO Auto-generated method stub
        
    }
}
