package com.example.devoir.services;

import com.example.devoir.models.SchoolClass;
import com.example.devoir.models.Student;
import com.example.devoir.models.Teacher;
import com.example.devoir.repositories.SchoolClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SchoolClassService {

    @Autowired
    private SchoolClassRepository schoolClassRepository;

    public SchoolClass createClass(SchoolClass schoolClass) {
        return schoolClassRepository.save(schoolClass);
    }

    public List<SchoolClass> getAllClasses() {
        return schoolClassRepository.findAll();
    }

    public List<SchoolClass> getClassesByTeacher(Teacher teacher) {
        return schoolClassRepository.findByTeacher(teacher);
    }

    public void addStudentToClass(Long classId, Student student) {
        SchoolClass schoolClass = schoolClassRepository.findById(classId)
                .orElseThrow(() -> new RuntimeException("Class not found"));
        schoolClass.getStudents().add(student);
        schoolClassRepository.save(schoolClass);
    }

    public SchoolClass getSchoolClassById(Long id) {
        return schoolClassRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Class not found"));
    }

    public Optional<SchoolClass> findById(Long id) {
        return schoolClassRepository.findById(id);
    }
}
