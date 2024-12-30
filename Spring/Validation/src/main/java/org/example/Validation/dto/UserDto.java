package org.example.Validation.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.example.Validation.utils.StrongPass;

@AllArgsConstructor
@Builder
@Data
public class UserDto {
    private long idUser;
    @NotBlank(message = "Name is required")
    private String name;
    @Min(value = 18, message = "The minimum age is 18")
    private Integer age;
    @Email(message = "Email must be valid")
    private String email;
    @StrongPass(message = "The password must have al least 8 characters, one uppercase character, one number and one special character")
    private String password;
}