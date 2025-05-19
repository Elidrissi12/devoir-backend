package com.example.devoir.services;

import com.example.devoir.DTO.UserUpdateDTO;
import com.example.devoir.models.Role;
import com.example.devoir.models.Student;
import com.example.devoir.models.Teacher;
import com.example.devoir.models.User;
import com.example.devoir.repositories.StudentRepository;
import com.example.devoir.repositories.TeacherRepository;
import com.example.devoir.repositories.UserRepository;
import jakarta.persistence.Id;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private  TeacherRepository teacherRepository;
    @Autowired
    private  StudentRepository studentRepository;


    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    /* public User updateUser(Long id, User user) {
        Optional<User> existingUserOpt = userRepository.findById(id);
        if (existingUserOpt.isPresent()) {
            User existingUser = existingUserOpt.get();
            existingUser.setUsername(user.getUsername());
            existingUser.setEmail(user.getEmail());
            existingUser.setPassword(user.getPassword());

            return userRepository.save(existingUser);
        } else {
            throw new RuntimeException("User with id " + id + " not found.");
        }
    }




     */
    @Transactional
    public User updateUser(Long userId, UserUpdateDTO dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setRole(dto.getRole());

        if (dto.getRole() == Role.TEACHER) {
            Teacher teacher = teacherRepository.findByUserId(userId)
                    .orElseGet(() -> {
                        Teacher newTeacher = new Teacher();
                        newTeacher.setUser(user);
                        return newTeacher;
                    });

            teacher.setSubject(dto.getSubject());
            teacherRepository.save(teacher);
            studentRepository.deleteByUserId(userId); // Remove student if exists
        } else if (dto.getRole() == Role.STUDENT) {
            Student student = studentRepository.findByUserId(userId)
                    .orElseGet(() -> {
                        Student newStudent = new Student();
                        newStudent.setUser(user);
                        return newStudent;
                    });

            studentRepository.save(student);
            teacherRepository.deleteByUserId(userId); // Remove teacher if exists
        } else {
            // If ADMIN, remove both
            teacherRepository.deleteByUserId(userId);
            studentRepository.deleteByUserId(userId);
        }

        return userRepository.save(user);
    }

}
