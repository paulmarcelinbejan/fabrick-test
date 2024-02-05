package io.github.paulmarcelinbejan.fabrick.dto.moneytransfer.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import io.github.paulmarcelinbejan.fabrick.dto.moneytransfer.request.MoneyTransferRequest;

public class ExecutionDateValidator implements ConstraintValidator<ExecutionDateValidation, MoneyTransferRequest> {

    @Override
	public boolean isValid(MoneyTransferRequest moneyTransferRequest, ConstraintValidatorContext context) {
		if (!moneyTransferRequest.isInstant() && moneyTransferRequest.getExecutionDate() == null) {
			return false;
		}

		return true;
    }
    
}