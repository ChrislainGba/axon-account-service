package com.xl1.axon.commonapi.exception;

public class NegativeInitialBalanceException extends RuntimeException {
	public NegativeInitialBalanceException(String message) {
		super(message);
	}
}
