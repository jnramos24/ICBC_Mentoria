package com.example.resoluciones_mentoria.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserDTO {

    private Long id;

    @NotBlank(message = "Es obligatorio colocar tu nombre")
    private String name;

    @Email(message = "El correo electrónico debe ser válido")
    private String email;

    @NotNull (message = "La edad a ingresar no debe ser nula")
    @Min(value = 18, message = "La edad debe ser mayor o igual a 18 años")
    private Integer age;

    @Size(min = 8, max = 16, message = "La contraseña que elijas debe tener entre 8 y 16 caracteres")
    private String password;
}
