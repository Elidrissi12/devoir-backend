package com.example.devoir.repositories;

import com.example.devoir.models.Assignment;  // Correct import statement
import com.example.devoir.models.SchoolClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    List<Assignment> findBySchoolClass(SchoolClass schoolClass);
}
