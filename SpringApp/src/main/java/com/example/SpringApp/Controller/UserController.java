// package com.example.SpringApp.Controller;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;
// import com.example.SpringApp.Model.User;
// import com.example.SpringApp.Service.UserService;
// @RestController
// @CrossOrigin(origins = "http://localhost:5173") // ✅ Fixed: added '//' after http
// @RequestMapping("/api/user") // ✅ Added base path for better API structure
// public class UserController {
//     @Autowired
//     UserService us;
//     @PostMapping("/register") // ✅ Fixed spelling mistake: "regitser" → "register"
//     public ResponseEntity<?> DoRegister(@RequestBody User u) {
//         String result = us.RegisterUser(u);
//         if (result.equals("Registration Successful")) {
//             return ResponseEntity.status(HttpStatus.CREATED).body(result);
//         } else {
//             return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
//         }
//     }
//     @PostMapping("/login")
//     public ResponseEntity<String> doLogin(@RequestBody User u) {
//         String result = us.loginUser(u.getEmail(), u.getPassword());
//         if (result.equals("Login Success")) {
//             return ResponseEntity.ok(result); // 200 OK
//         } else {
//             return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result); // 401 Unauthorized
//         }
//     }
//     // @PostMapping("/login")
//     // public ResponseEntity<String> doLogin(@RequestBody User u, HttpSession session) {
//     //     String result = us.loginUser(u.getEmail(), u.getPassword());
//     //     if (result.equals("Login Success")) {
//     //         session.setAttribute("user", u.getEmail()); // ✅ Store in session for later use
//     //         return ResponseEntity.ok(result);
//     //     } else {
//     //         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
//     //     }
//     // }
// }
package com.example.SpringApp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringApp.Model.User;
import com.example.SpringApp.Service.UserService;

import jakarta.servlet.http.HttpSession;

@RestController
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true") // Allow frontend requests with cookies
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService us;

    // REGISTER a new user
    @PostMapping("/register")
    public ResponseEntity<?> doRegister(@RequestBody User u) {
        String result = us.RegisterUser(u);

        if (result.equals("Registration Successful")) {
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
    }

    // LOGIN: validate and create session
    @PostMapping("/login")
    public ResponseEntity<String> doLogin(@RequestBody User u, HttpSession session) {
        String result = us.loginUser(u.getEmail(), u.getPassword());

        if (result.equals("Login Success")) {
            // ✅ Store user ID in session
            // session.setAttribute("LoggedInUser", us.getEmail(u.getEmail()).getId());
            session.setAttribute("LoggedInUser", us.getUserByEmail(u.getEmail()).getId());//imported fromuserservice..

            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
        }
    }
    @GetMapping("/profile")
    public ResponseEntity<?> getLoggedInUser(HttpSession session) {
        Integer id = (Integer) session.getAttribute("LoggedInUser"); // ✅ Correct key

        if (id == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not logged in");
        }

        User user = us.getUserById(id);
        return ResponseEntity.ok(user); // Sends the full User object as JSON
    }

//     @GetMapping("/profile")
// public ResponseEntity<?> getLoggedInUser(HttpSession session) {
//     Integer id = (Integer) session.getAttribute("LoggedInUser");

//     if (id == null) {
//         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not logged in");
//     }

//     User user = us.getUserById(id);
//     return ResponseEntity.ok(user); // Make sure user has .getUsername()
// }


}
