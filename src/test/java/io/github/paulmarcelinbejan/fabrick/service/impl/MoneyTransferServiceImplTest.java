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
import io.github.paulmarcelinbejan.fabrick.dto.moneytransfer.request.MoneyTransferRequest;
import io.github.paulmarcelinbejan.fabrick.dto.moneytransfer.response.MoneyTransferResponse;
import io.github.paulmarcelinbejan.fabrick.feign.FabrickServiceClient;
import io.github.paulmarcelinbejan.fabrick.service.MoneyTransferService;
import io.github.paulmarcelinbejan.fabrick.utility.JacksonUtils;

import feign.FeignException;

@ExtendWith(MockitoExtension.class)
class MoneyTransferServiceImplTest {

    @Mock
    private FabrickServiceClient fabrickServiceClient;

    private static final String ACCOUNT_ID = "14537780";
    
    private static final String MONEY_TRANSFER_REQUEST_FOLDER = "./src/test/resources/request/money_transfer/";
    
    private static final String MONEY_TRANSFER_EXPECTED_OUTPUT_FOLDER = "./src/test/resources/expected_output/money_transfer/";
    
    private static final TypeReference<FabrickApiResponse<MoneyTransferResponse>> FABRICK_API_RESPONSE_TYPE_REF = new TypeReference<>() {};  
    
    @Test
	@DisplayName("JUnit test to simulate MoneyTransferServiceImpl createMoneyTransfer without exceptions")
	void testMoneyTransferOK() throws StreamReadException, DatabindException, IOException {

		// Read JSON file containing request
		MoneyTransferRequest request = JacksonUtils.OBJECT_MAPPER.readValue(new File(MONEY_TRANSFER_REQUEST_FOLDER + "MoneyTransferRequest.json"), MoneyTransferRequest.class);

		// Read JSON file containing response
		FabrickApiResponse<MoneyTransferResponse> expected_response = JacksonUtils.OBJECT_MAPPER.readValue(new File(MONEY_TRANSFER_EXPECTED_OUTPUT_FOLDER + "MoneyTransferOK.json"), FABRICK_API_RESPONSE_TYPE_REF);
		
		// Mock fabrickServiceClient.createMoneyTransfer to throw exception
		when(fabrickServiceClient.createMoneyTransfer(request, ACCOUNT_ID)).thenReturn(expected_response);

		// Create an instance of MoneyTransferServiceImpl
		String fabrickBaseUrl = "https://sandbox.platfr.io";
		String fabrickApiCreateMoneyTransfer = "/api/gbs/banking/v4.0/accounts/{accountId}/payments/money-transfers";
		MoneyTransferService moneyTransferService = new MoneyTransferServiceImpl(fabrickServiceClient, fabrickBaseUrl, fabrickApiCreateMoneyTransfer);

		// Test moneyTransferService.createMoneyTransfer
		MoneyTransferResponse actual = moneyTransferService.createMoneyTransfer(ACCOUNT_ID, request);

		// Verify interactions with fabrickServiceClient.createMoneyTransfer
        verify(fabrickServiceClient, times(1)).createMoneyTransfer(request, ACCOUNT_ID);
        
		Assertions.assertThat(actual).usingRecursiveComparison().isEqualTo(expected_response.getPayload());


	}

	@Test
	@DisplayName("JUnit test to simulate MoneyTransferServiceImplTest createMoneyTransfer with exception")
	void testMoneyTransferKO() throws StreamReadException, DatabindException, IOException {
    	
		// Read JSON file containing request
	    MoneyTransferRequest request = JacksonUtils.OBJECT_MAPPER.readValue(new File(MONEY_TRANSFER_REQUEST_FOLDER+"MoneyTransferRequest.json"), MoneyTransferRequest.class);
    	
		// Mock fabrickServiceClient.createMoneyTransfer
		when(fabrickServiceClient.createMoneyTransfer(request, ACCOUNT_ID)).thenThrow(FeignException.class);

		// Create an instance of MoneyTransferServiceImpl
        String fabrickBaseUrl = "https://sandbox.platfr.io";
        String fabrickApiCreateMoneyTransfer = "/api/gbs/banking/v4.0/accounts/{accountId}/payments/money-transfers";
        MoneyTransferService moneyTransferService = new MoneyTransferServiceImpl(fabrickServiceClient, fabrickBaseUrl, fabrickApiCreateMoneyTransfer);

		// Test moneyTransferService.createMoneyTransfer
        assertThrows(FeignException.class, () -> moneyTransferService.createMoneyTransfer(ACCOUNT_ID, request));

		// Verify interactions with fabrickServiceClient.createMoneyTransfer
        verify(fabrickServiceClient, times(1)).createMoneyTransfer(request, ACCOUNT_ID);

    }
    
}
