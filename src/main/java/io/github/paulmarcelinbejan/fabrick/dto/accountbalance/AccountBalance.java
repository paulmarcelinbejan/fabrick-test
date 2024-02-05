package io.github.paulmarcelinbejan.fabrick.dto.accountbalance;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class AccountBalance {

	@JsonFormat(pattern = "yyyy-MM-dd")
	private String date;

	private BigDecimal balance;

	private BigDecimal availableBalance;

	private String currency;

}
