package com.fahad.CRUD2.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Course extends BaseEntity {
    @NotBlank(message = "Course name is required")
    @Size(min = 2, max = 100)
    private String name;

    @ManyToMany (mappedBy = "courses")
    private Set<Student> students = new HashSet<>();
}
