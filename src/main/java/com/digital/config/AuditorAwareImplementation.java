package com.digital.config;

import com.digital.facade.AuthenticationFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;

/**
 * Default JPA Auditor provider
 *
 * @author babacoul
 */
public class AuditorAwareImplementation implements AuditorAware<String> {

  private final AuthenticationFacade authenticationFacade;

  public AuditorAwareImplementation(AuthenticationFacade authenticationFacade) {
    this.authenticationFacade = authenticationFacade;
  }

  @Bean
  @Override public String getCurrentAuditor() {
    return authenticationFacade.getAuthenticatedUsername().orElse("Anonymous");
  }
}
