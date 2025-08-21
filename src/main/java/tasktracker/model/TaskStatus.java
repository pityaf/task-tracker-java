/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package tasktracker.model;

/**
 * Enum for status (TODO, IN_PROGRESS, DONE)
 * @author paolo.cantoreggi
 */
public enum TaskStatus {
    TODO, IN_PROGRESS, DONE;

    public TaskStatus next() {
        TaskStatus[] values = values();
        int nextIndex = (this.ordinal() + 1) % values.length;
        return values[nextIndex];
    }
}


