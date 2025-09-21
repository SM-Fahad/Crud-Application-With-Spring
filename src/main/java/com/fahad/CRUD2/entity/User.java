package com.fahad.CRUD2.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String username;

    @OneToOne(mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private Profile profile;

    public void setProfile(Profile profile) {
        this.profile = profile;
        if (profile != null) profile.setUser(this);
    }
}
