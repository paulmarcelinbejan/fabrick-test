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
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;

import io.github.paulmarcelinbejan.fabrick.dto.FabrickApiResponse;
import io.github.paulmarcelinbejan.fabrick.dto.accountbalance.AccountBalance;
import io.github.paulmarcelinbejan.fabrick.feign.FabrickServiceClient;
import io.github.paulmarcelinbejan.fabrick.service.AccountBalanceService;
import io.github.paulmarcelinbejan.fabrick.utility.JacksonUtils;

import feign.FeignException;

@ExtendWith(MockitoExtension.class)
class AccountBalanceServiceImplTest {

    @Mock
    private FabrickServiceClient fabrickServiceClient;

    private static final String ACCOUNT_ID = "14537780";
    
    private static final String ACCOUNT_BALANCE_EXPECTED_OUTPUT_FOLDER = "./src/test/resources/expected_output/account_balance/";
    
    private static final TypeReference<FabrickApiResponse<AccountBalance>> FABRICK_API_RESPONSE_TYPE_REF = new TypeReference<>() {};  
    
    @Test
	@DisplayName("JUnit test to simulate AccountBalanceServiceImpl getAccountBalance without exceptions")
	void testAccountBalanceOK() throws StreamReadException, DatabindException, IOException {
    	
		// Read JSON file containing expected response
	    FabrickApiResponse<AccountBalance> expected_response = JacksonUtils.OBJECT_MAPPER.readValue(new File(ACCOUNT_BALANCE_EXPECTED_OUTPUT_FOLDER+"AccountBalanceOK.json"), FABRICK_API_RESPONSE_TYPE_REF);
		
	    // Mock fabrickServiceClient.getAccountBalance
		when(fabrickServiceClient.getAccountBalance(ACCOUNT_ID)).thenReturn(expected_response);

        // Create an instance of AccountBalanceServiceImpl
        String fabrickBaseUrl = "https://sandbox.platfr.io";
        String fabrickApiGetAccountBalance = "/api/gbs/banking/v4.0/accounts/{accountId}/balance";
        AccountBalanceService accountBalanceService = new AccountBalanceServiceImpl(fabrickServiceClient, fabrickBaseUrl, fabrickApiGetAccountBalance);

		// Test accountBalanceService.getAccountBalance
        AccountBalance actual = accountBalanceService.getAccountBalance(ACCOUNT_ID);

        // Verify interactions with fabrickServiceClient.getAccountBalance
        verify(fabrickServiceClient, times(1)).getAccountBalance(ACCOUNT_ID);
        
		Assertions.assertThat(actual).usingRecursiveComparison().isEqualTo(expected_response.getPayload());

    }
    
    @Test
	@DisplayName("JUnit test to simulate AccountBalanceServiceImpl getAccountBalance with exception")
	void testAccountBalanceKO() throws StreamReadException, DatabindException, IOException {
    	
	    // Mock fabrickServiceClient.getAccountBalance to throw exception when a wrong account id is passed as input
		when(fabrickServiceClient.getAccountBalance("14537780123")).thenThrow(FeignException.class);

        // Create an instance of AccountBalanceServiceImpl
        String fabrickBaseUrl = "https://sandbox.platfr.io";
        String fabrickApiGetAccountBalance = "/api/gbs/banking/v4.0/accounts/{accountId}/balance";
        AccountBalanceService accountBalanceService = new AccountBalanceServiceImpl(fabrickServiceClient, fabrickBaseUrl, fabrickApiGetAccountBalance);

		// Test accountBalanceService.getAccountBalance
        assertThrows(FeignException.class, () -> accountBalanceService.getAccountBalance("14537780123"));

        // Verify interactions with fabrickServiceClient.getAccountBalance
        verify(fabrickServiceClient, times(1)).getAccountBalance("14537780123");

    }
    
}
