package io.github.paulmarcelinbejan.fabrick.service;

import io.github.paulmarcelinbejan.fabrick.dto.accountbalance.AccountBalance;

public interface AccountBalanceService {

	AccountBalance getAccountBalance(String accountId);

}
