package uk.ac.cranfield.workflow.prototype.model;

/*
 * StablePoint class
 * It is a simple class: constructor + getters
 */
public class StablePoint
{
    
    private int id;
    private int preModuleID;
    private int postModuleID;
    private String pathToOutputFile;
    private String pathToInputFile;
    private int iterationNumber;
    
    /*
     * Consturctor
     * Usage:
     * outputPath = "c:\\..\\myProgram\\resources\\outputFile0";
     * inputPath = "c:\\..\\myProgram\\resources\\inputFile1";
     * StablePoint(0, 1, outputPath, inputPath);
     */
    public StablePoint(int id, int pre, int post, String pathToOutput, String pathToInput, 
    																		int iterationNumber)
    {
        this.id = id;
        this.preModuleID = pre;
        this.postModuleID = post;
        this.pathToOutputFile = pathToOutput;
        this.pathToInputFile = pathToInput;
        this.iterationNumber = iterationNumber;
    }
    
    public int getPreModuleID()
    {
        
        return preModuleID;
    }
    
    public int getPostModuleID()
    {
        
        return postModuleID;
    }
    
    public String getPathToOutputFile()
    {
        
        return pathToOutputFile;
    }
    
    public String getPathToInputFile()
    {
        
        return pathToInputFile;
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
