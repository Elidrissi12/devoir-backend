package com.example.devoir.controllers;

import com.example.devoir.models.Assignment;
import com.example.devoir.models.SchoolClass;
import com.example.devoir.services.AssignmentService;
import com.example.devoir.services.SchoolClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assignments")
public class AssignmentController {

    @Autowired
    private AssignmentService assignmentService;

    @Autowired
    private SchoolClassService schoolClassService;  // Autowiring service to get SchoolClass

    @PostMapping
    public Assignment createAssignment(@RequestBody Assignment assignment) {
        return assignmentService.createAssignment(assignment);
    }

    @GetMapping("/class/{classId}")
    public List<Assignment> getAssignmentsByClass(@PathVariable Long classId) {
        // Fetch the SchoolClass by ID
        SchoolClass schoolClass = schoolClassService.findById(classId).orElse(null);
        if (schoolClass == null) {
            // Handle the case when the SchoolClass is not found
            throw new RuntimeException("SchoolClass not found for ID: " + classId);
        }
        return assignmentService.getAssignmentsByClass(schoolClass);
    }
}
