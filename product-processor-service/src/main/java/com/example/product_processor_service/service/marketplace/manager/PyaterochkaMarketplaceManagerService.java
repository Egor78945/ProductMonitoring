package com.example.product_processor_service.service.marketplace.manager;

import com.example.product_processor_service.model.product.ProductDTO;
import com.example.product_processor_service.service.marketplace.parser.PyaterochkaPlaywrightWebBrowserPageParser;
import com.example.product_processor_service.service.web.playwright.page.ReloadablePlaywrightWebBrowserPageContext;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
public class PyaterochkaMarketplaceManagerService implements MarketplaceManagerService {
    private final ReloadablePlaywrightWebBrowserPageContext pageContext;
    private final PyaterochkaPlaywrightWebBrowserPageParser<ProductDTO> parser;

    public PyaterochkaMarketplaceManagerService(ReloadablePlaywrightWebBrowserPageContext pageContext, PyaterochkaPlaywrightWebBrowserPageParser<ProductDTO> parser) {
        this.pageContext = pageContext;
        this.parser = parser;
    }

    @Override
    public void loadProduct(URI uri) {
        ProductDTO productDTO = pageContext.getAndParse(uri, parser);
    }
}
