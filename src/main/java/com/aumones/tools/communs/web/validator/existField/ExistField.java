package com.aumones.tools.communs.web.validator.existField;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;

@Constraint(validatedBy = { ExistFieldValidator.class })
@Target({ FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistField {

    Class<?> document();

    String field();

    String message() default "Invalid value";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}