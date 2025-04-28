package com.example.devoir.repositories;

import com.example.devoir.models.Student;
import com.example.devoir.models.Submission;
import com.example.devoir.models.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {
    List<Submission> findByAssignment(Assignment assignment);
    List<Submission> findByStudent(Student student);
}
