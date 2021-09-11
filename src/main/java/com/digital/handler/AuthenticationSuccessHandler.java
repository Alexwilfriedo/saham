package com.digital.handler;

import com.digital.facade.AuthenticationFacade;
import com.digital.model.ConnectionEvent;
import com.digital.repository.ConnectionEventRepository;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * @author babacoul
 */
@Component
public class AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

  private final AuthenticationFacade authenticationFacade;

  private final ConnectionEventRepository connectionEventRepository;

  @Autowired
  public AuthenticationSuccessHandler(AuthenticationFacade authenticationFacade,
      ConnectionEventRepository connectionEventRepository) {
    this.authenticationFacade = authenticationFacade;
    this.connectionEventRepository = connectionEventRepository;
  }

  private final Logger LOGGER = LoggerFactory.getLogger(AuthenticationSuccessHandler.class);

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException, ServletException {
    super.onAuthenticationSuccess(request, response, authentication);

    authenticationFacade.getAuthenticatedUser()
        .ifPresent(user -> {
          Device device = (Device) request.getSession().getAttribute("device");
          String deviceName;

          if (device.isNormal()) {
            deviceName = "Browser";
          } else if (device.isMobile()) {
            deviceName = "Mobile Phone";
          } else {
            deviceName = "Tablet";
          }

          connectionEventRepository.save(
              new ConnectionEvent(user, user.getUsername(), request.getHeader("User-Agent"),
                  deviceName));
        });

    LOGGER.info(String.format("Authentication Success for user %s ", authentication.getName()));
  }
}
