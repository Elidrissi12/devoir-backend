package com.example.devoir.controllers;

import com.example.devoir.models.Submission;
import com.example.devoir.services.AssignmentService;
import com.example.devoir.services.SubmissionService;
import com.example.devoir.models.Assignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/submissions")
public class SubmissionController {

    @Autowired
    private SubmissionService submissionService;

    @Autowired
    private AssignmentService assignmentService;

    // Soumettre un devoir
    @PostMapping
    public ResponseEntity<Submission> submitAssignment(@RequestBody Submission submission) {
        Submission savedSubmission = submissionService.submitAssignment(submission);
        return ResponseEntity.status(201).body(savedSubmission);  // Code 201 pour "Créé"
    }

    // Obtenir les soumissions par devoir
    @GetMapping("/assignment/{assignmentId}")
    public ResponseEntity<List<Submission>> getSubmissionsByAssignment(@PathVariable Long assignmentId) {
        Assignment assignment = assignmentService.findById(assignmentId)
                .orElseThrow(() -> new RuntimeException("Assignment not found"));
        List<Submission> submissions = submissionService.getSubmissionsByAssignment(assignment);
        return ResponseEntity.ok(submissions);  // Code 200 pour "OK"
    }

    // Noter une soumission
    @PostMapping("/{submissionId}/grade")
    public ResponseEntity<String> gradeSubmission(@PathVariable Long submissionId, @RequestParam Integer grade) {
        submissionService.gradeSubmission(submissionId, grade);
        return ResponseEntity.ok("Grade assigned successfully");  // Code 200 pour "OK"
    }
}
