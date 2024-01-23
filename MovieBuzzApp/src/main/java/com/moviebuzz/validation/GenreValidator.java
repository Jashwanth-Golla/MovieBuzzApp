package com.moviebuzz.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Arrays;
 
public class GenreValidator implements ConstraintValidator<GenreValue, String> {
 
    private static final String[] VALID_GENRES = {"ACTION", "HORROR", "THRILLER", "SCIFI", "ROMCOM"};
 
    @Override
    public void initialize(GenreValue constraintAnnotation) {
    }
 
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && Arrays.asList(VALID_GENRES).contains(value.toUpperCase());
    }
}
