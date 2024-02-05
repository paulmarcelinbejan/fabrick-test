package io.github.paulmarcelinbejan.fabrick.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;

import io.github.paulmarcelinbejan.fabrick.dto.accountbalance.AccountBalance;
import io.github.paulmarcelinbejan.fabrick.dto.accounttransactions.AccountTransactions;
import io.github.paulmarcelinbejan.fabrick.dto.moneytransfer.request.MoneyTransferRequest;
import io.github.paulmarcelinbejan.fabrick.dto.moneytransfer.response.MoneyTransferResponse;
import io.github.paulmarcelinbejan.fabrick.service.AccountBalanceService;
import io.github.paulmarcelinbejan.fabrick.service.AccountTransactionsService;
import io.github.paulmarcelinbejan.fabrick.service.MoneyTransferService;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
public class FabrickController {

	private final AccountBalanceService accountBalanceService;

	private final MoneyTransferService moneyTransferService;
	
	private final AccountTransactionsService accountTransactionsService;

	@GetMapping(value = "/lettura-saldo/{accountId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public AccountBalance letturaSaldo(@PathVariable("accountId") String accountId) {
		return accountBalanceService.getAccountBalance(accountId);
	}
	
	@PostMapping(value = "/effettua-bonifico/{accountId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public MoneyTransferResponse effettuaBonifico(
			@Valid @RequestBody MoneyTransferRequest moneyTransferRequest,
			@PathVariable("accountId") String accountId) {
		return moneyTransferService.createMoneyTransfer(accountId, moneyTransferRequest);
	}

	@GetMapping(value = "/lista-transazioni/{accountId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public AccountTransactions listaTransazioni(
			@PathVariable("accountId") String accountId,
			@RequestParam("fromAccountingDate") @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Invalid fromAccountingDate date format. Use yyyy-MM-dd") String fromAccountingDate,
			@RequestParam("toAccountingDate") @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Invalid toAccountingDate date format. Use yyyy-MM-dd") String toAccountingDate) {
		return accountTransactionsService.getAccountTransactions(accountId, fromAccountingDate, toAccountingDate);
	}

}
