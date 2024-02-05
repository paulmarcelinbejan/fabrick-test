package io.github.paulmarcelinbejan.fabrick.dto.moneytransfer.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = ExecutionDateValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ExecutionDateValidation {

	String message() default "executionDate required when isInstant is false!";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}