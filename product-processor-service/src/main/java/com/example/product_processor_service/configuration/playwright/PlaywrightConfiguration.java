package com.example.product_processor_service.configuration.playwright;

import com.microsoft.playwright.Playwright;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PlaywrightConfiguration {
    private final Playwright playwright;

    public PlaywrightConfiguration() {
        this.playwright = Playwright.create();
    }

    public Playwright getPlaywright() {
        return playwright;
    }
}
