package io.github.paulmarcelinbejan.fabrick.service.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;

import io.github.paulmarcelinbejan.fabrick.dto.FabrickApiResponse;
import io.github.paulmarcelinbejan.fabrick.dto.accounttransactions.AccountTransactions;
import io.github.paulmarcelinbejan.fabrick.feign.FabrickServiceClient;
import io.github.paulmarcelinbejan.fabrick.mapper.TransactionMapper;
import io.github.paulmarcelinbejan.fabrick.service.AccountTransactionsService;
import io.github.paulmarcelinbejan.fabrick.service.TransactionService;
import io.github.paulmarcelinbejan.fabrick.utility.JacksonUtils;

import feign.FeignException;

@SpringBootTest
class AccountTransactionsServiceImplTest {

    @Mock
    private FabrickServiceClient fabrickServiceClient;

	@Autowired
	private TransactionService transactionService;

	@Autowired
	private TransactionMapper transactionMapper;

    private static final String ACCOUNT_ID = "14537780";
    private static final String FROM_ACCOUNTING_DATE = "2019-01-01";
    private static final String TO_ACCOUNTING_DATE = "2019-12-01";
    
    private static final String ACCOUNT_TRANSACTIONS_EXPECTED_OUTPUT_FOLDER = "./src/test/resources/expected_output/account_transactions/";
    
    private static final TypeReference<FabrickApiResponse<AccountTransactions>> FABRICK_API_RESPONSE_TYPE_REF = new TypeReference<>() {};  
    
    @Test
	@Transactional
	@DisplayName("JUnit test to simulate AccountTransactionsServiceImpl getAccountTransactions without exceptions")
	void testAccountTransactionsOK() throws StreamReadException, DatabindException, IOException {
    	
		// Read JSON file containing expected response
	    FabrickApiResponse<AccountTransactions> expected_response = JacksonUtils.OBJECT_MAPPER.readValue(new File(ACCOUNT_TRANSACTIONS_EXPECTED_OUTPUT_FOLDER+"AccountTransactionsOK.json"), FABRICK_API_RESPONSE_TYPE_REF);
		
		// Mock fabrickServiceClient.getAccountTransactions
		when(fabrickServiceClient.getAccountTransactions(ACCOUNT_ID, FROM_ACCOUNTING_DATE, TO_ACCOUNTING_DATE)).thenReturn(expected_response);

        // Create an instance of AccountTransactionsServiceImpl
        String fabrickBaseUrl = "https://sandbox.platfr.io";
        String fabrickApiGetAccountTransactions = "/api/gbs/banking/v4.0/accounts/{accountId}/transactions";
		AccountTransactionsService accountTransactionsService = new AccountTransactionsServiceImpl(
				transactionService, 
				transactionMapper, 
				fabrickServiceClient, 
				fabrickBaseUrl,
				fabrickApiGetAccountTransactions);

		// Test accountTransactionsService.getAccountTransactions
        AccountTransactions actual = accountTransactionsService.getAccountTransactions(ACCOUNT_ID, FROM_ACCOUNTING_DATE, TO_ACCOUNTING_DATE);

        // Verify interactions with fabrickServiceClient.getAccountTransactions
        verify(fabrickServiceClient, times(1)).getAccountTransactions(ACCOUNT_ID, FROM_ACCOUNTING_DATE, TO_ACCOUNTING_DATE);
        
		Assertions.assertThat(actual).usingRecursiveComparison().isEqualTo(expected_response.getPayload());

    }
    
    @Test
	@DisplayName("JUnit test to simulate AccountTransactionsServiceImpl getAccountTransactions with exception")
	void testAccountTransactionsKO() throws StreamReadException, DatabindException, IOException {
    	
	    // Mock fabrickServiceClient.getAccountTransactions to throw exception when a fromAccountingDate is after toAccountingDate
		when(fabrickServiceClient.getAccountTransactions(ACCOUNT_ID, "2024-01-01", "2023-01-01")).thenThrow(FeignException.class);

        // Create an instance of AccountTransactionsServiceImpl
        String fabrickBaseUrl = "https://sandbox.platfr.io";
        String fabrickApiGetAccountTransactions = "/api/gbs/banking/v4.0/accounts/{accountId}/transactions";
		AccountTransactionsService accountTransactionsService = new AccountTransactionsServiceImpl(
				transactionService, 
				transactionMapper, 
				fabrickServiceClient, 
				fabrickBaseUrl,
				fabrickApiGetAccountTransactions);
        
		// Test accountTransactionsService.getAccountTransactions
        assertThrows(FeignException.class, () -> accountTransactionsService.getAccountTransactions(ACCOUNT_ID, "2024-01-01", "2023-01-01"));

		// Verify interactions with fabrickServiceClient.getAccountTransactions
		verify(fabrickServiceClient, times(1)).getAccountTransactions(ACCOUNT_ID, "2024-01-01", "2023-01-01");

    }
    
}
