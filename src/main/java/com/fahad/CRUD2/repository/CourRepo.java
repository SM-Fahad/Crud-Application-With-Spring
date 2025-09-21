package com.fahad.CRUD2.repository;

import com.fahad.CRUD2.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CourRepo extends JpaRepository<Course, Long> {

    @Query("SELECT c FROM Student s JOIN s.courses c WHERE s.id = :studentId")
    Set<Course> findCoursesByStudentId(Long studentId);
}
