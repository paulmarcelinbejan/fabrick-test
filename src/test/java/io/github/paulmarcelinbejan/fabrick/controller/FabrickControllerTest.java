package io.github.paulmarcelinbejan.fabrick.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import io.github.paulmarcelinbejan.fabrick.dto.moneytransfer.request.MoneyTransferRequest;
import io.github.paulmarcelinbejan.fabrick.utility.JacksonUtils;

@SpringBootTest
@AutoConfigureMockMvc
class FabrickControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void testLetturaSaldo() throws Exception {
		String accountId = "14537780";
		mockMvc.perform(MockMvcRequestBuilders.get("/lettura-saldo/{accountId}", accountId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}

	@Test
	void testEffettuaBonifico() throws Exception {
		String MONEY_TRANSFER_REQUEST_FOLDER = "./src/test/resources/request/money_transfer/";
		MoneyTransferRequest request = JacksonUtils.OBJECT_MAPPER.readValue(
				new File(MONEY_TRANSFER_REQUEST_FOLDER + "MoneyTransferRequest.json"), MoneyTransferRequest.class);
		String requestAsString = JacksonUtils.OBJECT_MAPPER.writeValueAsString(request);
		
		System.out.println(requestAsString);
		
		String accountId = "14537780";
		mockMvc.perform(MockMvcRequestBuilders
				.post("/effettua-bonifico/{accountId}", accountId)
				.content(JacksonUtils.OBJECT_MAPPER.writeValueAsString(request))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is4xxClientError());

	}

	@Test
	void testListaTransazioni() throws Exception {
		String accountId = "14537780";
		String fromAccountingDate = "2019-01-01";
		String toAccountingDate = "2019-12-01";

		mockMvc.perform(MockMvcRequestBuilders.get("/lista-transazioni/{accountId}", accountId)
				.param("fromAccountingDate", fromAccountingDate)
				.param("toAccountingDate", toAccountingDate))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}

}