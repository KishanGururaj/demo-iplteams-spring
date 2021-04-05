package com.wolken.wolkenapp.exceptions;

import org.springframework.stereotype.Component;

@Component
public class IplException extends Exception {
	@Override
	public String getMessage() {
		return "into user defined exception";
	}

}
