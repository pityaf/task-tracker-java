package tasktracker.app;

import java.util.Scanner;

import tasktracker.model.Task;
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
                }
                case "status" -> {
                }
                case "update" -> {
                }
                case "edit"-> {
                }
                case "filter" -> {
                }
                case "sort" -> {
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
