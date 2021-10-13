package com.example.springsecurity.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class ErrorDetails {
	private Date timestamp;
	private String errorCode;
	private String message;
}
