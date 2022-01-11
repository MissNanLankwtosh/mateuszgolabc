package uk.ac.cranfield.workflow.prototype.model;


/*
 * StablePoint class
 * It is a simple class: constructor + getters
 */
public class StablePoint
{
    
    private Integer id;
    private Integer moduleID;
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
    
    public StablePoint(Integer moduleId, String pathToOutput, String pathToInput, int iterationNumber)
    {
        this.moduleID = moduleId;
        this.prevModuleOutputPath = pathToOutput;
        this.nextModuleInputPath = pathToInput;
    }
    
    public void setId(int id)
    {
        this.id = id;
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
    
    
    /**
     * @return the moduleID
     */
    public final Integer getModuleID()
    {
        return moduleID;
    }
    
    
}
