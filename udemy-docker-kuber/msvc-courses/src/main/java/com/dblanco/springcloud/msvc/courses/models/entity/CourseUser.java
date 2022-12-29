package com.dblanco.springcloud.msvc.courses.models.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Objects;

@Entity
@Table(name = "tbl_courses_users")
@Data
public class CourseUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "user_id", unique = true)
    private Long userId;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if ( ! (o instanceof CourseUser that)) return false;

        return userId != null && Objects.equals(userId, that.userId);
    }

}
