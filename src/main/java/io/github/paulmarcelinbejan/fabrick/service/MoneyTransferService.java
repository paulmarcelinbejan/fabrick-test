package io.github.paulmarcelinbejan.fabrick.service;

import io.github.paulmarcelinbejan.fabrick.dto.moneytransfer.request.MoneyTransferRequest;
import io.github.paulmarcelinbejan.fabrick.dto.moneytransfer.response.MoneyTransferResponse;

public interface MoneyTransferService {

	MoneyTransferResponse createMoneyTransfer(String accountId, MoneyTransferRequest request);

}
