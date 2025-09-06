package com.example.product_processor_service.service.web.playwright.page;

import com.example.product_processor_service.service.web.page.parser.PlaywrightWebBrowserPageParser;
import com.microsoft.playwright.Page;

import java.net.URI;

public abstract class PlaywrightWebBrowserPageContext {
    public abstract <P> P getAndParse(URI uri, PlaywrightWebBrowserPageParser<P> parser);
}