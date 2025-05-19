package com.example.devoir.repositories;

import com.example.devoir.models.SchoolClass;
import com.example.devoir.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    List<Teacher> findBySubject(String subject);
    List<Teacher> findBySchoolClasses(SchoolClass schoolClass);
    Optional<Teacher> findByUserId(Long userId);
    void deleteByUserId(Long userId);
}
