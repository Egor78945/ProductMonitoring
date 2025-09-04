package com.example.product_processor_service.service.web.playwright.browser;

import com.example.product_processor_service.service.web.playwright.page.PlaywrightWebBrowserPageContext;
import com.microsoft.playwright.Page;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Optional;

//TO DELETE
@Service
public class PlaywrightWebBrowserPageManagerService implements PlaywrightWebBrowserPageManager {
    private final PlaywrightWebBrowserPageContext playwrightWebBrowserPageContext;
    public PlaywrightWebBrowserPageManagerService(PlaywrightWebBrowserPageContext playwrightWebBrowserPageContext) {
        this.playwrightWebBrowserPageContext = playwrightWebBrowserPageContext;
    }
    @Override
    public Optional<Page> getPageOf(URI uri) {
        return Optional.of(null);
    }
}
