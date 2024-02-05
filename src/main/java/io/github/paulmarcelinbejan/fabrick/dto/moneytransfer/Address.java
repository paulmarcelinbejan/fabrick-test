package io.github.paulmarcelinbejan.fabrick.dto.moneytransfer;

import lombok.Data;

@Data
public class Address {

	private String address;

	private String city;

	// TODO validation countryCode compliant to ISO 3166-1 alpha 2 standard.
	private String countryCode;

}
