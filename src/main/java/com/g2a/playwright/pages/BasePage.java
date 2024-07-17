package com.g2a.playwright.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import lombok.extern.slf4j.Slf4j;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@Slf4j
public abstract class BasePage {

  protected Page page;
  Locator cookiePopup;
  Locator searchInput;
  Locator searchButton;

  public BasePage(Page page) {
    this.page = page;
    cookiePopup = page.locator("div[data-test-id='cookie-modal']");
    searchInput = page.locator("input[type='search']");
    searchButton = page.locator("svg[class='search_icon']");
  }

  public abstract boolean isAt();

  public void acceptCookieBanner() {
    log.info("Accepting cookie banner");
    assertThat(cookiePopup).isVisible();
    cookiePopup.locator("button[data-test-id='cookie-accept-all-btn']").click();
  }

  public void fillSearchInputAndClickSearch(String searchText) {
    log.info("Filling search input with text: {}", searchText);
    assertThat(searchInput).isVisible();
    searchInput.pressSequentially(searchText, new Locator.PressSequentiallyOptions().setDelay(100));
    log.info("Clicking search button");
    searchButton.click();
  }

}
