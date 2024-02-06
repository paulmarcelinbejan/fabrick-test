package io.github.paulmarcelinbejan.fabrick.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import io.github.paulmarcelinbejan.fabrick.enumeration.GBS_TRANSACTION_TYPE;

import lombok.Data;

@Data
@Entity
@Table(name = "TRANSACTION")
public class Transaction {

	@Id
	@Column(name = "transactionId", nullable = false)
	private String transactionId;

	@Column(name = "operationId")
	private String operationId;

	@Column(name = "accountingDate")
	private String accountingDate;

	@Column(name = "valueDate")
	private String valueDate;

	@Enumerated(EnumType.STRING)
	@Column(name = "type")
	private GBS_TRANSACTION_TYPE type;

	@Column(name = "amount")
	private BigDecimal amount;

	@Column(name = "currency")
	private String currency;

	@Column(name = "description")
	private String description;

}