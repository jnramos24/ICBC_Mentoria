package org.example.Validation.utils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StrongPassValidator implements ConstraintValidator<StrongPass, String> {

    // Metodo que inicializa la validación (opcional)
    @Override
    public void initialize(StrongPass constraintAnnotation) {
        // Puedes usar esta función para configurar el validador, si es necesario.
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return false; // La contraseña no puede ser nula o vacía
        }

        // Regla: al menos 8 caracteres, una letra mayúscula, un número y un carácter especial
        String regex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        return value.matches(regex);
    }
}