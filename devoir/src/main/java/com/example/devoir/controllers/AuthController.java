package com.example.devoir.controllers;

import com.example.devoir.models.User;
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

    // Inscription d'un utilisateur
    @PostMapping("/signup")
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

    // Connexion d'un utilisateur
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        Optional<User> existingUser = userService.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            // Vérification du mot de passe sans encodage
            if (user.getPassword().equals(existingUser.get().getPassword())) {
                return ResponseEntity.ok("Login successful.");
            } else {
                return ResponseEntity.badRequest().body("Invalid credentials.");
            }
        }
        return ResponseEntity.badRequest().body("User not found.");
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
