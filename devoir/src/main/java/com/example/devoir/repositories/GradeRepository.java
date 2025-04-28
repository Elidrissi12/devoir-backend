package com.example.devoir.repositories;

import com.example.devoir.models.Grade;
import com.example.devoir.models.Student;
import com.example.devoir.models.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GradeRepository extends JpaRepository<Grade, Long> {
    List<Grade> findByStudent(Student student);
    List<Grade> findByAssignment(Assignment assignment);
}
