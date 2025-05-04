package tasktracker;

import tasktracker.app.TaskTrackerApp;

/**
 *
 * @author pityafinwe
 */
public class Main {
    
    public static void main(String args[]) {
        System.out.println("Inizializing...");
        
        TaskTrackerApp taskTracker = new TaskTrackerApp();
        taskTracker.start();
    }
    
}
