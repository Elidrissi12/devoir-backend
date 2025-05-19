package com.example.devoir.DTO;

import com.example.devoir.models.Role;

public class UserUpdateDTO {
    private String username;
    private String email;
    private Role role; // TEACHER or STUDENT
    private String subject;// Optional, only for teacher


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}