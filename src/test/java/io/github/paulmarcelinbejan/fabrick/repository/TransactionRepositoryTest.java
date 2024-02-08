package io.github.paulmarcelinbejan.fabrick.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;

import io.github.paulmarcelinbejan.fabrick.entity.Transaction;
import io.github.paulmarcelinbejan.fabrick.enumeration.GBS_TRANSACTION_TYPE;

/**
 * Tests annotated with {@code @DataJpaTest} are transactional and roll back at the end of each test.
 * They also use an embedded in-memory database.
 */
@DataJpaTest
@ImportAutoConfiguration({ FeignAutoConfiguration.class })
class TransactionRepositoryTest {

	@Autowired
    private TransactionRepository transactionRepository;
	
    @Test
    @DisplayName("JUnit test for saveAll")
	void testSaveAll() {

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

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction1);
        transactions.add(transaction2);

		// When : action to test
		List<Transaction> transactionsSaved = transactionRepository.saveAll(transactions);

        // Then : Verify the output
		assertThat(transactionsSaved).hasSize(2);

    }
    
}
