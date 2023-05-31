package com.aumones.tools.communs.web.validator.uniqueObject;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;

@Constraint(validatedBy = { UniqueObjectValidator.class })
@Target({ TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueObject {

    Class<?> document();

    String message() default "Invalid value";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}