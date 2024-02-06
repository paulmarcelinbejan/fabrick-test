package io.github.paulmarcelinbejan.fabrick.dto.accounttransactions;

import java.math.BigDecimal;

import io.github.paulmarcelinbejan.fabrick.enumeration.GBS_TRANSACTION_TYPE;

import lombok.Data;

@Data
public class TransactionDto {

	private String transactionId;

	private String operationId;

	private String accountingDate;

	private String valueDate;

	private GBS_TRANSACTION_TYPE type;

	private BigDecimal amount;

	private String currency;

	private String description;

}
