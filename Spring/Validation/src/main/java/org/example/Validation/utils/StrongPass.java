package org.example.Validation.utils;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = StrongPassValidator.class) // Asocia la anotación con el validador
@Target({ ElementType.FIELD }) // Define que se puede usar en campos (atributos)
@Retention(RetentionPolicy.RUNTIME) // La anotación estará disponible en tiempo de ejecución
public @interface StrongPass {
    // Mensaje de error predeterminado
    String message() default "The password doesn't meet the security requirements!";

    // Grupos (opcional, para validaciones específicas)
    Class<?>[] groups() default {};

    // Payload (opcional, para metadatos adicionales)
    Class<? extends Payload>[] payload() default {};
}