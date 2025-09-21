package com.fahad.CRUD2.DTO;

import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class StuDTO {
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private LocalDate dob;

    private DeptDTO deptDto;
    private Set<Long> courseIds;

}
