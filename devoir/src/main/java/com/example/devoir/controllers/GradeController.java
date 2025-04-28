package com.example.devoir.controllers;

import com.example.devoir.models.Assignment;
import com.example.devoir.models.Grade;
import com.example.devoir.models.Student;
import com.example.devoir.services.AssignmentService;
import com.example.devoir.services.GradeService;
import com.example.devoir.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/grades")
public class GradeController {

    @Autowired
    private GradeService gradeService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private AssignmentService assignmentService;

    @PostMapping
    public Grade assignGrade(@RequestBody Grade grade) {
        return gradeService.assignGrade(grade);
    }

    @GetMapping("/student/{studentId}")
    public List<Grade> getGradesByStudent(@PathVariable Long studentId) {
        Student student = studentService.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        return gradeService.getGradesByStudent(student);
    }

    @GetMapping("/assignment/{assignmentId}")
    public List<Grade> getGradesByAssignment(@PathVariable Long assignmentId) {
        Assignment assignment = assignmentService.findById(assignmentId)
                .orElseThrow(() -> new RuntimeException("Assignment not found"));
        return gradeService.getGradesByAssignment(assignment);
    }

    @GetMapping("/student/{studentId}/average")
    public Double getAverageGrade(@PathVariable Long studentId) {
        Student student = studentService.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        return gradeService.calculateAverageGrade(student);
    }
}
