package com.example.resoluciones_mentoria.utils;

import com.example.resoluciones_mentoria.validator.StrongPasswordValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = StrongPasswordValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface StrongPassword {

    String message() default "Revisar la contraseña porque no contiene los niveles de seguridad adecuados";

    Class <?>[]  groups() default {};

    Class<? extends Payload>[] payload() default {};
}
