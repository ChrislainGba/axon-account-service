package com.xl1.axon.query.controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xl1.axon.query.entity.Account;
import com.xl1.axon.query.queries.GetAllAccount;

@RestController
@RequestMapping("/query/count")
public class AcountQueryController {
	
	private QueryGateway queryGateway;
	
	public AcountQueryController(QueryGateway queryGateway) {
		super();
		this.queryGateway = queryGateway;
	}



	@GetMapping("/list")
	public CompletableFuture<List<Account>> accountList(){
		return queryGateway.query(new GetAllAccount(), ResponseTypes.multipleInstancesOf(Account.class));
		
	}
}
