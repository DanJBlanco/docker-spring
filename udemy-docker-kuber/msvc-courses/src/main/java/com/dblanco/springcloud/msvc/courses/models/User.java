package com.dblanco.springcloud.msvc.courses.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {

    private Long id;
    private String name;
    private String email;
    private String password;

}
