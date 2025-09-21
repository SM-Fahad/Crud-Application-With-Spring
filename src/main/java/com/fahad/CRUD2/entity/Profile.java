package com.fahad.CRUD2.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "profiles")
@Data
public class Profile extends BaseEntity {
    private String firstName;
    private String lastName;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
