package com.fahad.CRUD2.DTO;

import lombok.Data;

import java.util.Set;

@Data
public class CourDTO {
    private Long id;
    private String name;

    private Set<Long> studentIds;
}
