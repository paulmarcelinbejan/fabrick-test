package io.github.paulmarcelinbejan.fabrick.dto.moneytransfer.response;

import io.github.paulmarcelinbejan.fabrick.dto.moneytransfer.Account;

import lombok.Data;

@Data
public class Debtor {

	private String name;

	private Account account;

}
