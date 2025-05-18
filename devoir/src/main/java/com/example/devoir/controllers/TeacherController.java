package com.example.devoir.controllers;

import com.example.devoir.models.SchoolClass;
import com.example.devoir.models.Student;
import com.example.devoir.models.Teacher;
import com.example.devoir.services.SchoolClassService;  // Assurez-vous d'importer le service
import com.example.devoir.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private SchoolClassService schoolClassService;  // Ajout de la dépendance SchoolClassService



    @GetMapping
    public List<Teacher> getAllTeachers() {
        return teacherService.getAllTeachers();
    }

    // Récupérer les enseignants par matière
    @GetMapping("/subject/{subject}")
    public List<Teacher> getTeachersBySubject(@PathVariable String subject) {
        return teacherService.getTeachersBySubject(subject);
    }

    @PostMapping
    public Teacher createTeacher(@RequestBody Teacher teacher) {
        return teacherService.saveTeacher(teacher);
    }


    // Récupérer les enseignants par classe
    @GetMapping("/class/{classId}")
    public ResponseEntity<List<Teacher>> getTeachersByClass(@PathVariable Long classId) {
        SchoolClass schoolClass = schoolClassService.getSchoolClassById(classId);
        if (schoolClass == null) {
            return ResponseEntity.notFound().build();  // Retourne 404 si la classe n'est pas trouvée
        }
        List<Teacher> teachers = teacherService.getTeachersByClass(schoolClass);
        return ResponseEntity.ok(teachers);  // Retourne les enseignants de la classe
    }
}
