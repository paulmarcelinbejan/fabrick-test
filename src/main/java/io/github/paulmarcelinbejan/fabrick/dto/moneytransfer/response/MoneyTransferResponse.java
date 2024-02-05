package io.github.paulmarcelinbejan.fabrick.dto.moneytransfer.response;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

import io.github.paulmarcelinbejan.fabrick.dto.moneytransfer.Creditor;

import lombok.Data;

@Data
public class MoneyTransferResponse {

	private String moneyTransferId;

	private String status;

	private String direction;

	private Creditor creditor;

	private Debtor debtor;

	private String cro;

	private String trn;

	private String uri;

	private String description;

	private ZonedDateTime createdDatetime;

	private ZonedDateTime accountedDatetime;

	private LocalDate debtorValueDate;

	private LocalDate creditorValueDate;

	private Amount amount;

	private boolean isInstant;

	private String feeType;

	private String feeAccountID;

	private List<Fee> fees;

	private boolean hasTaxRelief;

}
