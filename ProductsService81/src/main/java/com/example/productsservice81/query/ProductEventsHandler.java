package com.example.productsservice81.query;

import com.example.productsservice81.core.data.ProductEntity;
import com.example.productsservice81.core.data.ProductRepository;
import com.example.productsservice81.core.events.ProductCreatedEvent;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component

public class ProductEventsHandler {
    private final ProductRepository productRepository;
    public ProductEventsHandler(ProductRepository productRepository){
        this.productRepository = productRepository;
    }
    @EventHandler
    public void on(ProductCreatedEvent event){
        ProductEntity productEntity = new ProductEntity();
        BeanUtils.copyProperties(event, productEntity);
        System.out.println("test");
        productRepository.save(productEntity);
    }
}
