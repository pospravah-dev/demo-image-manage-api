package edu.nvs.manage.validation.custom;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DurationValidator implements ConstraintValidator<ValidDuration, Integer> {

    @Override
    public void initialize(ValidDuration constraintAnnotation) {
    }

    @Override
    public boolean isValid(Integer duration, ConstraintValidatorContext context) {
        // Custom validation logic: For example, duration must be between 1 and 3600 seconds
        return duration != null && duration > 0 && duration <= 3600;
    }
}
