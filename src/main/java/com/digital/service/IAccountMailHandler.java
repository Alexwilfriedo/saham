package com.digital.service;

import com.digital.model.User;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;

/**
 * @author babacoul
 */
public interface IAccountMailHandler {

  /**
   * Send welcome mail to the new registered {@link User}
   *
   * The mail handles the activation link
   *
   * @param user the new registered {@link User}
   * @param request the {@link HttpServletRequest} of the {@link Controller} who called the method
   */
  void sendRegistrationMail(final User user, final HttpServletRequest request);

  /**
   * Send password reactivation link
   *
   * @param user the new register {@link User}
   * @param request the {@link HttpServletRequest} of the {@link Controller} who called the method
   */
  void sendForgotPasswordEmail(final User user, final HttpServletRequest request);

  /**
   * Send a mail to inform the user for a new connection
   *
   * @param user the new register {@link User}
   * @param request the {@link HttpServletRequest} of the {@link Controller} who called the method
   */
  void sendConnectionEmail(final User user, final HttpServletRequest request);
}
