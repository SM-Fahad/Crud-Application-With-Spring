package com.fahad.CRUD2.repository;

import com.fahad.CRUD2.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeptRepo extends JpaRepository<Department, Long> {

    Optional<Department> findByName(String name);
}
