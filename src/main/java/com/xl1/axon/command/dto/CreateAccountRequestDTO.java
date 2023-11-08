package com.xl1.axon.command.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor @Builder
public class CreateAccountRequestDTO {
	private String currency;
	private BigDecimal initialBalance;

}
