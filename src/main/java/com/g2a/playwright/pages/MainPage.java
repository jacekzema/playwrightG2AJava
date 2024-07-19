package com.g2a.playwright.pages;

import com.g2a.playwright.framework.ScreenshotHelper;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;

@Slf4j
public class MainPage extends BasePage {

  private static final String URL = "https://www.g2a.com/";

  public MainPage(Page page) {
    super(page);
  }

  @Override
  @Step("Check if main page is displayed")
  public boolean isAt() {
    await("Wait for main page").atMost(10, SECONDS).until(() -> searchInput.isVisible());
    return searchInput.isVisible();
  }

  @Step("Navigate to main page")
  public void navigate() {
    log.info("Navigating to {}", URL);
    page.navigate(URL);
    page.waitForLoadState();
    ScreenshotHelper.takeScreenshot(page);
  }
}
