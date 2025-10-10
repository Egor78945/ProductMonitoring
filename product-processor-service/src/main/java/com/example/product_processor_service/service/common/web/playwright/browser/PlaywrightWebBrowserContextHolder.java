package com.example.product_processor_service.service.common.web.playwright.browser;

import com.example.product_processor_service.service.common.web.playwright.browser.factory.PlaywrightWebBrowserContextFactory;
import com.microsoft.playwright.BrowserContext;

import javax.annotation.PostConstruct;

public abstract class PlaywrightWebBrowserContextHolder {
    protected final PlaywrightWebBrowserContextFactory webBrowserContextFactory;
    protected BrowserContext browserContext;

    protected PlaywrightWebBrowserContextHolder(PlaywrightWebBrowserContextFactory webBrowserContextFactory) {
        this.webBrowserContextFactory = webBrowserContextFactory;
    }

    public abstract BrowserContext getBrowserContext();

    @PostConstruct
    public void initBrowserContext() {
        this.browserContext = webBrowserContextFactory.getWithDefaults();
    }
}
