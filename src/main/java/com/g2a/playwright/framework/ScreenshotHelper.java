package com.g2a.playwright.framework;

import com.microsoft.playwright.Page;
import io.qameta.allure.Attachment;

public class ScreenshotHelper {

  private ScreenshotHelper() {
    throw new IllegalStateException("Utility class");
  }

  @Attachment(value = "Screenshot", type = "image/png")
  public static byte[] takeScreenshot(Page page) {
    return page.screenshot();
  }
}
