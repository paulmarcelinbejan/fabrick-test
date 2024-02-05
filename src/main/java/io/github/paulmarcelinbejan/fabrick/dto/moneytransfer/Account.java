package io.github.paulmarcelinbejan.fabrick.dto.moneytransfer;

import jakarta.validation.constraints.NotBlank;

import io.github.paulmarcelinbejan.fabrick.dto.moneytransfer.validator.BicCodeValidation;

import lombok.Data;

@Data
@BicCodeValidation
public class Account {

	// TODO validation of accountCode value based on IBAN or SWIFT pattern
	@NotBlank(message = "account.accountCode must not be blank!")
	private String accountCode;

	private String bicCode;

}
