package com.example.product_processor_service.service.web.playwright.browser;

import com.example.product_processor_service.service.web.playwright.page.ReloadablePlaywrightWebBrowserPageContext;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
public class PlaywrightWebBrowserContextManagerService implements PlaywrightWebBrowserContextManager {
    private final ReloadablePlaywrightWebBrowserPageContext playwrightWebBrowserPageContext;

    public PlaywrightWebBrowserContextManagerService(ReloadablePlaywrightWebBrowserPageContext playwrightWebBrowserPageContext) {
        this.playwrightWebBrowserPageContext = playwrightWebBrowserPageContext;
    }

    @Override
    public String loadPage(URI uri, String waitSelector) {
        return playwrightWebBrowserPageContext.getPageAsString(uri, waitSelector);
    }
}
