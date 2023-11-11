package com.ssafy.trip.domain.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class HtmlTemplate {
	// TemplateEngine을 이용할 경우 @Mock 사용시 NPE 발생
	private final ITemplateEngine templateEngine;

	public String build(final String template, final Map<String, Object> contents) {
		Context context = new Context();

		context.setVariables(contents);

		log.info("=========== templateEngine class: {}", templateEngine.getClass()); // SpringTemplateEngine

		return templateEngine.process(template, context);
	}
}
