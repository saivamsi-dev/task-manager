package com.example.SpringApp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SpringApp.Model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

    // Optional: You can use this if you want to fetch a specific task manually
    Task findById(int id);
    
    // You can also add custom queries later if needed
    // Example: List<Task> findByUserId(int userId);
    List<Task> findAllByUserId(int userId);
    List<Task> findByUserId(int userId);

}
