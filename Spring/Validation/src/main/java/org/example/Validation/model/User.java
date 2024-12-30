package org.example.Validation.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class User {
    private long idUser;
    private String name;
    private Integer age;
    private String email;
    private String password;
}