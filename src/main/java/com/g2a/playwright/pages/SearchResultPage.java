package com.g2a.playwright.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import lombok.extern.slf4j.Slf4j;

import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@Slf4j
public class SearchResultPage extends BasePage {

  Locator firstSearchListItem = page.locator("div[class='indexes__ContentWrapper-wklrsw-113 bPsjms'] li").first();

  Pattern searchPagePattern = Pattern.compile("search\\?query=");

  public SearchResultPage(Page page) {
    super(page);
  }

  @Override
  public boolean isAt() {
    page.waitForURL(searchPagePattern);
    return firstSearchListItem.isVisible();
  }

  public void clickOnFirstListItem() {
    log.info("Clicking on first search list item");
    assertThat(firstSearchListItem).isVisible();
    firstSearchListItem.focus();
    firstSearchListItem.click();
  }

}
