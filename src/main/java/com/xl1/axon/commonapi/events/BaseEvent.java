package com.xl1.axon.commonapi.events;

import lombok.Getter;

public class BaseEvent<T> {
	@Getter private T id;

	public BaseEvent(T id) {
		super();
		this.id = id;
	}
	
	
}
