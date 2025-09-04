package com.example.product_processor_service.service.web.playwright.browser;

import com.microsoft.playwright.Page;

import java.net.URI;
import java.util.Optional;

//TO DELETE
public interface PlaywrightWebBrowserPageManager {
    Optional<Page> getPageOf(URI uri);
}
