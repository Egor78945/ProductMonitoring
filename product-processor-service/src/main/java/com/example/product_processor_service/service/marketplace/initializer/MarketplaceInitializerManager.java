package com.example.product_processor_service.service.marketplace.initializer;

import com.example.product_processor_service.model.marketplace.definition.entity.MarketplaceDefinition;
import com.example.product_processor_service.repository.marketplace.definition.MarketplaceDefinitionRepository;
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
public class MarketplaceInitializerManager implements MarketplaceInitializer<String, MarketplaceManagerService> {
    private final MarketplaceDefinitionRepository<MarketplaceDefinition> marketplaceDefinitionRepository;
    private final ApplicationContext applicationContext;
    private final Map<String, MarketplaceManagerService> marketplaceManagerServiceMap;

    public MarketplaceInitializerManager(MarketplaceDefinitionRepository<MarketplaceDefinition> marketplaceDefinitionRepository, ApplicationContext applicationContext) {
        this.marketplaceDefinitionRepository = marketplaceDefinitionRepository;
        this.applicationContext = applicationContext;
        this.marketplaceManagerServiceMap = new HashMap<>();
    }

    @Override
    public Map<String, MarketplaceManagerService> getMarketplaces() {
        return marketplaceManagerServiceMap;
    }

    @PostConstruct
    public void initMarketplaceServiceMap() {
        if (marketplaceManagerServiceMap.isEmpty()) {
            for (MarketplaceDefinition marketplaceDefinition : marketplaceDefinitionRepository.findAll()) {
                List<String> urlList = marketplaceDefinitionRepository.findBaseUrlByMarketplaceId(marketplaceDefinition.getId());
                for (String url : urlList) {
                    try {
                        Class<?> managerServiceClass = Class.forName(marketplaceDefinition.getProcessorClassName());
                        if (MarketplaceManagerService.class.isAssignableFrom(managerServiceClass) && AnnotationUtils.findAnnotation(managerServiceClass, Component.class) != null) {
                            MarketplaceManagerService marketplaceManagerService = (MarketplaceManagerService) applicationContext.getBean(managerServiceClass);
                            marketplaceManagerServiceMap.put(url, marketplaceManagerService);
                        }
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
}
