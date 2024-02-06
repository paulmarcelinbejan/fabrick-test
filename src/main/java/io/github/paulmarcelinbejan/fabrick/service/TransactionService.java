package io.github.paulmarcelinbejan.fabrick.service;

import java.util.List;

import io.github.paulmarcelinbejan.fabrick.entity.Transaction;

public interface TransactionService {

	List<Transaction> saveTransactions(List<Transaction> transactions);

}
