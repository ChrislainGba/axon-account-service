package com.xl1.axon.command.controller;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xl1.axon.command.dto.CreateAccountRequestDTO;
import com.xl1.axon.command.dto.CreditAccountRequestDTO;
import com.xl1.axon.command.dto.DebitAccountRequestDTO;
import com.xl1.axon.commonapi.commands.CreateAccountCommand;
import com.xl1.axon.commonapi.commands.CreditAccountCommand;
import com.xl1.axon.commonapi.commands.DebitAccountCommand;

@RestController
@RequestMapping("/commands/account")
public class AccountCommandController {
	
	private CommandGateway commandGateway;
	
	private EventStore eventStore;
	
	
	public AccountCommandController(CommandGateway commandGateway, EventStore eventStore) {
		this.commandGateway = commandGateway;
		this.eventStore = eventStore;
	}



	@PostMapping("/create")
	public CompletableFuture<String> createAccount(@RequestBody CreateAccountRequestDTO request) {
		return commandGateway.send(new CreateAccountCommand(
				UUID.randomUUID().toString(),
				request.getCurrency(),
				request.getInitialBalance()
		));
	}
	
	@PostMapping("/debit")
	public CompletableFuture<String> debitAccount(@RequestBody DebitAccountRequestDTO request) {
		return commandGateway.send(new DebitAccountCommand(
				request.getAccountId(),
				request.getCurrency(),
				request.getAmount()
		));
	}
	
	@PostMapping("/credit")
	public CompletableFuture<String> creditAccount(@RequestBody CreditAccountRequestDTO request) {
		return commandGateway.send(new CreditAccountCommand(
				request.getAccountId(),
				request.getCurrency(),
				request.getAmount()
		));
	}
	
	@GetMapping("/eventStore/{id}")
	public Stream eventStore(@PathVariable String id) {
		return eventStore.readEvents(id).asStream();
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> exceptionHandler(Exception e) {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
