package com.example.product_processor_service.service.web.playwright.page;

import java.net.URI;

public abstract class PlaywrightWebBrowserPageContext {
    public abstract String getPageAsString(URI uri, String waitSelector);
}
