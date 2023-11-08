package com.xl1.axon.query.entity;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.xl1.axon.commonapi.enums.AccountStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Account {
	@Id
	private String id;
	private BigDecimal balance;
	private Instant createdAt;
	private AccountStatus status;
	private String currency;
	@OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
	private List<AccountTransaction> transactions;

}
