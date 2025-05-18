package com.example.devoir.controllers;

import com.example.devoir.models.Student;
import com.example.devoir.models.Teacher;
import com.example.devoir.models.User;
import com.example.devoir.services.StudentService;
import com.example.devoir.services.TeacherService;
import com.example.devoir.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;

    // Inscription d'un utilisateur
   /* @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody User user) {
        // Vérification si l'email est déjà pris
        Optional<User> existingUser = userService.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            return ResponseEntity.badRequest().body("Email already in use.");
        }

        // Sauvegarde de l'utilisateur sans encodage du mot de passe
        userService.createUser(user);
        return ResponseEntity.ok("User registered successfully.");
    }

    */



    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody User user) {
        Optional<User> existingUser = userService.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            return ResponseEntity.badRequest().body("Email already in use.");
        }

        // Save user
        User savedUser = userService.createUser(user);

        // Depending on role, create Student or Teacher
        switch (savedUser.getRole()) {
            case STUDENT:
                Student student = new Student();
                student.setUser(savedUser);
                studentService.saveStudent(student);
                break;
            case TEACHER:
                Teacher teacher = new Teacher();
                teacher.setUser(savedUser);
                teacherService.saveTeacher(teacher);
                break;
            default:
                // Do nothing for ADMIN
                break;
        }

        return ResponseEntity.ok("User registered successfully.");
    }


    // Connexion d'un utilisateur
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) {
        Optional<User> existingUser = userService.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            if (user.getPassword().equals(existingUser.get().getPassword())) {
                return ResponseEntity.ok(existingUser.get());
            } else {
                return ResponseEntity.badRequest().body(null);
            }
        }
        return ResponseEntity.badRequest().body(null);
    }

    // Réinitialisation du mot de passe
    @PostMapping("/reset-password")
    public String resetPassword(@RequestBody String email) {
        Optional<User> user = userService.findByEmail(email);
        if (user.isPresent()) {
            // Logique pour générer un lien de réinitialisation ou un mot de passe temporaire
            // Ex: Envoi par email avec un lien ou mot de passe généré
            return "Password reset link sent to " + email;
        }
        return "User not found";
    }
}
