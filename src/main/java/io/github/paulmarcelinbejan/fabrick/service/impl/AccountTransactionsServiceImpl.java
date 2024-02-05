package io.github.paulmarcelinbejan.fabrick.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.github.paulmarcelinbejan.fabrick.dto.FabrickApiResponse;
import io.github.paulmarcelinbejan.fabrick.dto.accounttransactions.AccountTransactions;
import io.github.paulmarcelinbejan.fabrick.exception.FabrickException;
import io.github.paulmarcelinbejan.fabrick.feign.FabrickServiceClient;
import io.github.paulmarcelinbejan.fabrick.service.AccountTransactionsService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class AccountTransactionsServiceImpl implements AccountTransactionsService {

	public AccountTransactionsServiceImpl(
			FabrickServiceClient fabrickServiceClient,
			@Value("${fabrick.base-url}") String fabrickBaseUrl,
			@Value("${fabrick.api.get-account-transactions}") String fabrickApiGetAccountTransactions) {
		this.fabrickServiceClient = fabrickServiceClient;
		url = fabrickBaseUrl + fabrickApiGetAccountTransactions;
	}

	private final FabrickServiceClient fabrickServiceClient;
	
	private final String url;

	@Override
	public AccountTransactions getAccountTransactions(String accountId, String fromAccountingDate, String toAccountingDate) {
		log.info(" -> sending request to {} for accountId {}", url, accountId);

		FabrickApiResponse<AccountTransactions> accountTransactions = fabrickServiceClient
				.getAccountTransactions(accountId, fromAccountingDate, toAccountingDate);
		
		log.info(" <- response received from {}{} for accountId {}", url, accountId);
		
		if (accountTransactions == null) {
			throw new FabrickException("Received unexpected response from " + url);
		}
		if (StringUtils.equals(accountTransactions.getStatus(), "KO")) {
			throw new FabrickException("KO from " + url + " with following errors: " + accountTransactions.getError());
		}
		
		return accountTransactions.getPayload();
	}

}
