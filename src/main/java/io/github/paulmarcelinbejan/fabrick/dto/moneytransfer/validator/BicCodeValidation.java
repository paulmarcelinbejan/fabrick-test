package io.github.paulmarcelinbejan.fabrick.dto.moneytransfer.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = BicCodeValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface BicCodeValidation {

	String message() default "bicCode required when accountCode contains SWIFT account number!";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}