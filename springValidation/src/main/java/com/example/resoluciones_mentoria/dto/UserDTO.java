package com.example.resoluciones_mentoria.dto;

import com.example.resoluciones_mentoria.utils.StrongPassword;
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

    //En caso de no utilizar una anotación personalizada, podemos usar cualquiera de estos dos ejemplos e incluir un mensaje
    //@Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{8,}$",
          //  message = "La contraseña que elijas debe tener entre 8 y 16 caracteres, una letra mayúscula, una letra minúscula, un número y un carácter especial.")

    //@Size(min = 8, max = 16, message = "La contraseña que elijas debe tener entre 8 y 16 caracteres")
   @StrongPassword(message = "La contraseña que elijas debe tener entre 8 y 16 caracteres, una letra mayúscula, una letra minúscula, un número y un carácter especial.")
    private String password;
}
