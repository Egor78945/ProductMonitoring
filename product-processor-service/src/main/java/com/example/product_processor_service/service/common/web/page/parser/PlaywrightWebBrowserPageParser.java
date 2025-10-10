package com.example.product_processor_service.service.common.web.page.parser;

import com.example.product_processor_service.model.web.browser.WebBrowserPageWrapper;

public interface PlaywrightWebBrowserPageParser<R> {
    R parse(WebBrowserPageWrapper wrapper);
}
