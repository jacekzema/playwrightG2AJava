package com.g2a.playwright.pages;

import com.microsoft.playwright.Page;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MainPage extends BasePage {

  private final String URL = "https://www.g2a.com/";

  public MainPage(Page page) {
    super(page);
  }

  @Override
  public boolean isAt() {
    page.waitForURL(URL);
    return false;
  }

  public void navigate() {
    log.info("Navigating to {}", URL);
    page.navigate(URL);
    page.waitForLoadState();
  }

}
