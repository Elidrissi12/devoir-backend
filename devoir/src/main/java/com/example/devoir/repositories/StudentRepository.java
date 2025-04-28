package com.example.devoir.repositories;

import com.example.devoir.models.SchoolClass;
import com.example.devoir.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findBySchoolClass(SchoolClass schoolClass);
    List<Student> findByAverageGradeGreaterThan(Double averageGrade);
}
