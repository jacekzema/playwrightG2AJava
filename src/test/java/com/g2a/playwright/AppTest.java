package com.g2a.playwright;

import com.g2a.playwright.framework.PlaywrightFactory;
import com.g2a.playwright.pages.MainPage;
import com.g2a.playwright.pages.ProductDetailsPage;
import com.g2a.playwright.pages.SearchResultPage;
import com.g2a.playwright.pages.YourCartPage;
import com.microsoft.playwright.Page;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@Slf4j
public class AppTest {


  Page page;
  PlaywrightFactory play;
  String searchText = "diablo 4";

  @BeforeEach
  public void setup() {
    play = new PlaywrightFactory();
    page = play.getPage("firefox");
    page.addInitScript("Object.defineProperty(navigator, 'webdriver', {get: () => undefined})");
  }

  @AfterEach
  public void tearDown() {
    page.context().browser().close();
  }

  @Test
  public void shouldAnswerWithTrue() {
    MainPage mainPage = new MainPage(page);
    mainPage.navigate();
    mainPage.isAt();
    mainPage.acceptCookieBanner();
    mainPage.fillSearchInputAndClickSearch(searchText);

    SearchResultPage searchResultPage = new SearchResultPage(page);
    searchResultPage.isAt();
    searchResultPage.clickOnFirstListItem();

    ProductDetailsPage productDetailsPage = new ProductDetailsPage(page);
    productDetailsPage.isAt();
    String expectedProductPrice = productDetailsPage.getPrice();
    productDetailsPage.clickAddToCart();

    YourCartPage yourCartPage = new YourCartPage(page);
    String totalProductPrice = yourCartPage.getTotalPrice();

    Assertions.assertEquals(expectedProductPrice, totalProductPrice);
  }
}


