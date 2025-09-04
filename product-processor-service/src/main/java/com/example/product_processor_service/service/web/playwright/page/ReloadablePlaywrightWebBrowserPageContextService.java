package com.example.product_processor_service.service.web.playwright.page;

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
    public String getPageAsString(URI uri, String waitSelector) {
        Page page = pagePoolManager.getPage();
        try {
            page.navigate(uri.toString());
            page.waitForSelector(waitSelector);
            String content = page.content();
            return content;
        } finally {
            if (!cleanup(page)) {
                pagePoolManager.reloadPage();
            } else {
                pagePoolManager.reloadPage(page);
            }
        }

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
            page.evaluate("localStorage.clear(); sessionStorage.clear();");
            return true;
        } catch (Exception e) {
            pagePoolManager.reloadPage();
            return false;
        }
    }
}
