package tasktracker.util;

import java.util.Map;
import java.util.Scanner;

import tasktracker.model.Task;

/**
 * Utility for getting validated user input
 * @author pityafinwe
 */
public class UIHelper {
    
    private final Scanner scanner;
    
    public UIHelper(Scanner scan) {
        this.scanner = scan;
    }
    
    public String askCommand() {
        System.out.print("> ");
        return this.scanner.nextLine();
    }
    
    public String askTaskToAdd() {
        System.out.print("Name:");
        return this.scanner.nextLine();
    }
    
    public void printIntro() {
        System.out.println("Welcome to task tracker CLI!");
        System.out.println("Type 'help' to see available commands.\n");
    }
    
    public void printHelp() {
        System.out.println("");
        System.out.println("-".repeat(90));
        System.out.println("  add               |   Add a new task");
        System.out.println("  update            |   Update task status");
        System.out.println("  list              |   List all tasks created");
        System.out.println("  view <id>         |   View a single task given the ID");
        System.out.println("  edit <id>         |   Edit a single task given the ID");
        System.out.println("  delete <id>       |   Delete a single task given the ID");
        System.out.println("  status <id>       |   Status of a single task given the ID");
        System.out.println("  filter <status>   |   Filter active tasks based on STATUS");
        System.out.println("  sort <field>      |   Sort actvie tasks based on the given field");
        System.out.println("  help              |   Print help");
        System.out.println("  exit              |   Exit application");
        System.out.println("-".repeat(90));
        System.out.println("");
    }
    
    public void printGoodbye() {
        System.out.println("Exiting task tracker. Bye!");
    }
    
    public void printDB(Map<Integer, Task> tasks) {
        System.out.println("");
        this.printHeader();
        for(int id : tasks.keySet()) {
            this.printFormatted(tasks.get(id));
        }
        System.out.println("");
    }
    
    public void printHeader() {
        System.out.println("-".repeat(90));
        System.out.printf(
            "lis%-5s %-25s %-15s %-20s %-20s%n",
            "ID", "Title", "Status", "Created At", "Updated At"
        );
        System.out.println("-".repeat(90));
    }
    
    public void printFormatted(Task task) {
        System.out.printf(
            "%-5d %-25s %-15s %-20s %-20s%n",
            task.getID(),
            task.getTitle(),
            task.getStatus(),
            task.getCreationTime(),
            task.getUpdateTime()
        );
    }
}
