package com.example.devoir.services;

import com.example.devoir.models.Assignment;
import com.example.devoir.models.Grade;
import com.example.devoir.models.Student;
import com.example.devoir.repositories.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeService {

    @Autowired
    private GradeRepository gradeRepository;

    public Grade assignGrade(Grade grade) {
        return (Grade) gradeRepository.save(grade);
    }

    public List<Grade> getGradesByStudent(Student student) {
        return gradeRepository.findByStudent(student);
    }

    public List<Grade> getGradesByAssignment(Assignment assignment) {
        return gradeRepository.findByAssignment(assignment);
    }

    public Double calculateAverageGrade(Student student) {
        List<Grade> grades = gradeRepository.findByStudent(student);
        return grades.stream()
                .mapToInt(Grade::getScore)
                .average()
                .orElse(0.0);
    }

    public void generateReportCard(Student student) {
        Double averageGrade = calculateAverageGrade(student);
        // Logic to generate and store a report card
    }
}
