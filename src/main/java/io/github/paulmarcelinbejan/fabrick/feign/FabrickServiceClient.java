package io.github.paulmarcelinbejan.fabrick.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import io.github.paulmarcelinbejan.fabrick.dto.FabrickApiResponse;
import io.github.paulmarcelinbejan.fabrick.dto.accountbalance.AccountBalance;
import io.github.paulmarcelinbejan.fabrick.dto.accounttransactions.AccountTransactions;
import io.github.paulmarcelinbejan.fabrick.dto.moneytransfer.request.MoneyTransferRequest;
import io.github.paulmarcelinbejan.fabrick.dto.moneytransfer.response.MoneyTransferResponse;
import io.github.paulmarcelinbejan.fabrick.feign.config.FabrickClientConfig;
import io.github.paulmarcelinbejan.toolbox.utils.log.audit.Audit;
import io.github.paulmarcelinbejan.toolbox.utils.log.duration.TimeExecution;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@FeignClient(name = "fabrickServiceClient", configuration = FabrickClientConfig.class)
public interface FabrickServiceClient {

	@Audit
	@TimeExecution
	@GetMapping(value = "${fabrick.api.get-account-balance}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody FabrickApiResponse<AccountBalance> getAccountBalance(@PathVariable("accountId") String accountId);

	@Audit
	@TimeExecution
	@PostMapping(value = "${fabrick.api.create-money-transfer}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody FabrickApiResponse<MoneyTransferResponse> createMoneyTransfer(
			@RequestBody MoneyTransferRequest request,
			@PathVariable("accountId") String accountId);

	@Audit
	@TimeExecution
	@GetMapping(value = "${fabrick.api.get-account-transactions}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody FabrickApiResponse<AccountTransactions> getAccountTransactions(
			@PathVariable("accountId") String accountId,
			@RequestParam("fromAccountingDate") String fromAccountingDate,
			@RequestParam("toAccountingDate") String toAccountingDate);

}