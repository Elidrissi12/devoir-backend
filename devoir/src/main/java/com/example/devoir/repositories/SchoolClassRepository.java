package com.example.devoir.repositories;

import com.example.devoir.models.SchoolClass;
import com.example.devoir.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SchoolClassRepository extends JpaRepository<SchoolClass, Long> {
    List<SchoolClass> findByTeacher(Teacher teacher);
}
