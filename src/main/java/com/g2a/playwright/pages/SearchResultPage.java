package com.g2a.playwright.pages;

import com.g2a.playwright.framework.ScreenshotHelper;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;

import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;

@Slf4j
public class SearchResultPage extends BasePage {

  Locator firstSearchListItem = page.locator("div[class='indexes__ContentWrapper-wklrsw-113 bPsjms'] li").first();

  Pattern searchPagePattern = Pattern.compile("search\\?query=");

  public SearchResultPage(Page page) {
    super(page);
  }

  @Override
  @Step("Check if search result page is displayed")
  public boolean isAt() {
    page.waitForURL(searchPagePattern);
    await("Wait for search page").atMost(10, SECONDS).until(() -> firstSearchListItem.isVisible());
    return firstSearchListItem.isVisible();
  }

  @Step("Click on first search list item")
  public void clickOnFirstListItem() {
    log.info("Clicking on first search list item");
    assertThat(firstSearchListItem).isVisible();
    firstSearchListItem.focus();
    firstSearchListItem.click();
    ScreenshotHelper.takeScreenshot(page);
  }

}
