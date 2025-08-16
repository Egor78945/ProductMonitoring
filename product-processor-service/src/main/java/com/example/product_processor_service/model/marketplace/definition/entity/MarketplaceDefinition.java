package com.example.product_processor_service.model.marketplace.definition.entity;

import java.util.Objects;

public class MarketplaceDefinition {
    private Long id;
    private String name;
    private String processorClassName;

    public MarketplaceDefinition(Long id, String name, String processorClassName) {
        this.id = id;
        this.name = name;
        this.processorClassName = processorClassName;
    }

    public MarketplaceDefinition() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProcessorClassName() {
        return processorClassName;
    }

    public void setProcessorClassName(String processorClassName) {
        this.processorClassName = processorClassName;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MarketplaceDefinition that = (MarketplaceDefinition) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "MarketplaceDefinition{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", processorClassName='" + processorClassName + '\'' +
                '}';
    }
}
