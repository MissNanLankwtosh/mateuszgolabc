package uk.ac.cranfield.workflow.prototype.model.mock;

import uk.ac.cranfield.workflow.prototype.model.interfaces.ModuleParameter;


public class ModuleParameterMock implements ModuleParameter
{
	private long estimatedExecutionTime;
	private String executionCommand;
	private Integer requiredNumberOfProcessors;
    
	public ModuleParameterMock(long estimatedTime, String executionC, Integer requiredN) {
		this.estimatedExecutionTime = estimatedTime;
		this.executionCommand = executionC;
		this.requiredNumberOfProcessors = requiredN;
	}
	
    @Override
    public Long getEstimatedExecutionTime()
    {
        return estimatedExecutionTime;
    }
    
    @Override
    public String getExecutionCommand()
    {
        return executionCommand;
    }
    
    @Override
    public Integer getRequiredNumberOfProcessors()
    {
        return requiredNumberOfProcessors;
    }
    
}
