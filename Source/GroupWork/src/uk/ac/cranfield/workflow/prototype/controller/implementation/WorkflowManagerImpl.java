package uk.ac.cranfield.workflow.prototype.controller.implementation;

import java.util.Observable;

import uk.ac.cranfield.workflow.prototype.controller.interfaces.DatabaseManager;
import uk.ac.cranfield.workflow.prototype.controller.interfaces.WorkflowManager;
import uk.ac.cranfield.workflow.prototype.controller.interfaces.WorkflowQueue;
import uk.ac.cranfield.workflow.prototype.controller.interfaces.WorkflowSequence;
import uk.ac.cranfield.workflow.prototype.model.StablePoint;
import uk.ac.cranfield.workflow.prototype.view.WorkflowManagerView;


public class WorkflowManagerImpl implements WorkflowManager
{
    
    private static int DEFAULT_NUMBER_OF_LAST_BACKUPS = 3;
    private WorkflowQueue queue;
    private DatabaseManager database;
    private WorkflowManagerView view;
    private final Integer numberOfLastBackups;
    private Integer lastBackupPerformed;
    private Integer backupsPerformed;
    private Boolean result;
    
    public WorkflowManagerImpl(WorkflowQueue queue, DatabaseManager database, WorkflowManagerView view)
    {
        this.view = view;
        this.queue = queue;
        this.database = database;
        this.numberOfLastBackups = DEFAULT_NUMBER_OF_LAST_BACKUPS;
        this.lastBackupPerformed = 0;
        this.backupsPerformed = 0;
        this.result = false;
    }
    
    
    @Override
    public void update(Observable o, Object arg)
    {
        if (o instanceof WorkflowSequence)
        {
            WorkflowSequence sequence = (WorkflowSequenceImpl) o;
            switch (sequence.getState()) {
            
                case MODULE_INPUT_VALIDATION:
                    if (sequence.isInputStateCorrect())
                    {
                        view.printInputValidated();
                        sequence.executeModule();
                    }
                    else
                    {
                        view.printError("Module input is incorrect");
                        recover(sequence);
                    }
                    
                    break;
                
                case MODULE_OUTPUT_VALIDATION:
                    if (sequence.isOutputStateCorrect())
                    {
                        view.printOutputValidated();
                        view.printModuleFinished(sequence.getCurrentModule().toString());
                        sequence.nextModule();
                    }
                    else
                    {
                        view.printError("Module output is incorrect");
                        recover(sequence);
                    }
                    
                    break;
                
                case ERROR:
                    view.printError("Module error");
                    recover(sequence);
                    break;
                
                case START_NEW_SIMULATION:
                    startSimulation(sequence);
                    break;
                
                case SIMULATION_RECOVERY_RESTART:
                    restart(sequence);
                    break;
                case SIMULATION_SUCCESS:
                    result = true;
                    view.printSimulationSuccessfullyFinished();
                    sendResult();
                    break;
                case SIMULATION_FAILURE:
                    result = false;
                    view.printSimulationUnsuccessfullyFinished();
                    sendResult();
                    break;
            }
        }
    }
    
    @Override
    public void startSimulation(WorkflowSequence sequence)
    {
        if (!queue.isEmpty())
        {
            backupsPerformed = 0;
            lastBackupPerformed = 0;
            database.setInitialStablePoint(sequence.createStablePoint());
            sequence.startSimulation(queue.pop());
        }
        else
        {
            view.printoutWorkflowQueueEmpty();
        }
        
    }
    
    
    private void restart(WorkflowSequence sequence)
    {
        // get stable point
        StablePoint stablePoint = database.getInitialStablePoint();
        
        // restart using initial stable point
        if (stablePoint != null)
        {
            sequence.recoverFromStablePoint(stablePoint);
        }
        else
        {
            view.printError("Simulation could not be restarted");
            // current simulation failed, starting new simulation
            startSimulation(sequence);
        }
    }
    
    
    @Override
    public boolean sendResult()
    {
        return result;
    }
    
    private void recover(WorkflowSequence sequence)
    {
        // get stable point
        StablePoint stablePoint = null;
        if (lastBackupPerformed < 2)
        {
            stablePoint = database.getLastStablePoint();
            lastBackupPerformed++;
        }
        else
        {
            stablePoint = database.getPreviousStablePoint();
            backupsPerformed++;
        }
        
        // recover using stable point
        if (stablePoint != null)
        {
            sequence.recoverFromStablePoint(stablePoint);
            
        }
        else
        {
            view.printError("Recovery finished , simulation could not finish successfully");
            
            // current simulation failed, starting new simulation
            startSimulation(sequence);
        }
    }
}
