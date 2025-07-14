package com.example.SpringApp.Model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity  // Marks this class as a JPA entity (table in DB)
public class User {

    @Id  // Marks 'id' as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-increment ID generation (MySQL style)
    int id;  // Unique identifier for the user (primary key)

    String username;  // Username for login or display
    String email;     // Email address of the user (can be used for login or contact)
    String password;  // Hashed/stored password for authentication

    // One-to-many relationship: One user can have many tasks
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)  
    // 'mappedBy' points to the 'user' field in Task.java (owning side)
    // 'cascade = ALL' means any change (save/delete) to User also affects related tasks
    @JsonIgnore  
    // Prevents infinite loop during JSON serialization (helps with REST APIs)
    List<Task> task;  // List of tasks assigned to this user

    // ------------------ Setter Methods ------------------

    // Set the username
    public void setUsername(String username) {
        this.username = username;
    }

    // Set the email address
    public void setEmail(String email) {
        this.email = email;
    }

    // Set the password
    public void setPassword(String password) {
        this.password = password;
    }

    // Set the user ID (normally not needed when auto-generated)
    public void setId(int id) {
        this.id = id;
    }

    // ------------------ Getter Methods ------------------

    // Get the user ID
    public int getId() {
        return this.id;
    }

    // Get the username
    public String getUsername() {
        return this.username;
    }

    // Get the email address
    public String getEmail() {
        return this.email;
    }

    // Get the password
    public String getPassword() {
        return this.password;
    }
}
