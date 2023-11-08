package com.xl1.axon.commonapi.commands;

import java.math.BigDecimal;

import lombok.Getter;
//commands are imutables
public class CreditAccountCommand extends BaseCommand<String>{
	@Getter private String currency;
	@Getter private BigDecimal amount;
	
	public CreditAccountCommand(String id, String currency, BigDecimal amount) {
		super(id);
		this.currency = currency;
		this.amount= amount;
	}
	
	
}
