package com.digital.facade;

import com.digital.model.User;
import java.util.Optional;

/**
 * @author babacoul
 */
public interface IAuthenticationFacade {

  Optional<User> getAuthenticatedUser();

  Optional<String> getAuthenticatedUsername();
}
