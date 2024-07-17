package com.g2a.playwright.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import lombok.extern.slf4j.Slf4j;

import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@Slf4j
public class ProductDetailsPage extends BasePage {

  private final Pattern productDetailsURLPattern = Pattern.compile("search\\?query");
  Locator productPrice = page.locator("div[data-locator='product-info']").locator("span[data-locator='zth-price']").first();
  Locator addToCartButton = page.locator("div[data-locator='product-info']").locator("button[data-locator='ppa-payment__btn']");
  Locator availableInAnAccountPopup = page.locator("div[data-test-id='dialog-content']");
  Locator addToCartButtonInAvailableInAnAccountPopup = page.locator("button[data-test-id='primary-button']");

  public ProductDetailsPage(Page page) {
    super(page);
  }

  @Override
  public boolean isAt() {
    return productPrice.isVisible();
  }

  public String getPrice() {
    log.info("Getting item price");
    String price = productPrice.textContent();
    log.info("Price is: {}", price);
    return price;
  }

  public void clickAddToCart() {
    log.info("Clicking Add to Cart button");
    page.waitForTimeout(100);
    assertThat(addToCartButton).isVisible();
    addToCartButton.focus();
    addToCartButton.click();
    if (availableInAnAccountPopup.isVisible()) {
      assertThat(addToCartButtonInAvailableInAnAccountPopup).isVisible();
      addToCartButtonInAvailableInAnAccountPopup.click();
    }
  }
}


