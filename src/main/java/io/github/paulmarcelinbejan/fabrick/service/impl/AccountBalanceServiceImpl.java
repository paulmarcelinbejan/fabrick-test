package io.github.paulmarcelinbejan.fabrick.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.github.paulmarcelinbejan.fabrick.dto.FabrickApiResponse;
import io.github.paulmarcelinbejan.fabrick.dto.accountbalance.AccountBalance;
import io.github.paulmarcelinbejan.fabrick.exception.FabrickException;
import io.github.paulmarcelinbejan.fabrick.feign.FabrickServiceClient;
import io.github.paulmarcelinbejan.fabrick.service.AccountBalanceService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class AccountBalanceServiceImpl implements AccountBalanceService {

	public AccountBalanceServiceImpl(
			FabrickServiceClient fabrickServiceClient,
			@Value("${fabrick.base-url}") String fabrickBaseUrl,
			@Value("${fabrick.api.get-account-balance}") String fabrickApiGetAccountBalance) {
		this.fabrickServiceClient = fabrickServiceClient;
		url = fabrickBaseUrl + fabrickApiGetAccountBalance;

	}

	private final FabrickServiceClient fabrickServiceClient;

	private final String url;
	
	@Override
	public AccountBalance getAccountBalance(String accountId) {
		
		log.info(" -> sending request to {} for accountId {}", url, accountId);

		FabrickApiResponse<AccountBalance> accountBalance = fabrickServiceClient.getAccountBalance(accountId);
		
		log.info(" <- response received from {} for accountId {}", url, accountId);
		
		if (accountBalance == null) {
			throw new FabrickException("Received unexpected response from " + url);
		}
		if (StringUtils.equals(accountBalance.getStatus(), "KO")) {
			throw new FabrickException("KO from " + url + " with following errors: " + accountBalance.getError());
		}
		
		return accountBalance.getPayload();

	}

}
