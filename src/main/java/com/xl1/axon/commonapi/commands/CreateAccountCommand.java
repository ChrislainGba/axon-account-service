package com.xl1.axon.commonapi.commands;

import java.math.BigDecimal;

import lombok.Getter;
//commands are imutables
public class CreateAccountCommand extends BaseCommand<String>{
	@Getter private String currency;
	@Getter private BigDecimal initialBalance;
	
	public CreateAccountCommand(String id, String currency, BigDecimal initialBalance) {
		super(id);
		this.currency = currency;
		this.initialBalance = initialBalance;
	}
	
	
}
