package uk.ac.cranfield.workflow.prototype.view;

import uk.ac.cranfield.workflow.prototype.model.StablePoint;

// TODO : add method to print info about empty Workflow Queue

public class WorkflowManagerView
{
    
    void printModuleRunning(String moduleName)
    {
        
        System.out.println("" + moduleName + " module running.");
    }
    
    void printModuleStarted(String moduleName)
    {
        
        System.out.println("" + moduleName + " module started.");
    }
    
    void printModuleFinished(String moduleName)
    {
        
        System.out.println("" + moduleName + " module finished.");
    }
    
    void printBackupPerfomed(StablePoint sp)
    {
        
        System.out.println("Backup performed.");
    }
    
    void printError(String errorName)
    {
        
        System.out.println("Error occured in WorkflowManager! Type: " + errorName + ".");
    }
    
    void printLastModuleExecutionTime(long time)
    {
        
        System.out.println("Last module execution time: " + time + ".");
    }
    
    void printInputValidated()
    {
        
        System.out.println("Module's input(s) validated successfully.");
    }
    
    void printOutputValidated()
    {
        
        System.out.println("Module's output(s) validated successfully.");
    }
    
    void printSimulationRecovered()
    {
        
        System.out.println("Simulation recovered.");
    }
}
