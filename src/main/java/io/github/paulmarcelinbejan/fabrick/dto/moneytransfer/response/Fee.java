package io.github.paulmarcelinbejan.fabrick.dto.moneytransfer.response;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Fee {

	private String feeCode;

	private String description;

	private BigDecimal amount;

	private String currency;

}
