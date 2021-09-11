package com.digital.service;

import com.digital.repository.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author babacoul
 */
@Service
public class UserDetailsServiceImplementation implements UserDetailsService {

  private final UserRepository userRepository;

  @Autowired
  public UserDetailsServiceImplementation(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override public UserDetails loadUserByUsername(String username)
      throws UsernameNotFoundException {

    return Optional.ofNullable(userRepository.findFirstByUsername(username))
        .orElseThrow(() -> new UsernameNotFoundException("Invalid username and password"));
  }
}
