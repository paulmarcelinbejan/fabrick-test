package io.github.paulmarcelinbejan.fabrick.dto.moneytransfer.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = LegalPersonBeneficiaryValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface LegalPersonBeneficiaryValidation {

	String message() default "legalPersonBeneficiary required when beneficiaryType is false LEGAL_PERSON!";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}