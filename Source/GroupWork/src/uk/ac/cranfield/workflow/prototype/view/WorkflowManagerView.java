package uk.ac.cranfield.workflow.prototype.view;

import uk.ac.cranfield.workflow.prototype.model.StablePoint;

// TODO : add method to print info about empty Workflow Queue

public class WorkflowManagerView
{
    
    public void printModuleRunning(String moduleName)
    {
        
        System.out.println("" + moduleName + " module running.");
    }
    
    public void printModuleStarted(String moduleName)
    {
        
        System.out.println("" + moduleName + " module started.");
    }
    
    public void printModuleFinished(String moduleName)
    {
        
        System.out.println("" + moduleName + " module finished.");
    }
    
    public void printBackupPerfomed(StablePoint sp)
    {
        
        System.out.println("Backup performed.");
    }
    
    public void printError(String errorName)
    {
        
        System.err.println("Error occured in WorkflowManager! Type: " + errorName + ".");
    }
    
    public void printLastModuleExecutionTime(long time)
    {
        
        System.out.println("Last module execution time: " + time + ".");
    }
    
    public void printInputValidated()
    {
        
        System.out.println("Module's input(s) validated successfully.");
    }
    
    public void printOutputValidated()
    {
        
        System.out.println("Module's output(s) validated successfully.");
    }
    
    public void printSimulationRecovered()
    {
        
        System.out.println("Simulation recovered.");
    }
    
    public void printoutWorkflowQueueEmpty()
    {
        
        System.err.println("Simulation proess failure! Reason: WorkflowQueue is EMPTY.");
    }
    
    public void printSimulationSuccessfullyFinished()
    {
        System.out.println("Simulation process successfully finished");
        
    }
    
    public void printSimulationUnsuccessfullyFinished()
    {
        System.err.println("Simulation process failed");
        
    }
}
