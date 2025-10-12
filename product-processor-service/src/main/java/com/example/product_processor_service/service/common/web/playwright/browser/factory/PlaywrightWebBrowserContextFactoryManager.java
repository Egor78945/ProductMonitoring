package com.example.product_processor_service.service.common.web.playwright.browser.factory;

import com.example.product_processor_service.configuration.web.client.WebClientEnvironment;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import org.springframework.stereotype.Component;

@Component
public class PlaywrightWebBrowserContextFactoryManager implements PlaywrightWebBrowserContextFactory {
    private final PlaywrightWebBrowserFactory webBrowserFactory;
    private final WebClientEnvironment webClientEnvironment;

    public PlaywrightWebBrowserContextFactoryManager(PlaywrightWebBrowserFactory webBrowserFactory, WebClientEnvironment webClientEnvironment) {
        this.webBrowserFactory = webBrowserFactory;
        this.webClientEnvironment = webClientEnvironment;
    }

    @Override
    public BrowserContext getWithDefaults() {
        return webBrowserFactory.getWithDefaults()
                .newContext(new Browser.NewContextOptions()
                        .setUserAgent(webClientEnvironment.getWEB_USER_AGENT())
                        .setViewportSize(webClientEnvironment.getWEB_VIEW_WIDTH(), webClientEnvironment.getWEB_VIEW_HEIGHT()));
    }
}
