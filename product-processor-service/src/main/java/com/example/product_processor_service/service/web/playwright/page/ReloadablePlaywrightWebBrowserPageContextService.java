package com.example.product_processor_service.service.web.playwright.page;

import com.example.product_processor_service.model.web.browser.DefaultWebBrowserPageWrapper;
import com.example.product_processor_service.model.web.browser.WebBrowserPageWrapper;
import com.example.product_processor_service.service.web.page.parser.PlaywrightWebBrowserPageParser;
import com.example.product_processor_service.service.web.playwright.browser.PlaywrightWebBrowserPagePoolManager;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitUntilState;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
public class ReloadablePlaywrightWebBrowserPageContextService extends ReloadablePlaywrightWebBrowserPageContext {
    private final PlaywrightWebBrowserPagePoolManager pagePoolManager;

    public ReloadablePlaywrightWebBrowserPageContextService(PlaywrightWebBrowserPagePoolManager pagePoolManager) {
        this.pagePoolManager = pagePoolManager;
    }

    @Override
    protected boolean cleanup(Page page) {
        try {
            page.evaluate("window.stop()");
            page.navigate("about:blank", new Page.NavigateOptions()
                    .setWaitUntil(WaitUntilState.LOAD));
            page.evaluate("""
                document.body.innerHTML = '';
                document.head.innerHTML = '';
                if (window.clearTimeout) {
                    for (let i = setTimeout(() => {}, 0); i > 0; i--) {
                        clearTimeout(i);
                        clearInterval(i);
                    }
                }
            """);
            page.context().clearCookies();
            return true;
        } catch (Exception e) {
            pagePoolManager.reloadPage();
            return false;
        }
    }

    @Override
    public <P> P getAndParse(URI uri, PlaywrightWebBrowserPageParser<P> parser) {
        Page page = pagePoolManager.getPage();
        page.navigate(uri.toString());

        WebBrowserPageWrapper pageWrapper = new DefaultWebBrowserPageWrapper(page);

        P parsed = parser.parse(pageWrapper);

        try {
            return parsed;
        } finally {
            pageWrapper.terminate();
            if(cleanup(page)) {
                pagePoolManager.reloadPage(page);
            } else {
                pagePoolManager.reloadPage();
            }
        }
    }
}
