package com.example.devoir.services;

import com.example.devoir.models.SchoolClass;
import com.example.devoir.models.Student;
import com.example.devoir.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getStudentsByClass(SchoolClass schoolClass) {
        return studentRepository.findBySchoolClass(schoolClass);
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }


    public List<Student> getStudentsByAverageGrade(Double grade) {
        return studentRepository.findByAverageGradeGreaterThan(grade);
    }

    public Optional<Student> findById(Long id) {
        return studentRepository.findById(id);
    }
}
