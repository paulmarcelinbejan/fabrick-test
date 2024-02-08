package io.github.paulmarcelinbejan.fabrick.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import io.github.paulmarcelinbejan.fabrick.entity.Transaction;
import io.github.paulmarcelinbejan.fabrick.enumeration.GBS_TRANSACTION_TYPE;
import io.github.paulmarcelinbejan.fabrick.repository.TransactionRepository;

@ExtendWith(MockitoExtension.class)
class TransactionServiceImplTest {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
	private TransactionServiceImpl transactionService;

    @Test
	@DisplayName("JUnit test for saveTransactions")
	void testSaveTransactions() {

        Transaction transaction1 = Transaction.builder()
        		.transactionId("282831")
				.operationId("00000000282831")
				.accountingDate("2019-11-29")
				.valueDate("2019-12-01")
				.type(GBS_TRANSACTION_TYPE.GBS_ACCOUNT_TRANSACTION_TYPE_0050)
				.amount(new BigDecimal("-343.77"))
				.currency("EUR")
				.description("PD VISA CORPORATE 10")
				.build();
        
        Transaction transaction2 = Transaction.builder()
        		.transactionId("1460159524001")
				.operationId("19000191134336")
				.accountingDate("2019-11-11")
				.valueDate("2019-11-09")
				.type(GBS_TRANSACTION_TYPE.GBS_ACCOUNT_TRANSACTION_TYPE_0010)
				.amount(new BigDecimal("854"))
				.currency("EUR")
				.description("BD LUCA TERRIBILE        DA 03268.49130         DATA ORDINE 09112019 COPERTURA VISA")
				.build();
        
        List<Transaction> transactionsToSave = new ArrayList<>();
        transactionsToSave.add(transaction1);
        transactionsToSave.add(transaction2);

        List<Transaction> savedTransactions = new ArrayList<>(transactionsToSave);

        // Mock transactionRepository.saveAll
        when(transactionRepository.saveAll(transactionsToSave)).thenReturn(savedTransactions);

		// Test saveTransactions
        List<Transaction> result = transactionService.saveTransactions(transactionsToSave);

        // Verify interactions and assertions
        verify(transactionRepository, times(1)).saveAll(transactionsToSave);
        assertEquals(savedTransactions.size(), result.size());
        
    }
    
}
