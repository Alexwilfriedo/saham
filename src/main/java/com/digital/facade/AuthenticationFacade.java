package com.digital.facade;

import com.digital.model.User;
import com.digital.repository.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * @author babacoul
 */
@Component
public class AuthenticationFacade implements IAuthenticationFacade {

  private final UserRepository userRepository;

  @Autowired
  public AuthenticationFacade(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override public Optional<User> getAuthenticatedUser() {
    User user = null;

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
      user = userRepository.findFirstByUsername(authentication.getName());
    }

    return Optional.ofNullable(user);
  }

  @Override public Optional<String> getAuthenticatedUsername() {
    String username = null;

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
      username = authentication.getName();
    }
    return Optional.ofNullable(username);
  }
}
