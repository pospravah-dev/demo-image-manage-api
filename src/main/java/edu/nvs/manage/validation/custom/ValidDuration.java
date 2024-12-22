package edu.nvs.manage.validation.custom;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DurationValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidDuration {
    String message() default "Invalid duration";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
