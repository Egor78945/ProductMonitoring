package com.example.product_processor_service.service.web.playwright.browser;

import com.example.product_processor_service.service.web.playwright.browser.factory.PlaywrightWebBrowserContextFactory;
import com.microsoft.playwright.BrowserContext;
import org.springframework.stereotype.Service;

@Service
public class PlaywrightWebBrowserContextHolderManager extends PlaywrightWebBrowserContextHolder {
    protected PlaywrightWebBrowserContextHolderManager(PlaywrightWebBrowserContextFactory playwrightWebBrowserContextFactory) {
        super(playwrightWebBrowserContextFactory);
    }

    public BrowserContext getBrowserContext() {
        if(browserContext == null) {
            browserContext = webBrowserContextFactory.getWithDefaults();
        }
        return browserContext;
    }

    private void resetBrowserContext() {
        if (browserContext != null) {
            browserContext.close();
        }
        browserContext = webBrowserContextFactory.getWithDefaults();
    }
}
