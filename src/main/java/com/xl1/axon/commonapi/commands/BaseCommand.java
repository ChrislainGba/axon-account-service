package com.xl1.axon.commonapi.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Getter;

public abstract class BaseCommand <T> {
	@TargetAggregateIdentifier
	@Getter
	private T id;

	public BaseCommand(T id) {
		super();
		this.id = id;
	}
	
	
}
