package com.example.devoir.services;

import com.example.devoir.models.Assignment;  // Correct import
import com.example.devoir.models.SchoolClass;
import com.example.devoir.repositories.AssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssignmentService {

    @Autowired
    private AssignmentRepository assignmentRepository;

    public Assignment createAssignment(Assignment assignment) {
        return assignmentRepository.save(assignment);
    }

    public List<Assignment> getAssignmentsByClass(SchoolClass schoolClass) {
        return assignmentRepository.findBySchoolClass(schoolClass);
    }

    public Optional<Assignment> findById(Long id) {
        return assignmentRepository.findById(id);
    }
}
