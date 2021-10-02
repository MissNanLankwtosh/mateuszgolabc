package uk.ac.cranfield.workflow.prototype.model;

import uk.ac.cranfield.workflow.prototype.model.interfaces.Module;

/*
 * StablePoint class
 * It is a simple class: constructor + getters
 */
public class StablePoint
{
    
    private int id;
    private int preModuleID;
    private int postModuleID;
    private Module prevModule;
    private Module nextModule;
    private int iterationNumber;
    
    /*
     * Consturctor
     * Usage:
     * outputPath = "c:\\..\\myProgram\\resources\\outputFile0";
     * inputPath = "c:\\..\\myProgram\\resources\\inputFile1";
     * StablePoint(0, 1, outputPath, inputPath);
     */
    public StablePoint(int pre, int post, int iterationNumber)
    {
        
        this.preModuleID = pre;
        this.postModuleID = post;
        // this.prevModule = prev;
        // this.nextModule = next;
        this.iterationNumber = iterationNumber;
    }
    
    public void setId(int id)
    {
        this.id = id;
    }
    
    public int getPreModuleID()
    {
        
        return preModuleID;
    }
    
    public int getPostModuleID()
    {
        
        return postModuleID;
    }
    
    public Module getPathToOutputFile()
    {
        
        return prevModule;
    }
    
    public Module getPathToInputFile()
    {
        
        return nextModule;
    }
    
    public int getIterationNumber()
    {
        
        return iterationNumber;
    }
    
    
    /**
     * @return the id
     */
    public final int getId()
    {
        return id;
    }
    
    
}
