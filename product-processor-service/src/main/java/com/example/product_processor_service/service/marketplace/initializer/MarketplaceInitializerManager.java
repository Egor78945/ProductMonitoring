package com.example.product_processor_service.service.marketplace.initializer;

import com.example.product_processor_service.model.marketplace.definition.entity.MarketplaceDefinition;
import com.example.product_processor_service.model.marketplace.definition.path.entity.MarketplacePathDefinition;
import com.example.product_processor_service.model.product.ProductDTO;
import com.example.product_processor_service.repository.marketplace.definition.MarketplaceDefinitionRepository;
import com.example.product_processor_service.repository.marketplace.definition.path.MarketplacePathDefinitionRepository;
import com.example.product_processor_service.service.marketplace.manager.MarketplaceManagerService;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MarketplaceInitializerManager implements MarketplaceInitializer<String, MarketplaceManagerService<ProductDTO>> {
    private final MarketplaceDefinitionRepository<MarketplaceDefinition> marketplaceDefinitionRepository;
    private final MarketplacePathDefinitionRepository<MarketplacePathDefinition> marketplacePathDefinitionRepository;
    private final ApplicationContext applicationContext;
    private final Map<String, MarketplaceManagerService<ProductDTO>> marketplaceManagerServiceMap;

    public MarketplaceInitializerManager(MarketplaceDefinitionRepository<MarketplaceDefinition> marketplaceDefinitionRepository, MarketplacePathDefinitionRepository<MarketplacePathDefinition> marketplacePathDefinitionRepository, ApplicationContext applicationContext) {
        this.marketplaceDefinitionRepository = marketplaceDefinitionRepository;
        this.marketplacePathDefinitionRepository = marketplacePathDefinitionRepository;
        this.applicationContext = applicationContext;
        this.marketplaceManagerServiceMap = new HashMap<>();
    }

    @Override
    public Map<String, MarketplaceManagerService<ProductDTO>> getMarketplaces() {
        return marketplaceManagerServiceMap;
    }

    @PostConstruct
    public void initMarketplaceServiceMap() {
        if (marketplaceManagerServiceMap.isEmpty()) {
            for (MarketplaceDefinition marketplaceDefinition : marketplaceDefinitionRepository.findAll()) {
                List<MarketplacePathDefinition> marketplacePathDefinitions = marketplacePathDefinitionRepository.findByMarketplaceId(marketplaceDefinition.getId());
                for (MarketplacePathDefinition mpd : marketplacePathDefinitions) {
                    try {
                        Class<?> managerServiceClass = Class.forName(marketplaceDefinition.getProcessorClassName());
                        if (MarketplaceManagerService.class.isAssignableFrom(managerServiceClass) && AnnotationUtils.findAnnotation(managerServiceClass, Component.class) != null) {
                            MarketplaceManagerService<ProductDTO> marketplaceManagerService = (MarketplaceManagerService) applicationContext.getBean(managerServiceClass);
                            marketplaceManagerServiceMap.put(mpd.getBaseUrl(), marketplaceManagerService);
                        }
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
}
