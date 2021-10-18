package uk.ac.cranfield.workflow.prototype.model;


/*
 * StablePoint class
 * It is a simple class: constructor + getters
 */
public class StablePoint
{
    
    private Integer id;
    private int preModuleID;
    private int postModuleID;
    private String prevModuleOutputPath;
    private String nextModuleInputPath;
    private Integer iterationNumber;
    
    /*
     * Consturctor
     * Usage:
     * outputPath = "c:\\..\\myProgram\\resources\\outputFile0";
     * inputPath = "c:\\..\\myProgram\\resources\\inputFile1";
     * StablePoint(0, 1, outputPath, inputPath);
     */
    
    public StablePoint(int pre, int post, String pathToOutput, String pathToInput, int iterationNumber)
    {
        this.preModuleID = pre;
        this.postModuleID = post;
        this.prevModuleOutputPath = pathToOutput;
        this.nextModuleInputPath = pathToInput;
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
    
    
    public int getIterationNumber()
    {
        
        return iterationNumber;
    }
    
    /**
     * @return the id
     */
    public final Integer getId()
    {
        return id;
    }
    
    
    /**
     * @return the prevModuleOutputPath
     */
    public final String getPrevModuleOutputPath()
    {
        return prevModuleOutputPath;
    }
    
    
    /**
     * @return the nextModuleInputPath
     */
    public final String getNextModuleInputPath()
    {
        return nextModuleInputPath;
    }
    
    
}
