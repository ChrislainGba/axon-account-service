package com.xl1.axon.query.service;

import java.util.List;

import javax.persistence.Transient;
import javax.transaction.Transactional;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.EventMessage;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import com.xl1.axon.commonapi.events.AccountCreatedEvent;
import com.xl1.axon.query.entity.Account;
import com.xl1.axon.query.queries.GetAllAccount;
import com.xl1.axon.query.repository.AccountRepository;
import com.xl1.axon.query.repository.AccountTransactionRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class AccountEventHandlerService {
	
	private AccountRepository accountRepository;
	private AccountTransactionRepository accountTransactionRepository;
	
	public AccountEventHandlerService(AccountRepository accountRepository,
			AccountTransactionRepository accountTransactionRepository) {
		super();
		this.accountRepository = accountRepository;
		this.accountTransactionRepository = accountTransactionRepository;
	}
	
	@EventHandler
	public void on(AccountCreatedEvent event, EventMessage<AccountCreatedEvent> eventMessage) {
		log.info("****************");
		log.info("AccountCreatedEvent received");
		Account account = new Account();
		account.setId(event.getId());
		account.setBalance(event.getBalance());
		account.setCurrency(event.getCurrency());
		account.setStatus(event.getStatus());
		//use eventMessage to get timeStame et payload instant on event
		account.setCreatedAt(eventMessage.getTimestamp());
		
		accountRepository.save(account);
	}
	
	@QueryHandler
	public List<Account> on(GetAllAccount query) {
		List<Account> list = accountRepository.findAll();
		return list;
	}
	
	

	
}
