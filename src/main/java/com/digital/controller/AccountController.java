package com.digital.controller;

import com.digital.facade.AuthenticationFacade;
import com.digital.model.Branche;
import com.digital.model.User;

import java.util.List;
import java.util.Optional;

import com.digital.repository.BrancheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * @author babacoul
 */
@ControllerAdvice
@Controller
public class AccountController extends BaseController {

  private User user = null;

  final AuthenticationFacade authenticationFacade;

  public AccountController(String templateBaseDir,
      AuthenticationFacade authenticationFacade) {
    super(templateBaseDir);
    this.authenticationFacade = authenticationFacade;
  }

  @Autowired
  public AccountController(AuthenticationFacade authenticationFacade) {
    super("");
    this.authenticationFacade = authenticationFacade;
  }

  @ModelAttribute
  void injectUserModel(Model model) {

    user = user != null ? user : authenticationFacade.getAuthenticatedUser().orElse(null);

    Optional.ofNullable(user).ifPresent(u -> {
      user = u;
      model.addAttribute("userFullname",
          String.format("%s %s", u.getFirstname(), u.getLastname()));

      u.getRoles()
          .stream()
          .findFirst()
          .ifPresent(role -> model.addAttribute("userRole", role.getRole().toUpperCase()));
    });
  }


}
