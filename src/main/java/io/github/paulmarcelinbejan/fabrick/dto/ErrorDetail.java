package io.github.paulmarcelinbejan.fabrick.dto;

import lombok.Data;

@Data
public class ErrorDetail {

	private String code;

	private String description;

	private String params;

}
