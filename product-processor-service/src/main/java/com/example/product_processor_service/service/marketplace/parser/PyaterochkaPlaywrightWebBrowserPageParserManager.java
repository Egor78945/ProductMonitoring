package com.example.product_processor_service.service.marketplace.parser;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.product_processor_service.enumeration.MarketplaceDetailsEnumeration;
import com.example.product_processor_service.model.marketplace.selector.entity.MarketplaceSelector;
import com.example.product_processor_service.model.product.ProductDTO;
import com.example.product_processor_service.model.web.browser.WebBrowserPageWrapper;
import com.example.product_processor_service.service.marketplace.selector.MarketplaceSelectorService;
import org.springframework.stereotype.Service;

@Service
public class PyaterochkaPlaywrightWebBrowserPageParserManager implements PyaterochkaPlaywrightWebBrowserPageParser<ProductDTO> {
    private final MarketplaceSelectorService<UserProtoConfiguration.MarketplaceSelectorMessage> marketplaceSelectorService;

    public PyaterochkaPlaywrightWebBrowserPageParserManager(MarketplaceSelectorService<UserProtoConfiguration.MarketplaceSelectorMessage> marketplaceSelectorService) {
        this.marketplaceSelectorService = marketplaceSelectorService;
    }

    @Override
    public ProductDTO parse(WebBrowserPageWrapper wrapper) {
        UserProtoConfiguration.MarketplaceSelectorMessage selectorMessage = marketplaceSelectorService.getByMarketplaceName(MarketplaceDetailsEnumeration.PYATEROCHKA.getName());
        System.out.println("marketplace selector message: " + selectorMessage);
        String name = wrapper.locator(selectorMessage.getProductName());
        System.out.println("loaded name: " + name);
        int price = Integer.parseInt(wrapper.locator(selectorMessage.getProductPrice()));
        System.out.println("loaded price: " + price);
        return new ProductDTO(wrapper.url(), name, price);
    }
}