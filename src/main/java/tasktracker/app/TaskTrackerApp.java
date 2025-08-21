package tasktracker.app;

import java.util.Map;
import java.util.Scanner;

import tasktracker.model.Task;
import tasktracker.model.TaskStatus;
import tasktracker.service.TaskService;
import tasktracker.util.FileHandler;
import tasktracker.util.UIHelper;

/**
 * main loop, CLI logic
 * @author pityafinwe
 */
public class TaskTrackerApp {
    
    private final UIHelper UI;
    private final FileHandler fileHandler;
    private final Scanner scanner;
    private final TaskService taskService;
    
    public TaskTrackerApp() {
        this.scanner = new Scanner(System.in);
        this.UI = new UIHelper(this.scanner);
        this.fileHandler = new FileHandler();
        this.taskService = new TaskService();
    }
    
    public void start() {
        System.out.println("DEBUG: Task Tracker Starting...");
        
        this.UI.printIntro();
        this.taskService.initTasks(this.fileHandler.readFromCsv());
        
        while(true) {
            String[] input = this.UI.askCommand().split(" ");
            String command = input[0];
            
            switch(command) {
                case "add" -> {
                    String title = this.UI.askTaskToAdd();
                    Task added = this.taskService.addTask(title);
                    this.fileHandler.addToCsv(added);
                    System.out.printf("Task %s added.\n", title);
                }
                case "list" -> {
                    this.UI.printDB(this.taskService.getTasks());
                }
                case "view" -> {
                    if(input.length < 2) {
                        System.out.println("Usage: view <id>");
                        break;
                    }
                    int viewID = Integer.parseInt(input[1]);
                    Task task = this.taskService.getTasks().get(viewID);
                    if(task != null) {
                        System.out.printf("%-15s%s\n", "Task ID:", task.getID());
                        System.out.printf("%-15s%s\n", "Title:", task.getTitle());
                        System.out.printf("%-15s%s\n", "Status:", task.getStatus());
                        System.out.printf("%-15s%s\n", "Created at:", task.getCreationTime());
                        System.out.printf("%-15s%s\n", "Updated at:", task.getUpdateTime());
                    } else {
                        System.out.println("Task not found.");
                    }
                }
                case "delete" -> {
                    if(input.length < 2) {
                        System.out.println("Usage: delete <id>");
                        break;
                    }
                    int deleteID = Integer.parseInt(input[1]);
                    Task task = this.taskService.getTasks().get(deleteID);

                    if(task != null) {
                        this.fileHandler.removeFromCsv(task);
                        this.taskService.initTasks(this.fileHandler.readFromCsv());
                    } else {
                        System.out.println("Task do not exists");
                    }
                }
                case "status" -> {
                    // status of task given the id
                    if(input.length < 2) {
                        System.out.println("Usage: status <id>");
                        break;
                    }
                    try {
                        int statusID = Integer.parseInt(input[1]);
                        Task task = this.taskService.getTasks().get(statusID);
                        if (task != null) {
                            this.UI.printFormatted(task);
                        } else {
                            System.out.println("Task with ID " + statusID + " not found");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid ID format: " + input[1]);
                    }
                }
                case "update" -> {
                    // update status given the id
                    if(input.length < 2) {
                        System.out.println("Usage: update <id>");
                        break;
                    }
                    try {
                        int statusID = Integer.parseInt(input[1]);
                        TaskStatus status = this.taskService.getTasks().get(statusID).getStatus();
                        this.taskService.getTasks().get(statusID).setStatus(status.next());
                        this.fileHandler.addToCsv(this.taskService.getTasks());

                    } catch (NumberFormatException e) {
                        System.out.println("Invalid ID format: " + input[1]);
                    }
                }
                case "edit"-> {
                    // edit task giveen the id
                }
                case "filter" -> {
                    // base on status
                    if(input.length < 2) {
                        System.out.println("Usage: filter <id>");
                        break;
                    }
                    
                    TaskStatus statusType = TaskStatus.TODO;

                    try {
                        statusType = TaskStatus.valueOf(input[1]); 
                    } catch (IllegalArgumentException e) {
                        System.out.println("Illegal status: " + input[1]);
                    }

                    Map<Integer, Task> tasks = this.taskService.getTasks();
                    for(int id : tasks.keySet()) {
                        if(tasks.get(id).getStatus() == statusType) {
                            this.UI.printFormatted(tasks.get(id));
                        }
                    }
                }
                case "sort" -> {
                      Map<Integer, Task> tasks = this.taskService.getTasks();

                    for (TaskStatus statusType : TaskStatus.values()) {
                        for(int id : tasks.keySet()) {
                            if(tasks.get(id).getStatus() == statusType) {
                                this.UI.printFormatted(tasks.get(id));
                            }
                        }
                    }
                }
                case "help" -> {
                    this.UI.printHelp();
                }
                case "exit" -> {
                    this.UI.printGoodbye();
                    return;
                }
                default -> System.out.printf("Unknown command: '%s'. Type 'help' for a list of commands.\n", command);
            }
        }
    }
}
