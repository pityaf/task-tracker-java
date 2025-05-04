package tasktracker.service;

import java.util.HashMap;
import java.util.Map;

import tasktracker.model.Task;
import tasktracker.model.TaskStatus;

/**
 * Core logic (add/edit/delete/list tasks)
 * @author pityafinwe
 */
public class TaskService {
    
    private Map<Integer, Task> tasks;
    private int currentID;
    
    public TaskService() {
        this.currentID = 1;
        this.tasks = new HashMap<>();
    }
    
    public void initTasks(Map<Integer, Task> task) {
        this.tasks = task;
        for(Integer i : task.keySet()) {
            this.currentID = i;
        }
        this.currentID++;
    }
    
    public Map<Integer, Task> getTasks() {
        return this.tasks;
    }
    
    public void setCurrentID(int id) {
        this.currentID = id;
    }
    
    public int getCurrentID() {
        return this.currentID;
    }
    
    public Task addTask(String title) {
        Task newTask = new Task(this.currentID, title);
        this.tasks.put(this.currentID, newTask);
        this.currentID++;
        
        return newTask;
    }
    public void addTask(String title, TaskStatus status, String created, String updated) {
        this.tasks.put(this.currentID, new Task(this.currentID, title, status, created, updated));
        this.currentID++;
    }
    
    public void advanceStatus(int id) {
        switch(this.tasks.get(id).getStatus()) {
            case DONE -> System.out.println("Task already completed.");
            case TODO -> this.tasks.get(id).setStatus(TaskStatus.IN_PROGRESS);
            case IN_PROGRESS -> this.tasks.get(id).setStatus(TaskStatus.DONE);
        }
    }
    
    public void deleteTask(int id) {
        if(!this.tasks.containsKey(id)) {
            return;
        }
        this.tasks.remove(id);
    }
       
}
