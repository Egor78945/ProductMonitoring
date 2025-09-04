package com.example.product_processor_service.service.marketplace.manager;

import com.example.product_processor_service.model.product.ProductDTO;
import com.example.product_processor_service.service.web.playwright.page.ReloadablePlaywrightWebBrowserPageContext;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
public class PyaterochkaMarketplaceManagerService implements MarketplaceManagerService {
    private final ReloadablePlaywrightWebBrowserPageContext pageContext;

    public PyaterochkaMarketplaceManagerService(ReloadablePlaywrightWebBrowserPageContext pageContext) {
        this.pageContext = pageContext;
    }

    @Override
    public ProductDTO getProductDetails(URI url) {
        return null;
    }
}
