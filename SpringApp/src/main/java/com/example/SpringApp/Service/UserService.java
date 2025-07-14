package com.example.SpringApp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SpringApp.Model.User;
import com.example.SpringApp.Repository.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository ur;

    // Register a new user
    public String RegisterUser(User u) {
        User existing = ur.findByEmail(u.getEmail());
        if (existing != null) {
            return "Email exists";
        }
        ur.save(u);
        return "Registration Successful";
    }

    // Validate login credentials
    public String loginUser(String email, String password) {
        User user = ur.findByEmail(email);
        if (user == null) {
            return "Email not found";
        } else if (!user.getPassword().equals(password)) {
            return "Incorrect password";
        }
        return "Login Success";
    }

    // Helper method to retrieve User by email
    public User getUserByEmail(String email) {
        return ur.findByEmail(email);
    }

    public User getUserById(int id) {
    return ur.findById(id).orElse(null);
}

}
