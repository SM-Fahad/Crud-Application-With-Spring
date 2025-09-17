package com.fahad.CRUD2.repository;

import com.fahad.CRUD2.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StuRepo extends JpaRepository<Student, Long> {
}
