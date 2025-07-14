// package com.example.SpringApp.Controller;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;
// import com.example.SpringApp.Model.Task;
// import com.example.SpringApp.Service.TaskService;
// @RestController
// @CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true") // Allow frontend access with credentials
// @RequestMapping("/api/task")
// public class TaskController {
//     @Autowired
//     TaskService taskService;
//     // POST endpoint to add task
//     @PostMapping("/add")
//     public ResponseEntity<String> addTask(@RequestBody Task task) {
//         String result = taskService.addTask(task);
//         return ResponseEntity.ok(result);
//     }
// }
package com.example.SpringApp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringApp.Model.Task;
import com.example.SpringApp.Repository.TaskRepository;
import com.example.SpringApp.Service.TaskService;

import jakarta.servlet.http.HttpSession;

@RestController
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true") // Enable session sharing with React
@RequestMapping("/api/task")
public class TaskController {

    @Autowired
    TaskService taskService;

    @Autowired
    TaskRepository tr;

    // Add new task
    @PostMapping("/add")
    public ResponseEntity<String> addTask(@RequestBody Task task) {
        String result = taskService.addTask(task);
        return ResponseEntity.ok(result);
    }

    // Get all tasks for logged-in user
    @GetMapping("/all")
    public ResponseEntity<?> getAllTasks() {
        Object result = taskService.getAllTasks();
        if (result instanceof String) {
            return ResponseEntity.status(401).body(result); // User not logged in
        } else {
            return ResponseEntity.ok(result); // Return list of tasks
        }
    }

    @GetMapping("/user-tasks")
    public ResponseEntity<?> getTasksForLoggedInUser(HttpSession session) {
        Integer userId = (Integer) session.getAttribute("LoggedInUser");
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not logged in");
        }
        return ResponseEntity.ok(taskService.getTasksForUser(userId));
    }
    // @DeleteMapping("/delete/{id}")
    // void DoDelete(@PathVariable int id){
    //     Task t = tr.findById(id).orElse(null);
    //     tr.delete(t);
    //     return ResponseEntity.ok("deleted!");
    // }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable int id) {
        Task t = tr.findById(id);
        if (t == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found");
        }
        tr.delete(t);
        return ResponseEntity.ok("Deleted!");
    }

    // @PutMapping("/update/{id}")
    // public ResponseEntity<String> doUpdate(@PathVariable int id, @RequestBody Task t) {
    //     Task existing = tr.findById(id);
    //     if (existing == null) {
    //         return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found");
    //     }
    //     existing.setTitle(t.getTitle());
    //     existing.setDescription(t.getDescription());
    //     existing.setDueDate(t.getDueDate());
    //     tr.save(existing);
    //     return ResponseEntity.ok("Updated..");
    // }
    @PutMapping("/update/{id}")
    public ResponseEntity<String> doUpdate(@PathVariable int id, @RequestBody Task t) {
        Task existing = tr.findById(id);
        if (existing == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found");
        }
        existing.setTitle(t.getTitle());
        existing.setDescription(t.getDescription());
        existing.setDueDate(t.getDueDate());
        existing.setCompleted(t.isCompleted()); // Add this line to support status update
        tr.save(existing);
        return ResponseEntity.ok("Updated..");
    }

}
