package com.xl1.axon.command.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor @Builder
public class CreditAccountRequestDTO {
	private String accountId;
	private String currency;
	private BigDecimal amount;
}
