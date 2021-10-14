package uk.ac.cranfield.workflow.prototype.model.interfaces;


public interface ModuleParameter
{
    
    public Long getEstimatedExecutionTime();
    
    public String getExecutionCommand();
    
    public Integer getRequiredNumberOfProcessors();
}
