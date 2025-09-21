package com.fahad.CRUD2.DTO;

import lombok.Data;

import java.util.List;

@Data
public class DeptDTO {
    private Long id;
    private String name;

    private List<StuDTO> students;
}
