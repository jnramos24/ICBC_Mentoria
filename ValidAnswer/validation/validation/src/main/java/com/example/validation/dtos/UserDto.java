package com.example.validation.dtos;
import jakarta.validation.constraints.*;

public class UserDto {

    private Long idUser;
    @NotBlank(message = "el nombre no puede estar vacio")
    private String name;
    @Email(message = "el email debe ser válido")
    private String email;
    @Min(value = 18, message = "La edad minima es 18 años")
    private int age;
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{8,}$",
            message = "La contraseña debe tener al menos 8 caracteres, una letra mayúscula, una letra minúscula, un número y un carácter especial.")
    private String password;

    public UserDto() {
    }

    public UserDto(Long idName, String name, String email, int age, String password) {
        this.idUser = idName;
        this.name = name;
        this.email = email;
        this.age = age;
        this.password = password;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}


