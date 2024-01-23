package com.moviebuzz.validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
 
@Documented
@Constraint(validatedBy = GenreValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface GenreValue {
    String message() default "Invalid genre value";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

