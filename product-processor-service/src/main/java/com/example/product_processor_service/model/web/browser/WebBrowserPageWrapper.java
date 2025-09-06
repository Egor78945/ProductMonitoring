package com.example.product_processor_service.model.web.browser;

import com.microsoft.playwright.Page;

public abstract class WebBrowserPageWrapper {
    protected Page page;

    public WebBrowserPageWrapper(Page page) {
        this.page = page;
    }

    public abstract String locator(String selector);

    public String url() {
        return page.url();
    }

    public void terminate() {
        page = null;
    }
}
