package io.github.paulmarcelinbejan.fabrick.exception;

public class FabrickException extends RuntimeException {

	private static final long serialVersionUID = -3495478373598160110L;

	public FabrickException(String message) {
		super(message);
	}

	public FabrickException(String message, Throwable cause) {
		super(message, cause);
	}

	public FabrickException(Throwable cause) {
		super(cause);
	}

}
