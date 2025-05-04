/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tasktracker.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Task class (fields: id, title, status, deadline, etc.)
 * @author pityafinwe
 */
public class Task {
    
    private final int ID;
    
    private String title;
    private TaskStatus status;
    private final String createdAt;
    private String updatedAt;
    
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    public Task(int id, String title) {
        this.ID = id;
        this.title = title;
        this.status = TaskStatus.TODO;
        this.createdAt = LocalDateTime.now().format(formatter);
    }
    public Task(int id, String title, TaskStatus status, String created, String updated) {
        this.ID = id;
        this.title = title;
        this.status = status;
        this.createdAt = created;
        this.updatedAt = updated;
    }
    
    public void setTitle(String title) {
        this.title = title;
        this.updatedAt = LocalDateTime.now().format(formatter);
    }
    public void setStatus(TaskStatus status) {
        this.status = status;
        this.updatedAt = LocalDateTime.now().format(formatter);
    }
    
    public int getID() {
        return this.ID;
    }
    public String getTitle() {
        return this.title;
    }
    public TaskStatus getStatus() {
        return this.status;
    } 
    public String getCreationTime() {
        return this.createdAt;
    }
    public String getUpdateTime() {
        if(this.updatedAt == null) {
            return null;
        }
        return this.updatedAt;
    }
    
    //use to write to csv
    @Override
    public String toString() {
        return "ID: " + this.ID + " | " + this.title + " - " + this.status;
    }
    
}
