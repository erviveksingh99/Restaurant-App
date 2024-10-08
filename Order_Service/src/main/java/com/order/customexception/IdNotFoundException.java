package com.order.customexception;

@SuppressWarnings("serial")
public class IdNotFoundException extends RuntimeException{
	public IdNotFoundException(String msg) {
		super(msg);
	}
}
