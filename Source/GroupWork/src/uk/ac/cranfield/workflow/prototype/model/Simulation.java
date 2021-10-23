package uk.ac.cranfield.workflow.prototype.model;

import java.util.List;


public class Simulation
{
    
    private Integer ID;
    private Integer scientistID;
    private List<String> parameterFiles;
    private String xmlName;
    private Integer numberOfIterations;
    
    public Simulation(Integer scientistID, List<String> parameterFiles, String xmlName, Integer numberOfIterations)
    {
        this.scientistID = scientistID;
        this.parameterFiles = parameterFiles;
        this.xmlName = xmlName;
        this.numberOfIterations = numberOfIterations;
    }
    
    public void addParameter(String parameterFile)
    {
        parameterFiles.add(parameterFile);
    }
    
    public Object getScientistID()
    {
        
        return scientistID;
    }
    
    
    /**
     * @return the iD
     */
    public final Integer getID()
    {
        return ID;
    }
    
    
    /**
     * @return the parameterFiles
     */
    public final List<String> getParameterFiles()
    {
        return parameterFiles;
    }
    
    
    /**
     * @return the xmlName
     */
    public final String getXmlName()
    {
        return xmlName;
    }
    
    public Integer getNumberOfIterations()
    {
        return numberOfIterations;
    }
}
