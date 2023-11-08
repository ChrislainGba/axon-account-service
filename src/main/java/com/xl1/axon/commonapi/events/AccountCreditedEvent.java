package com.xl1.axon.commonapi.events;

import java.math.BigDecimal;

import com.xl1.axon.commonapi.enums.AccountStatus;

import lombok.Getter;

public class AccountCreditedEvent extends BaseEvent<String>{
	@Getter private String currency;
	@Getter private BigDecimal amount;

	public AccountCreditedEvent(String id, String currency, BigDecimal amount) {
		super(id);
		this.currency = currency;
		this.amount = amount;
	}
	

}
