package com.digital.controller;

/**
 * @author babacoul
 */
public abstract class BaseController {

  private final String templateBaseDir;

  public BaseController(String templateBaseDir) {
    this.templateBaseDir = templateBaseDir;
  }

  String forwardTo(String path) {
    return String.format("/%s/%s", templateBaseDir, path);
  }

  String redirectTo(String path) {
    return String.format("redirect:/%s/%s", templateBaseDir, path);
  }

  public String getTemplateBaseDir() {
    return templateBaseDir;
  }

  public String getRedirectBaseDir() {
    return String.format("redirect:/%s/", templateBaseDir);
  }
}
