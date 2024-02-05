package io.github.paulmarcelinbejan.fabrick.dto.moneytransfer.response;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Amount {

	private BigDecimal debtorAmount;

	private String debtorCurrency;

	private BigDecimal creditorAmount;

	private String creditorCurrency;

	private String creditorCurrencyDate;

	private BigDecimal exchangeRate;

}
