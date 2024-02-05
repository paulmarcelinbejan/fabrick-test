package io.github.paulmarcelinbejan.fabrick.service;

import io.github.paulmarcelinbejan.fabrick.dto.accounttransactions.AccountTransactions;

public interface AccountTransactionsService {

	AccountTransactions getAccountTransactions(String accountId, String fromAccountingDate, String toAccountingDate);

}
