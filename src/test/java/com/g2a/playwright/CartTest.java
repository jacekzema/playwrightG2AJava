package com.g2a.playwright;

import com.g2a.playwright.framework.BaseTest;
import com.g2a.playwright.pages.MainPage;
import com.g2a.playwright.pages.ProductDetailsPage;
import com.g2a.playwright.pages.SearchResultPage;
import com.g2a.playwright.pages.YourCartPage;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.FieldSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class CartTest extends BaseTest {

  static String searchText = System.getProperty("SEARCH_TEXT", "diablo 4 pc");
  static List<String> searchTextList = new ArrayList<>(Arrays.asList(searchText.split(",")));

  @ParameterizedTest
  @FieldSource("searchTextList")
  @DisplayName("Should price from search be equals to price in cart")
  @Description("This test verifies that the price of the product in the search results is the same as the price of the product in the cart.")
  void shouldPriceFromSearchBeEqualsToPriceInCart(String searchText) {

    MainPage mainPage = new MainPage(page);
    mainPage.navigate();
    Assertions.assertTrue(mainPage.isAt(), "Main page is not displayed");

    mainPage.acceptCookieBanner();
    mainPage.fillSearchInputAndClickSearch(searchText);

    SearchResultPage searchResultPage = new SearchResultPage(page);
    Assertions.assertTrue(searchResultPage.isAt(), "Search page is not displayed");
    searchResultPage.clickOnFirstListItem();

    ProductDetailsPage productDetailsPage = new ProductDetailsPage(page);
    Assertions.assertTrue(productDetailsPage.isAt(), "Product page is not displayed");
    String expectedProductPrice = productDetailsPage.getPrice();
    productDetailsPage.clickAddToCart();

    YourCartPage yourCartPage = new YourCartPage(page);
    Assertions.assertTrue(yourCartPage.isAt(), "Your cart page is not displayed");
    String totalProductPrice = yourCartPage.getTotalPrice();

    Assertions.assertEquals(expectedProductPrice, totalProductPrice, "Prices are not equal");
  }
}