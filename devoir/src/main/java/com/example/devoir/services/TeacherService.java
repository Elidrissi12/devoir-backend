package com.example.devoir.services;

import com.example.devoir.models.SchoolClass;
import com.example.devoir.models.Teacher;
import com.example.devoir.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    public List<Teacher> getTeachersBySubject(String subject) {
        return teacherRepository.findBySubject(subject);
    }

    public List<Teacher> getTeachersByClass(SchoolClass schoolClass) {
        return teacherRepository.findBySchoolClasses(schoolClass);
    }
}
