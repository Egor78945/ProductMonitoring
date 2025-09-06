package com.example.product_processor_service.service.web.playwright.browser;

import com.microsoft.playwright.Page;

public abstract class PlaywrightWebBrowserPagePoolManager {
    public abstract Page getPage();
    public abstract void reloadPage();
    public abstract void reloadPage(Page page);
}
