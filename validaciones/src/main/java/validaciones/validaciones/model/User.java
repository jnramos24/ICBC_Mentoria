package validaciones.validaciones.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class User {

    private Long idUser;
    private String name;
    private Integer age;
    private String email;
    private String password;
}
