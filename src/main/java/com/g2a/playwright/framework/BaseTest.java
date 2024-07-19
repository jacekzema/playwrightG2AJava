package com.g2a.playwright.framework;

import com.microsoft.playwright.Page;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

@Slf4j
@ExtendWith(TestResultLoggerExtension.class)
public class BaseTest {

  protected Page page;
  protected PlaywrightFactory play;

  static final String BROWSER = System.getProperty("BROWSER", "chrome").toLowerCase();

  @BeforeEach()
  public void setup() {
    play = new PlaywrightFactory();
    page = play.getPage(BROWSER);
    page.addInitScript("Object.defineProperty(navigator, 'webdriver', {get: () => undefined})");
  }
}