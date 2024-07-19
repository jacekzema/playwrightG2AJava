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
public class ProductDetailsPage extends BasePage {

  Locator productPrice = page.locator("div[data-locator='product-info']").locator("span[data-locator='zth-price']").first();
  Locator addToCartButton = page.locator("div[data-locator='product-info']").locator("button[data-locator='ppa-payment__btn']");
  Locator availableInAnAccountPopup = page.locator("div[data-test-id='dialog-content']");
  Locator addToCartButtonInAvailableInAnAccountPopup = page.locator("button[data-test-id='primary-button']");

  public ProductDetailsPage(Page page) {
    super(page);
  }

  @Override
  @Step("Check if product details page is displayed")
  public boolean isAt() {
    await("Wait for product page").atMost(10, SECONDS).until(() -> productPrice.isVisible());
    return productPrice.isVisible();
  }

  @Step("Get product price")
  public String getPrice() {
    log.info("Getting item price");
    ScreenshotHelper.takeScreenshot(page);
    String price = productPrice.textContent();
    log.info("Price is: {}", price);
    return price;
  }

  @Step("Click Add to Cart button")
  public void clickAddToCart() {
    log.info("Clicking Add to Cart button");
    ScreenshotHelper.takeScreenshot(page);
    addToCartButton.waitFor();
    addToCartButton.focus();
    addToCartButton.evaluate("button => button.click()");
   // addToCartButton.click(new Locator.ClickOptions().setForce(true));
    if (availableInAnAccountPopup.isVisible()) {
      log.info("Available in an account popup is displayed");
      assertThat(addToCartButtonInAvailableInAnAccountPopup).isVisible();
      ScreenshotHelper.takeScreenshot(page);
      addToCartButtonInAvailableInAnAccountPopup.click();
    }
  }
}