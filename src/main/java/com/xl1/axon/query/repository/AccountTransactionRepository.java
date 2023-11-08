package com.xl1.axon.query.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xl1.axon.query.entity.AccountTransaction;

public interface AccountTransactionRepository extends JpaRepository<AccountTransaction, Long>{

}
