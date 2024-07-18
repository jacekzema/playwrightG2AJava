package com.g2a.playwright.framework;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlaywrightFactory {

  final String headless = System.getProperty("HEADLESS", "false").toLowerCase();
  protected Page page;
  Playwright playwright;
  Browser browser;
  BrowserContext browserContext;
  BrowserType.LaunchOptions launchOptions;

  private List<String> options = new ArrayList<>(Arrays.asList(
      "--disable-gpu",
      "--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.110 Safari/537.36",
      "--disable-blink-features=AutomationControlled",
      "--useAutomationExtension=false"));

  public Page getPage(String browserType) {

    if (headless.equals("true")) {
      options.add("--headless=new");
    }

    playwright = Playwright.create();
    launchOptions = new BrowserType.LaunchOptions()
        .setHeadless(false)
        .setSlowMo(3)
        .setArgs(options);

    switch (browserType) {
      case "firefox":
        browser = playwright.firefox()
            .launch(launchOptions);
        break;
      case "safari":
        browser = playwright.webkit()
            .launch(launchOptions);
        break;
      default:
        browser = playwright.chromium()
            .launch(launchOptions);
        break;
    }
    browserContext = browser.newContext();
    page = browserContext.newPage();
    return page;
  }
}
