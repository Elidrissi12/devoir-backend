package com.example.devoir.controllers;

import com.example.devoir.models.SchoolClass;
import com.example.devoir.models.Student;
import com.example.devoir.services.SchoolClassService;
import com.example.devoir.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private SchoolClassService schoolClassService;

    @GetMapping("/class/{classId}")
    public List<Student> getStudentsByClass(@PathVariable Long classId) {
        SchoolClass schoolClass = schoolClassService.getSchoolClassById(classId);
        return studentService.getStudentsByClass(schoolClass);
    }

    @GetMapping("/average/{grade}")
    public List<Student> getStudentsByGrade(@PathVariable Double grade) {
        return studentService.getStudentsByAverageGrade(grade);
    }
}
