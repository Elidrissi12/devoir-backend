package com.example.devoir.controllers;

import com.example.devoir.models.SchoolClass;
import com.example.devoir.models.Student;
import com.example.devoir.models.Teacher;
import com.example.devoir.models.User;
import com.example.devoir.repositories.TeacherRepository;
import com.example.devoir.repositories.UserRepository;
import com.example.devoir.services.SchoolClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classes")
public class SchoolClassController {

    @Autowired
    private SchoolClassService schoolClassService;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private UserRepository userRepository;



    @GetMapping
    public List<SchoolClass> getAllSchoolClasses() {
        return schoolClassService.getAllClasses();
    }


    @PostMapping
    public SchoolClass createClass(@RequestBody SchoolClass schoolClass) {
        return schoolClassService.createClass(schoolClass);
    }

    @GetMapping("/teacher/{teacherId}")
    public List<SchoolClass> getClassesByTeacher(@PathVariable Long teacherId) {
        User user = userRepository.findById(teacherId).orElseThrow(() -> new RuntimeException("User not found"));
        Teacher teacher = teacherRepository.findByUserId(user.getId()).orElseThrow(() -> new RuntimeException("Teacher not found")); // find teacher by id (add code here)
        return schoolClassService.getClassesByTeacher(teacher);
    }



    @PostMapping("/{classId}/{studentId}/add-student")
    public void addStudentToClass(@PathVariable Long classId, @PathVariable Long studentId) {
        schoolClassService.addStudentToClass(classId, studentId);
    }
}
