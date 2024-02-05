package io.github.paulmarcelinbejan.fabrick.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.github.paulmarcelinbejan.fabrick.dto.FabrickApiResponse;
import io.github.paulmarcelinbejan.fabrick.dto.moneytransfer.request.MoneyTransferRequest;
import io.github.paulmarcelinbejan.fabrick.dto.moneytransfer.response.MoneyTransferResponse;
import io.github.paulmarcelinbejan.fabrick.exception.FabrickException;
import io.github.paulmarcelinbejan.fabrick.feign.FabrickServiceClient;
import io.github.paulmarcelinbejan.fabrick.service.MoneyTransferService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class MoneyTransferServiceImpl implements MoneyTransferService {

	public MoneyTransferServiceImpl(
			FabrickServiceClient fabrickServiceClient,
			@Value("${fabrick.base-url}") String fabrickBaseUrl,
			@Value("${fabrick.api.create-money-transfer}") String fabrickApiCreateMoneyTransfer) {
		this.fabrickServiceClient = fabrickServiceClient;
		url = fabrickBaseUrl + fabrickApiCreateMoneyTransfer;
	}
	
	private final FabrickServiceClient fabrickServiceClient;
	
	private final String url;

	@Override
	public MoneyTransferResponse createMoneyTransfer(String accountId, MoneyTransferRequest request) {
		
		log.info(" -> sending request to {} for accountId {}", url, accountId);
		
		FabrickApiResponse<MoneyTransferResponse> moneyTransferResponse = fabrickServiceClient
				.createMoneyTransfer(request, accountId);
		
		log.info(" <- response received from {} for accountId {}", url, accountId);
		
		if (moneyTransferResponse == null) {
			throw new FabrickException("Received unexpected response from " + url);
		}
		if (StringUtils.equals(moneyTransferResponse.getStatus(), "KO")) {
			throw new FabrickException("KO from " + url + " with following errors: " + moneyTransferResponse.getError());
		}

		return moneyTransferResponse.getPayload();
		
	}

}
