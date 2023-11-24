package com.ssafy.trip.domain.user.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Email {
	private String receiver;
	private String subject;
	private String content;

	public static Email of(String receiver, String subject, String content) {
		return new Email(receiver, subject, content);
	}
}
