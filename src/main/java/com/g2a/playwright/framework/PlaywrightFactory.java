package com.g2a.playwright.framework;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import java.util.Arrays;

public class PlaywrightFactory {

  protected Page page;
  Playwright playwright;
  Browser browser;
  BrowserContext browserContext;

  public Page getPage(String browserType) {
    playwright = Playwright.create();
    switch (browserType) {
      case "chrome":
        browser = playwright.chromium()
            .launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));
        break;
      case "firefox":
        browser = playwright.firefox()
            .launch(new BrowserType.LaunchOptions()
                .setHeadless(false)
                .setArgs(Arrays.asList("--viewport-size==1920,1080",
                    // "--headless=new",
                    "--disable-gpu",
                    "--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.110 Safari/537.36",
                    "--disable-blink-features=AutomationControlled",
                    "--useAutomationExtension=false"))
            );
        break;
      case "safari":
        browser = playwright.webkit()
            .launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));
        break;
      default:
        browser = playwright.chromium()
            .launch(new BrowserType.LaunchOptions()
                .setChannel("chrome")
                .setSlowMo(3)
                .setHeadless(false)
                .setArgs(Arrays.asList("--viewport-size==1920,1080",
                    // "--headless=new",
                    "--disable-gpu",
                    "--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.110 Safari/537.36",
                    "--disable-blink-features=AutomationControlled",
                    "--useAutomationExtension=false"))
            );
        break;
    }
    browserContext = browser.newContext();
    page = browserContext.newPage();
    return page;
  }

}
