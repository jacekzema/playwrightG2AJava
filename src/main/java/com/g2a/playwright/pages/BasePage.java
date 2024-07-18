package com.g2a.playwright.pages;

import com.g2a.playwright.framework.ScreenshotHelper;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@Slf4j
public abstract class BasePage {

  protected Page page;
  Locator cookiePopup;
  Locator searchInput;
  Locator searchButton;

  protected BasePage(Page page) {
    this.page = page;
    cookiePopup = page.locator("div[data-test-id='cookie-modal']");
    searchInput = page.locator("input[type='search']");
    searchButton = page.locator("svg[class='search_icon']");
  }

  public abstract boolean isAt();

  @Step("Accept cookie banner")
  public void acceptCookieBanner() {
    log.info("Accepting cookie banner");
    assertThat(cookiePopup).isVisible();
    cookiePopup.locator("button[data-test-id='cookie-accept-all-btn']").click();
    ScreenshotHelper.takeScreenshot(page);
  }

  @Step("Fill search input with text: {searchText} and click search button")
  public void fillSearchInputAndClickSearch(String searchText) {
    log.info("Filling search input with text: {}", searchText);
    assertThat(searchInput).isVisible();
    searchInput.pressSequentially(searchText, new Locator.PressSequentiallyOptions().setDelay(100));
    ScreenshotHelper.takeScreenshot(page);
    log.info("Clicking search button");
    searchButton.click();
    ScreenshotHelper.takeScreenshot(page);
  }

}
