package validaciones.validaciones.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import validaciones.validaciones.util.StrongPass;

@AllArgsConstructor
@Data
@Builder
public class UserDto {

    private Long idUser;

    @NotBlank(message = "El nombre es obligatorio")
    private String name;

    @Min(value = 18, message = "La edad mínima es 18")
    private Integer age;

    @Email(message = "El correo debe ser válido")
    private String email;

    @StrongPass(message = "La contraseña debe tener al menos 8 caracteres, una mayúscula, un número y un carácter especial")

    private String password;

}
