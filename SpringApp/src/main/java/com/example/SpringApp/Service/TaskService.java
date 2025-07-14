// package com.example.SpringApp.Service;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
// import com.example.SpringApp.Model.Task;
// import com.example.SpringApp.Model.User;
// import com.example.SpringApp.Repository.TaskRepository;
// import com.example.SpringApp.Repository.UserRepository;
// import jakarta.servlet.http.HttpSession;
// @Service
// public class TaskService {
//     @Autowired
//     TaskRepository taskRepo;
//     @Autowired
//     UserRepository userRepo;
//     @Autowired
//     HttpSession session;
//     // Method to add a task with session-based user binding
//     public String addTask(Task task) {
//         // Get logged-in user ID from session
//         Integer userId = (Integer) session.getAttribute("LoggedInuser");
//         // If session is missing, return error
//         if (userId == null) {
//             return "User not logged in!";
//         }
//         // Fetch user from DB
//         User user = userRepo.findById(userId).orElse(null);
//         // If user not found in DB
//         if (user == null) {
//             return "User not found!";
//         }
//         // Bind task to user and save
//         task.setUser(user);
//         taskRepo.save(task);
//         return "Task added successfully!";
//     }
// }
package com.example.SpringApp.Service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SpringApp.Model.Task;
import com.example.SpringApp.Model.User;
import com.example.SpringApp.Repository.TaskRepository;
import com.example.SpringApp.Repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepo;

    @Autowired
    UserRepository userRepo;

    @Autowired
    HttpSession session;

    // Add task to DB for the logged-in user
    public String addTask(Task task) {
        // Get logged-in user ID from session
        Integer userId = (Integer) session.getAttribute("LoggedInUser");

        if (userId == null) {
            return "User not logged in!";
        }

        // Get user object from DB
        User user = userRepo.findById(userId).orElse(null);
        if (user == null) {
            return "User not found!";
        }

        // Set task's user and save
        task.setUser(user);
        taskRepo.save(task);
        return "Task added successfully!";
    }

    // Return all tasks for the logged-in user
    public Object getAllTasks() {
        Integer userId = (Integer) session.getAttribute("LoggedInUser");
        if (userId == null) {
            return "User not logged in!";
        }
        return taskRepo.findAllByUserId(userId);
    }

    public List<Task> getUserTasks() {
        Integer userId = (Integer) session.getAttribute("LoggedInuser");
        if (userId == null) {
            return Collections.emptyList();
        }
        return taskRepo.findByUserId(userId);
    }

    public List<Task> getTasksForUser(int userId) {
        return taskRepo.findByUserId(userId);
    }

}
