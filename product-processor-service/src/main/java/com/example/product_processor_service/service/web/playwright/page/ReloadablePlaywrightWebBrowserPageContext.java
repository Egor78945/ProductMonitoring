package com.example.product_processor_service.service.web.playwright.page;

import com.microsoft.playwright.Page;

public abstract class ReloadablePlaywrightWebBrowserPageContext extends PlaywrightWebBrowserPageContext {
    protected abstract boolean cleanup(Page page);
}
