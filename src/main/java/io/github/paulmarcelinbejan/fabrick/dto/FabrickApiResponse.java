package io.github.paulmarcelinbejan.fabrick.dto;

import java.util.List;

import lombok.Data;

@Data
public class FabrickApiResponse<T> {

	private String status;

	private List<ErrorDetail> errors;

	private T payload;

}