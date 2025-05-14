package tasktracker.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import tasktracker.model.Task;
import tasktracker.model.TaskStatus;

/**
 * save/load tasks to file
 * @author pityafinwe
 */
public class FileHandler {
    
    private final String fileName;
    private Scanner scanner;
    private PrintWriter writer;
    
    public FileHandler() {
        this.fileName = "./src/main/java/tasktracker/data/task-data.csv";
        try {
            this.scanner = new Scanner(Paths.get(this.fileName));
            this.writer = new PrintWriter(this.fileName);
        } catch(IOException e) {
            System.out.println("Unable to open file. " + e.getMessage());
        }
    } 
    
    public Map<Integer, Task> readFromCsv() {
        HashMap<Integer, Task> tasks = new HashMap<>();
        // read header
        this.scanner.nextLine();
        while(this.scanner.hasNextLine()) {
            String[] task = this.scanner.nextLine().split(",");
            tasks.put(Integer.valueOf(task[0]), new Task(Integer.parseInt(task[0]), task[1], TaskStatus.valueOf(task[2]), task[3], task[4]));
        }
        return tasks;
    }
    
    public void addToCsv(Task task) {
        
    }
    
    public void saveToCsv(Map<Integer, Task> tasks) {
        
    }
    
    public void addToCsv() {
        
    }
    
}
