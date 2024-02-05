package io.github.paulmarcelinbejan.fabrick.dto.accounttransactions;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Transactions {

	private String transactionId;

	private String operationId;

	private String accountingDate;

	private String valueDate;

	private Type type;

	private BigDecimal amount;

	private String currency;

	private String description;

	@Data
	public static class Type {
		private String enumeration;
		private String value;
	}

}
