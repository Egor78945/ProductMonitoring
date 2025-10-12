package com.example.product_processor_service.service.common.web.playwright.page;

import com.example.product_processor_service.service.common.web.page.parser.PlaywrightWebBrowserPageParser;

import java.net.URI;

public abstract class PlaywrightWebBrowserPageContext {
    public abstract <P> P getAndParse(URI uri, PlaywrightWebBrowserPageParser<P> parser);
}