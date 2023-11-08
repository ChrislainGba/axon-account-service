package com.xl1.axon.commonapi.events;

import java.math.BigDecimal;

import com.xl1.axon.commonapi.enums.AccountStatus;

import lombok.Getter;

public class AccountCreatedEvent extends BaseEvent<String>{
	@Getter private String currency;
	@Getter private BigDecimal balance;
	@Getter private AccountStatus status;

	public AccountCreatedEvent(String id, String currency, BigDecimal balance, AccountStatus status) {
		super(id);
		this.currency = currency;
		this.balance = balance;
		this.status = status;
	}
	

}
