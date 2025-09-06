package com.example.product_processor_service.model.web.browser;

import com.example.product_processor_service.exception.SessionTerminatedException;
import com.microsoft.playwright.Page;

public class DefaultWebBrowserPageWrapper extends WebBrowserPageWrapper {
    public DefaultWebBrowserPageWrapper(Page page) {
        super(page);
    }

    @Override
    public String locator(String selector) {
        if(page == null) {
            return page.locator(selector).first().textContent();
        }
        throw new SessionTerminatedException();
    }
}
