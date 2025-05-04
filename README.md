# TaskTracker CLI app made in Java for university

## Project structures

```
task-tracker/
│
├── Main.java
├── app/
│   └── TaskTrackerApp.java   → main loop, CLI logic
│
├── model/
│   └── Task.java             → Task class (fields: id, title, status, etc.)
│   └── TaskStatus.java       → Enum for status (TODO, IN_PROGRESS, DONE)
│
├── service/
│   └── TaskService.java      → Core logic (add/edit/delete/list tasks)
│
├── util/
│   └── UIHelper.java         → Utility for getting validated user input
│   └── FileHandler.java      → Optional: save/load tasks to file
│
└── data/
	└── task-data.txt         → (Optional) persistent storage
```

## UML Structure

```mermaid
---
title: Task Tracker UML
---
classDiagram
    class Main {
	    +main(String args[]) void
    }
	class TaskTrackerApp {
		-UIHelper UI
		-FileHandler fileHandler
		-Scanner scanner

		+start() void
	}
	class Task {
		-String title
		-TaskStatus status
		-String createdAt
		-String updatedAt
		-static DateTimeFormatter formatter
	
		+Task(int id, String title) void
		+Task void

		+setTitle(String title) void
		+setStatus(TaskStatus status) void
		+getTitle() String
		+getStatus() TaskStatus
		+getCreationTime() String
		+getUpdatedTime() String
		+toString() String
	}
	class TaskStatus {
		<<enumaration>>
		TODO
		IN_PROGRESS
		DONE
	}
	class TaskService {
		-Map<Integer, Task> tasks
		-int currentId

		+initTasks(Map<Integer, Task> task) void
		+getTasks() Map<Integer, Task>
		+setCurrentId(int id) void
		+getCurrentId() int
		+addTask(String title) Task
		+addTask(String title, TaskStatus status, String created, String updated) void
		+advanceStatus(int id) void
		+deleteTask(int id) void
	}
	class UIHelper{
		-Scanner scanner

		+UIHelper(Scanner scanner)
		+askCommand() String
		+askTaskToAdd() String
		+printIntro() void
		+printHelp() void
		+printGoodbye() void
		+printDB() void
		+printHeader() void
		+printFormatted() void
	}
	class FileHandler {
		-finale String fileName
		-Scanner scanner
		-PrintWriter writer

		+FileHandler()
		+readFromCSV() Map<Integer, Task>
		+addToCSV(Task task) void
		+saveToCSV(Map<Integer, Task>) void
	}
	Main --> TaskTrackerApp : instantiates 
	TaskTrackerApp --> TaskService : uses 
	TaskTrackerApp --> UIHelper : uses 
	TaskService --> Task : manages 
	TaskService --> FileHandler : uses 
	Task --> TaskStatus : has
```
