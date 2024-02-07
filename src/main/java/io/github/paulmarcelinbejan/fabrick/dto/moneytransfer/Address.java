package io.github.paulmarcelinbejan.fabrick.dto.moneytransfer;

import jakarta.validation.constraints.Pattern;

import lombok.Data;

@Data
public class Address {

	private String address;

	private String city;

	@Pattern(regexp = "^[A-Z]{2}$", message = "Address.countryCode must be compliant with ISO 3166-1 alpha 2 standard")
	private String countryCode;

}
