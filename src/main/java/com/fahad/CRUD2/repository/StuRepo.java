package com.fahad.CRUD2.repository;

import com.fahad.CRUD2.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StuRepo extends JpaRepository<Student, Long> {
}
