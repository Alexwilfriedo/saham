package com.digital.service;

import com.digital.model.User;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

/**
 * {@link Service} that handles mail sending for account purpose
 *
 * @author babacoul
 */
@Service
public class AccountMailHandler implements IAccountMailHandler {

  private static final String TEMPLATE_BASE_DIR = "mail/%s";

  private final JavaMailSenderHandler javaMailSenderHandler;

  private final MailTemplateHandler mailTemplateHandler;

  private static String basePath = "";

  @Autowired
  public AccountMailHandler(JavaMailSenderHandler javaMailSenderHandler,
      MailTemplateHandler mailTemplateHandler) {
    this.javaMailSenderHandler = javaMailSenderHandler;
    this.mailTemplateHandler = mailTemplateHandler;
  }

  /**
   * Send welcome mail to the new registered {@link User}
   *
   * The mail handles the activation link
   *
   * @param user the new registered {@link User}
   * @param request the {@link HttpServletRequest} of the {@link Controller} who called the method
   */
  @Override
  public void sendRegistrationMail(final User user, final HttpServletRequest request) {
    final Context context = new Context();

    try {

      context.setVariable("userFullname",
          String.format("%s %s", user.getFirstname(), user.getLastname()));
     // context.setVariable("confirmationToken", user.getTokens().getAccountConfirmationToken());
      injectBasePath(context, request);

      Future<String> text =
          mailTemplateHandler.build(String.format(TEMPLATE_BASE_DIR, "sign_up"), context);

      javaMailSenderHandler.send(user.getEmail(),
          String.format("SWIK | Welcome %s", context.getVariables().get("userFullname")),
          text.get(),
          true);
    } catch (InterruptedException | ExecutionException | MessagingException e) {
      e.printStackTrace();
    }
  }

  /**
   * Send password reactivation link
   *
   * @param user the new register {@link User}
   * @param request the {@link HttpServletRequest} of the {@link Controller} who called the method
   */
  @Override
  public void sendForgotPasswordEmail(final User user, final HttpServletRequest request) {
    final Context context = new Context();

    try {

      context.setVariable("userFullname",
          String.format("%s %s", user.getFirstname(), user.getLastname()));
     // context.setVariable("confirmationToken", user.getTokens().getChangePasswordToken());
      injectBasePath(context, request);

      Future<String> text =
          mailTemplateHandler.build(String.format(TEMPLATE_BASE_DIR, "password_new"), context);

      javaMailSenderHandler.send(user.getEmail(),
          String.format("SWIK | Change Password %s", context.getVariables().get("userFullname")),
          text.get(),
          true);
    } catch (InterruptedException | ExecutionException | MessagingException e) {
      e.printStackTrace();
    }
  }

  /**
   * Send a mail to inform the user for a new connection
   *
   * @param user the new register {@link User}
   * @param request the {@link HttpServletRequest} of the {@link Controller} who called the method
   */
  @Override
  public void sendConnectionEmail(final User user, final HttpServletRequest request) {

  }

  private void injectBasePath(Context context, HttpServletRequest request) {
    Optional.ofNullable(basePath).
        filter(String::isEmpty)
        .ifPresent(s -> basePath =
            String.format("%s://%s:%s", request.getScheme(), request.getServerName(),
                request.getServerPort()));
    context.setVariable("basePath", basePath);
  }
}