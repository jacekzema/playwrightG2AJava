package com.g2a.playwright.pages;

import com.g2a.playwright.framework.ScreenshotHelper;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;

@Slf4j
public class YourCartPage extends BasePage {

  Locator totalPriceSpan = page.locator("div[class='indexes__Root1-tx0lkb-1 jrlmPp']").locator("span[data-locator='zth-price']").first();

  public YourCartPage(Page page) {
    super(page);
  }

  @Override
  @Step("Check if your cart page is displayed")
  public boolean isAt() {
    await("Wait for cart page").atMost(10, SECONDS).until(() -> totalPriceSpan.isVisible());
    return totalPriceSpan.isVisible();
  }

  @Step("Get total price")
  public String getTotalPrice() {
    log.info("Getting total price");
    assertThat(totalPriceSpan).isVisible();
    ScreenshotHelper.takeScreenshot(page);
    String price = totalPriceSpan.textContent();
    log.info("Total price is: {}", price);
    return price;
  }
}
