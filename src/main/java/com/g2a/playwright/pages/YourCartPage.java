package com.g2a.playwright.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import lombok.extern.slf4j.Slf4j;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@Slf4j
public class YourCartPage extends BasePage {

  Locator totalPriceSpan = page.locator("div[class='indexes__Root1-tx0lkb-1 jrlmPp']").locator("span[data-locator='zth-price']").first();

  public YourCartPage(Page page) {
    super(page);
  }

  @Override
  public boolean isAt() {
    return false;
  }

  public String getTotalPrice() {
    log.info("Getting total price");
    assertThat(totalPriceSpan).isVisible();
    String price = totalPriceSpan.textContent();
    log.info("Total price is: {}", price);
    return price;
  }

}
