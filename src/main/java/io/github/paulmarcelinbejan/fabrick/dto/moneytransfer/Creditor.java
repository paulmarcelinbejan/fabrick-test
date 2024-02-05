package io.github.paulmarcelinbejan.fabrick.dto.moneytransfer;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.Data;

@Data
public class Creditor {

	@NotBlank(message = "creditor.name must not be blank!")
	private String name;

	@NotNull(message = "creditor.account must not be null!")
	@Valid
	private Account account;

	//TODO validation : Required if the creditor account is rooted on SEPA and non- SEPA bank other than Italy.
	private Address address;

}
