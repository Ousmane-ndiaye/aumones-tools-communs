package com.aumones.tools.communs.web.validator.existObject;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;

@Constraint(validatedBy = { ExistObjectValidator.class })
@Target({ FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistObject {

    Class<?> document();

    String field();

    String message() default "Invalid value";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}