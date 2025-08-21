package tasktracker.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
    private FileWriter writer;
    
    public FileHandler() {
        this.fileName = "./src/main/java/tasktracker/data/task-data.csv";
        try {
            this.writer = new FileWriter(fileName, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    } 
    
    public Map<Integer, Task> readFromCsv() {
        HashMap<Integer, Task> tasks = new HashMap<>();
        File file = new File(this.fileName);

        // check if file is empty
        if(!file.exists() || file.length() == 0) {
            System.out.println("File is empty.");
            return tasks;	
        }

        try {
            this.scanner = new Scanner(Paths.get(this.fileName));
            
            if(this.scanner.hasNextLine()) {
                this.scanner.nextLine(); //read header

                while(this.scanner.hasNextLine()) {
                String[] task = this.scanner.nextLine().split(",");
                tasks.put(Integer.valueOf(task[0]), new Task(Integer.parseInt(task[0]), task[1], TaskStatus.valueOf(task[2]), task[3], task[4]));
                }

            }
        } catch(IOException e) {
            System.out.println("Unable to open file. " + e.getMessage());
        }
        return tasks;
    }
    
    public void addToCsv(Task task) {
        try {
            this.writer.append("\n");
            this.writer.append(
                task.getID() + "," +
                task.getTitle() + "," +
                task.getStatus() + "," +
                task.getCreationTime() + "," +
                task.getUpdateTime()
            );
            this.writer.flush();
        } catch (IOException e) {
            System.out.println("File not found...");
        }
    }

    public void addToCsv(Map<Integer, Task> tasks) {
        try {
            this.writer = new FileWriter(this.fileName, false);
            this.writer.write("id,title,status,createdAt,updatedAt");
            this.writer.append("\n");
            for(int id : tasks.keySet()) {
                this.writer.append(
                    tasks.get(id).getID() + "," +
                    tasks.get(id).getTitle() + "," +
                    tasks.get(id).getStatus() + "," +
                    tasks.get(id).getCreationTime() + "," +
                    tasks.get(id).getUpdateTime()
                );
                this.writer.append("\n");
            }
            this.writer.flush();
        } catch (IOException e) {
            System.out.println("File not found...");
        }
    }
    
    public void saveToCsv(Map<Integer, Task> tasks) {
        
    }
    
    public void addToCsv() {
        
    }

    public void removeFromCsv(Task task) {
        Map<Integer, Task> tasks = this.readFromCsv();
        HashMap<Integer, Task> updatedTasks = new HashMap<>();

        for(int id : tasks.keySet()) {
            if(!tasks.get(id).getTitle().equals(task.getTitle())) {
                updatedTasks.put(id, tasks.get(id));
            }
        }
        this.addToCsv(updatedTasks);
    }
    
}
