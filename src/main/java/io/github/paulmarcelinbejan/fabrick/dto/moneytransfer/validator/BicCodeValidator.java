package io.github.paulmarcelinbejan.fabrick.dto.moneytransfer.validator;

import java.util.regex.Pattern;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import io.github.paulmarcelinbejan.fabrick.dto.moneytransfer.Account;

import io.micrometer.common.util.StringUtils;

public class BicCodeValidator implements ConstraintValidator<BicCodeValidation, Account> {

	private static final Pattern ACCOUNT_CODE_SWIFT_PATTERN = Pattern
			.compile("^[A-Z]{4}[A-Z]{2}[0-9A-Z]{2}[0-9A-Z]{3}$");

    @Override
    public boolean isValid(Account account, ConstraintValidatorContext context) {
        if (account != null) {
        	String accountCode = account.getAccountCode();

    		// Validation: bicCode required if a SWIFT account number is provided as accountCode. 
    		// Optional if an IBAN code is provided as accountCode.
    		if (accountCode != null && (ACCOUNT_CODE_SWIFT_PATTERN.matcher(accountCode).matches() && StringUtils.isBlank(account.getBicCode()))) {
    			return false;
            }
		}

		return true;
    }
    
}