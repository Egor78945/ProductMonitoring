package com.example.product_processor_service.service.marketplace.parser;

import com.example.product_processor_service.model.web.browser.WebBrowserPageWrapper;
import com.example.product_processor_service.service.web.page.parser.PlaywrightWebBrowserPageParser;

public interface PyaterochkaPlaywrightWebBrowserPageParser<R> extends PlaywrightWebBrowserPageParser<R> {
    R parse(WebBrowserPageWrapper wrapper);
}
