package io.github.paulmarcelinbejan.fabrick.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.paulmarcelinbejan.fabrick.entity.Transaction;
import io.github.paulmarcelinbejan.fabrick.repository.TransactionRepository;
import io.github.paulmarcelinbejan.fabrick.service.TransactionService;
import io.github.paulmarcelinbejan.toolbox.utils.log.duration.TimeExecution;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

	private final TransactionRepository transactionRepository;

	@Override
	@TimeExecution
	@Transactional
	public List<Transaction> saveTransactions(List<Transaction> transactions) {

		log.info(" -> saving 'Transaction' entities into DB");

		transactions = transactionRepository.saveAll(transactions);

		log.info(" <- saved {} 'Transaction' entities into DB", transactions.size());

		return transactions;

	}

}
