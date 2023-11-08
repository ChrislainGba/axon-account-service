package com.xl1.axon.command.aggregate;

import java.math.BigDecimal;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import com.xl1.axon.commonapi.commands.CreateAccountCommand;
import com.xl1.axon.commonapi.commands.CreditAccountCommand;
import com.xl1.axon.commonapi.commands.DebitAccountCommand;
import com.xl1.axon.commonapi.enums.AccountStatus;
import com.xl1.axon.commonapi.events.AccountCreatedEvent;
import com.xl1.axon.commonapi.events.AccountCreditedEvent;
import com.xl1.axon.commonapi.events.AccountDebitedEvent;
import com.xl1.axon.commonapi.exception.NegativeInitialBalanceException;
//define the courrent state of account need to be check before each change
//for each application start AccountAggregate state is set
@Aggregate
public class AccountAggregate {
	@AggregateIdentifier
	private String accountId;
	private String currency;
	private BigDecimal balance;
	private AccountStatus status;
	
	//required by Axon
	public AccountAggregate() {
		
	}
	
	
	//Decision function
	//it suscribe to the command bus to handler incomming createAccountCommand
	@CommandHandler
	public AccountAggregate(CreateAccountCommand command) {
		//verify business rules
		if(command.getInitialBalance().compareTo(BigDecimal.ZERO) < 0) {
			throw new NegativeInitialBalanceException("Negative balance");
		}
		
		AggregateLifecycle.apply(new AccountCreatedEvent(
				command.getId(),
				command.getCurrency(),
				command.getInitialBalance(),
				AccountStatus.CREATED
				)
			);
	}
	
	//Evolution function to mutate the application State
	//every time the is an event
	@EventSourcingHandler
	public void on(AccountCreatedEvent event) {
		this.accountId = event.getId();
		this.balance = event.getBalance();
		this.currency = event.getCurrency();
		this.status = event.getStatus();
	}
	
	@CommandHandler
	public void handle(CreditAccountCommand command) {
		if(command.getAmount().compareTo(BigDecimal.ZERO) < 0) {
			throw new NegativeInitialBalanceException("Negative amount");
		}
		
		AggregateLifecycle.apply(new AccountCreditedEvent(
				command.getId(), 
				command.getCurrency(), 
				command.getAmount()
		));
	}
	
	@EventSourcingHandler
	public void on(AccountCreditedEvent event) {
		this.balance.add(event.getAmount()); 
	}
	
	@CommandHandler
	public void handle(DebitAccountCommand command) {
		if(command.getAmount().compareTo(BigDecimal.ZERO) < 0) {
			throw new NegativeInitialBalanceException("Negative amount");
		}
		if(command.getAmount().compareTo(this.balance) >= 0) {
			throw new NegativeInitialBalanceException("balance insufficient");
		}
		
		AggregateLifecycle.apply(new AccountDebitedEvent(
				command.getId(), 
				command.getCurrency(), 
				command.getAmount()
		));
	}
	
	@EventSourcingHandler
	public void on(AccountDebitedEvent event) {
		this.balance.subtract(event.getAmount());	}
	
}
