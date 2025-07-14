package com.example.SpringApp.Model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity  // Marks this class as a JPA entity (table in the database)
public class Task {

    @Id  // Marks 'id' as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-increment strategy for the primary key
    int id;  // Unique ID for each task

    String title;  // Title or name of the task
    String description;  // Description/details of the task (note: spelling is intentionally preserved if required)
    LocalDate dueDate;  // Date by which the task should be completed

    boolean completed = false;  // Status of task: true if done, false otherwise

    // Many tasks can belong to one user
    @ManyToOne
    @JoinColumn(name = "user_id")  // Creates a foreign key column in Task table to reference User
    User user;  // Reference to the user who owns this task

    // ------------------ Setter Methods ------------------

    // Set the task title
    public void setTitle(String title) {
        this.title = title;
    }

    // Set the task description
    public void setDescription(String description) {
        this.description = description;
    }

    // Set the task due date
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    // Set the completed status of the task
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    // Set the task ID (useful for testing or manual assignment)
    public void setId(int id) {
        this.id = id;
    }

    // Set the associated user for this task
    public void setUser(User user) {
        this.user = user;
    }

    // ------------------ Getter Methods ------------------

    // Get the task ID
    public int getId() {
        return this.id;
    }

    // Get the task title
    public String getTitle() {
        return this.title;
    }

    // Get the task description
    public String getDescription() {
        return this.description;
    }

    // Get the task due date
    public LocalDate getDueDate() {  // Fixed method name from getDoDuete()
        return this.dueDate;
    }

    // Get the completion status
    public boolean isCompleted() {
        return this.completed;
    }

    // Get the associated user of the task
    public User getUser() {
        return this.user;
    }
}
