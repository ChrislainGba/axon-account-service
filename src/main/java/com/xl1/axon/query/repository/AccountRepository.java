package com.xl1.axon.query.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xl1.axon.query.entity.Account;

public interface AccountRepository extends JpaRepository<Account, String>{

}
