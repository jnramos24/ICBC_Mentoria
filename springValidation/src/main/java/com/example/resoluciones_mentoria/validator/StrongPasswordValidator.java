package com.example.resoluciones_mentoria.validator;

import com.example.resoluciones_mentoria.utils.StrongPassword;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public abstract class StrongPasswordValidator implements ConstraintValidator<StrongPassword, String> {

    @Override
    public void initialize(StrongPassword constraintAnnotation){

    }

    public boolean validPassword(String value, ConstraintValidatorContext context) {
        boolean result = false;
        if (value != null && !value.isEmpty()) {
            String regex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
            result = value.matches(regex);
        }

        return result;
    }
}


