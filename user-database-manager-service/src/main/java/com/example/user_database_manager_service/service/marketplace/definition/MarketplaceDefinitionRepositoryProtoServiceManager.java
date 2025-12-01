package com.example.user_database_manager_service.service.marketplace.definition;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.exception.AlreadyExistsException;
import com.example.user_database_manager_service.exception.NotFoundException;
import com.example.user_database_manager_service.repository.marketplace.definition.MarketplaceDefinitionRepository;
import org.springframework.stereotype.Service;

@Service
public class MarketplaceDefinitionRepositoryProtoServiceManager extends MarketplaceDefinitionRepositoryService<UserProtoConfiguration.MarketplaceDefinitionMessage> {
    public MarketplaceDefinitionRepositoryProtoServiceManager(MarketplaceDefinitionRepository<UserProtoConfiguration.MarketplaceDefinitionMessage> marketplaceDefinitionRepository) {
        super(marketplaceDefinitionRepository);
    }

    @Override
    public UserProtoConfiguration.MarketplaceDefinitionMessage save(UserProtoConfiguration.MarketplaceDefinitionMessage entity) {
        if (!existsByName(entity.getName())) {
            return super.save(entity);
        } else
            throw new AlreadyExistsException(String.format("MarketplaceDefinition is already exists by name: %s", entity.getName()));
    }

    @Override
    public UserProtoConfiguration.MarketplaceDefinitionMessage update(UserProtoConfiguration.MarketplaceDefinitionMessage entity) {
        if (existsByName(entity.getName())) {
            return super.save(entity);
        } else
            throw new NotFoundException(String.format("MarketplaceDefinition is not found by name: %s", entity.getName()));
    }
}
