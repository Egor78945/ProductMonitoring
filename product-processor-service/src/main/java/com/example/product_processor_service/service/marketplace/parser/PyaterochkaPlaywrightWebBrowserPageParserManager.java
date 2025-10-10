package com.example.product_processor_service.service.marketplace.parser;

import com.example.product_processor_service.enumeration.MarketplaceDetailsEnumeration;
import com.example.product_processor_service.model.marketplace.selector.entity.MarketplaceSelector;
import com.example.product_processor_service.model.product.ProductDTO;
import com.example.product_processor_service.model.web.browser.WebBrowserPageWrapper;
import com.example.product_processor_service.service.marketplace.selector.MarketplaceSelectorService;
import org.springframework.stereotype.Service;

@Service
public class PyaterochkaPlaywrightWebBrowserPageParserManager implements PyaterochkaPlaywrightWebBrowserPageParser<ProductDTO> {
    private final MarketplaceSelectorService<MarketplaceSelector> marketplaceSelectorService;

    public PyaterochkaPlaywrightWebBrowserPageParserManager(MarketplaceSelectorService<MarketplaceSelector> marketplaceSelectorService) {
        this.marketplaceSelectorService = marketplaceSelectorService;
    }

    @Override
    public ProductDTO parse(WebBrowserPageWrapper wrapper) {
        String name = wrapper.locator(marketplaceSelectorService.getByMarketplaceName(MarketplaceDetailsEnumeration.PYATEROCHKA.getName()).getProductName());
        int price = Integer.parseInt(wrapper.locator(marketplaceSelectorService.getByMarketplaceName(MarketplaceDetailsEnumeration.PYATEROCHKA.getName()).getProductPrice()));
        return new ProductDTO(wrapper.url(), name, price);
    }
}