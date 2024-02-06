package io.github.paulmarcelinbejan.fabrick.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.paulmarcelinbejan.fabrick.dto.FabrickApiResponse;
import io.github.paulmarcelinbejan.fabrick.dto.accounttransactions.AccountTransactions;
import io.github.paulmarcelinbejan.fabrick.entity.Transaction;
import io.github.paulmarcelinbejan.fabrick.feign.FabrickServiceClient;
import io.github.paulmarcelinbejan.fabrick.mapper.TransactionMapper;
import io.github.paulmarcelinbejan.fabrick.service.AccountTransactionsService;
import io.github.paulmarcelinbejan.fabrick.service.TransactionService;
import io.github.paulmarcelinbejan.fabrick.utility.FabrickUtils;
import io.github.paulmarcelinbejan.toolbox.utils.log.audit.Audit;
import io.github.paulmarcelinbejan.toolbox.utils.log.duration.TimeExecution;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class AccountTransactionsServiceImpl implements AccountTransactionsService {

	public AccountTransactionsServiceImpl(
			TransactionService transactionService,
			TransactionMapper transactionMapper,
			FabrickServiceClient fabrickServiceClient,
			@Value("${fabrick.base-url}") String fabrickBaseUrl,
			@Value("${fabrick.api.get-account-transactions}") String fabrickApiGetAccountTransactions) {
		this.transactionService = transactionService;
		this.transactionMapper = transactionMapper;
		this.fabrickServiceClient = fabrickServiceClient;
		url = fabrickBaseUrl + fabrickApiGetAccountTransactions;
	}

	private final TransactionService transactionService;

	private final TransactionMapper transactionMapper;

	private final FabrickServiceClient fabrickServiceClient;
	
	private final String url;

	@Override
	@Audit
	@TimeExecution
	@Transactional
	public AccountTransactions getAccountTransactions(String accountId, String fromAccountingDate, String toAccountingDate) {

		log.info(" -> sending request to {} for accountId {}", url, accountId);

		FabrickApiResponse<AccountTransactions> accountTransactions = fabrickServiceClient
				.getAccountTransactions(accountId, fromAccountingDate, toAccountingDate);
		
		log.info(" <- response received from {} for accountId {}", url, accountId);
		
		FabrickUtils.throwFabrickExceptionForUnexpectedApiResponse(url, accountTransactions);
		FabrickUtils.throwFabrickExceptionForKO(url, accountTransactions);
		
		AccountTransactions payload = accountTransactions.getPayload();

		List<Transaction> transactionEntities = transactionMapper.toEntities(payload.getList());

		transactionService.saveTransactions(transactionEntities);

		return payload;
	}

}
