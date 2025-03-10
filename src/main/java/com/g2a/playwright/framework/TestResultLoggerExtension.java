package com.g2a.playwright.framework;

import com.microsoft.playwright.Page;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;
import org.junit.jupiter.api.extension.TestWatcher;

@Slf4j
public class TestResultLoggerExtension implements TestWatcher, TestExecutionExceptionHandler {

    @Override
    public void testSuccessful(ExtensionContext context) {
        closePage(context);
        log.info("Test Successfully Finished: {}", context.getDisplayName());
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        Object testInstance = context.getRequiredTestInstance();
        if (testInstance instanceof BaseTest) {
            Page page = ((BaseTest) testInstance).page;
            if (page != null && !page.isClosed()) {
                ScreenshotHelper.takeScreenshot(page);
                page.close();
            }
        }
        log.info("Test Failed: {}", context.getDisplayName());
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        closePage(context);
    }

    @Override
    public void handleTestExecutionException(ExtensionContext context, Throwable throwable) throws Throwable {
        log.info("Exception in test: {}", context.getDisplayName());
        throw throwable;
    }

    private void closePage(ExtensionContext context) {
        Object testInstance = context.getRequiredTestInstance();
        if (testInstance instanceof BaseTest) {
            Page page = ((BaseTest) testInstance).page;
            if (page != null && !page.isClosed()) {
                page.close();
            }
        }
    }
}