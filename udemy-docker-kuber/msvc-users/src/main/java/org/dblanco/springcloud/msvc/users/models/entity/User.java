package org.dblanco.springcloud.msvc.users.models.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "tbl_users")
public class User {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;
}