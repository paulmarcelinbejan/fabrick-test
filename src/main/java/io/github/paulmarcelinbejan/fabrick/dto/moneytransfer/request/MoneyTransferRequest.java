package io.github.paulmarcelinbejan.fabrick.dto.moneytransfer.request;

import java.math.BigDecimal;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import io.github.paulmarcelinbejan.fabrick.dto.moneytransfer.Creditor;
import io.github.paulmarcelinbejan.fabrick.dto.moneytransfer.validator.ExecutionDateValidation;

import lombok.Data;

@Data
@ExecutionDateValidation
public class MoneyTransferRequest {

	@Valid
	@NotNull(message = "creditor must not be null!")
	private Creditor creditor;

	private String executionDate;

	private String uri;

	@NotBlank(message = "description must not be blank!")
	@Size(max = 140, message = "description length must not be greather tha 140!")
	private String description;

	@NotNull(message = "amount must not be null!")
	private BigDecimal amount;

	@NotBlank(message = "currency must not be blank!")
	private String currency;

	private boolean isUrgent;

	private boolean isInstant;

	@Pattern(regexp = "^(SHA|OUR|BEN)?$", message = "Invalid feeType. Valid values are 'SHA', 'OUR', 'BEN'.")
	private String feeType = "SHA";

	private String feeAccountId;

	// TODO Italy only, based on what? creditor Address ? creditor IBAN ?
	@Valid
	private TaxRelief taxRelief;

}
