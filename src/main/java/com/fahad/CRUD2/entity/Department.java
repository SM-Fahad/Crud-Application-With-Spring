package com.fahad.CRUD2.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Data
@Entity
@Table(
        name = "department",
        uniqueConstraints = {
                @UniqueConstraint(name = "UK_DEPARTMENT_NAME", columnNames = {"name"}),
        }
)
public class Department extends BaseEntity{
//    @NotBlank(message = "Department name is required")
//    @Size(min = 2, max = 100)
    private String name;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JsonManagedReference
//    @JsonIgnoreProperties("department")
    private List <Student> students;
}
