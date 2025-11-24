package com.example.product_processor_service.service.marketplace.initializer;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.product_processor_service.model.product.ProductDTO;
import com.example.product_processor_service.service.marketplace.definition.MarketplaceDefinitionService;
import com.example.product_processor_service.service.marketplace.manager.MarketplaceManagerService;
import com.example.product_processor_service.service.marketplace.path.MarketplacePathDefinitionService;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MarketplaceInitializerManager implements MarketplaceInitializer<String, ProductDTO, MarketplaceManagerService<ProductDTO>> {
    private final MarketplaceDefinitionService<UserProtoConfiguration.MarketplaceDefinitionMessage> marketplaceDefinitionService;
    private final MarketplacePathDefinitionService<UserProtoConfiguration.MarketplacePathDefinitionMessage> marketplacePathDefinitionService;
    private final Map<String, MarketplaceManagerService<ProductDTO>> marketplaceManagerServiceMap;
    private final List<MarketplaceManagerService<ProductDTO>> marketplaceManagerServiceList;

    public MarketplaceInitializerManager(MarketplaceDefinitionService<UserProtoConfiguration.MarketplaceDefinitionMessage> marketplaceDefinitionService, MarketplacePathDefinitionService<UserProtoConfiguration.MarketplacePathDefinitionMessage> marketplacePathDefinitionService, List<MarketplaceManagerService<ProductDTO>> marketplaceManagerServices) {
        this.marketplaceDefinitionService = marketplaceDefinitionService;
        this.marketplacePathDefinitionService = marketplacePathDefinitionService;
        this.marketplaceManagerServiceMap = new HashMap<>();
        this.marketplaceManagerServiceList = marketplaceManagerServices;
    }

    @Override
    public Map<String, MarketplaceManagerService<ProductDTO>> getMarketplaces() {
        return marketplaceManagerServiceMap;
    }

    @EventListener(ContextRefreshedEvent.class)
    private void initMarketplaces() {
        Map<String, MarketplaceManagerService<ProductDTO>> marketplaceManagerServiceClassNameMap = new HashMap<>();
        for (MarketplaceManagerService<ProductDTO> marketplaceManagerService : marketplaceManagerServiceList) {
            marketplaceManagerServiceClassNameMap.put(marketplaceManagerService.getClass().getName(), marketplaceManagerService);
        }
        for (UserProtoConfiguration.MarketplaceDefinitionMessage marketplaceDefinition : marketplaceDefinitionService.getAll()) {
            for (UserProtoConfiguration.MarketplacePathDefinitionMessage mpd : marketplacePathDefinitionService.getByMarketplaceDefinitionId(marketplaceDefinition.getId())) {
                try {
                    Class<?> managerServiceClass = Class.forName(marketplaceDefinition.getProcessorClassName());
                    if (MarketplaceManagerService.class.isAssignableFrom(managerServiceClass) && AnnotationUtils.findAnnotation(managerServiceClass, Component.class) != null) {
                        marketplaceManagerServiceMap.put(mpd.getBaseUrl(), marketplaceManagerServiceClassNameMap.get(marketplaceDefinition.getProcessorClassName()));
                    }
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
