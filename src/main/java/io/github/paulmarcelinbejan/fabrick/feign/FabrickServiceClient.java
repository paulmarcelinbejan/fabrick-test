package io.github.paulmarcelinbejan.fabrick.feign;

import org.springframework.cloud.openfeign.FeignClient;
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

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@FeignClient(name = "fabrickServiceClient", configuration = FabrickClientConfig.class)
public interface FabrickServiceClient {

	@GetMapping(value = "${fabrick.api.get-account-balance}")
	@ResponseBody
	FabrickApiResponse<AccountBalance> getAccountBalance(@PathVariable("accountId") String accountId);

	@PostMapping(value = "${fabrick.api.create-money-transfer}")
	@ResponseBody
	FabrickApiResponse<MoneyTransferResponse> createMoneyTransfer(@RequestBody MoneyTransferRequest request,
			@PathVariable("accountId") String accountId);

	@GetMapping(value = "${fabrick.api.get-account-transactions}")
	@ResponseBody
	FabrickApiResponse<AccountTransactions> getAccountTransactions(
			@PathVariable("accountId") String accountId,
			@RequestParam("fromAccountingDate") String fromAccountingDate,
			@RequestParam("toAccountingDate") String toAccountingDate);

}