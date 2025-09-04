package com.example.product_processor_service.service.web.playwright.browser.factory;

import com.example.product_processor_service.configuration.playwright.PlaywrightConfiguration;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import org.springframework.stereotype.Component;

@Component
public class PlaywrightWebBrowserFactoryManager implements PlaywrightWebBrowserFactory {
    private final PlaywrightConfiguration playwrightConfiguration;

    public PlaywrightWebBrowserFactoryManager(PlaywrightConfiguration playwrightConfiguration) {
        this.playwrightConfiguration = playwrightConfiguration;
    }

    @Override
    public Browser getWithDefaults() {
        return playwrightConfiguration.getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
    }
}
