package uk.ac.cranfield.workflow.prototype.controller.mock;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.ListIterator;

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
    private LinkedList<StablePoint> stablePoints;
    private ListIterator<StablePoint> iterator;
    private StablePoint currentStablePoint;
    private StablePoint initialStablePoint;
    
    public DatabaseManagerMock()
    {
        stablePoints = new LinkedList<StablePoint>();
        iterator = stablePoints.listIterator();
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
        iterator = stablePoints.listIterator();
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
            currentStablePoint = iterator.next();
            stablePoints.removeFirst();
        }
        
        if (stablePoints.isEmpty())
        {
            stablePoints.addLast(stablePoint);
            currentStablePoint = stablePoints.get(0);
        }
        else
        {
            stablePoints.addLast(stablePoint);
        }
    }
    
    @Override
    public void removeStablePoint(StablePoint stablePoint)
    {
        if (currentStablePoint.equals(stablePoint))
        {
            if (!iterator.hasNext())
            {
                iterator = stablePoints.listIterator(0);
                currentStablePoint = stablePoints.get(0);
            }
            else
            {
                currentStablePoint = iterator.next();
            }
        }
        stablePoints.remove(stablePoint);
        /*
         * if (currentBackup.equals(stablePoint))
         * {
         * stablePoints.remove(stablePoint);
         * currentBackup = stablePoints.peekLast();
         * }
         * stablePoints.remove(stablePoint);
         */
    }
    
    @Override
    public StablePoint getPreviousStablePoint()
    {
        if (!iterator.hasPrevious())
        {
            currentStablePoint = null;
        }
        else
        {
            currentStablePoint = iterator.previous();
        }
        return currentStablePoint;
    }
    
    @Override
    public void disconnect()
    {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public StablePoint getInitialStablePoint()
    {
        return initialStablePoint;
    }
    
    @Override
    public void setInitialStablePoint(StablePoint sp)
    {
        initialStablePoint = sp;
        
    }
}
