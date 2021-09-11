package com.digital.service;

import java.util.concurrent.Future;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * Process Thymeleaf template for mailing
 *
 * @author babacoul
 */
@Service
public class MailTemplateHandler {

  private final TemplateEngine templateEngine;

  @Autowired
  public MailTemplateHandler(TemplateEngine templateEngine) {
    this.templateEngine = templateEngine;
  }

  @Async
  public Future<String> build(String template, Context context) {
    return new AsyncResult<>(templateEngine.process(template, context));
  }
}
