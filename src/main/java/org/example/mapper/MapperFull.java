package org.example.mapper;

import org.example.dto.ProductRequestDto;
import org.example.dto.ProductResponseDto;
import org.example.model.Attribute;
import org.example.model.Producer;
import org.example.model.Product;
import org.example.model.Technique;


public class MapperFull {

    public static Technique toEntityTechnique(String type) {
        return Technique.builder()
                .name(type)
                .build();
    }

    public static Producer toEntityProducer(String name) {
        return Producer.builder()
                .name(name)
                .build();
    }

    public static Attribute toEntityAttribute(String name) {
        return Attribute.builder()
                .name(name)
                .build();
    }

    public static Product toEntityProduct(ProductRequestDto dto, Technique technique, Producer producer, Attribute attribute) {
        return Product.builder()
                .numer(dto.getNumer())
                .producer(producer)
                .price(dto.getPrice())
                .quantity(dto.getQuantity())
                .technique(technique)
                .attribute(attribute)
                .build();
    }

    public static ProductResponseDto toDto(Product product) {
        return ProductResponseDto.builder()
                .id(product.getId())
                .numer(product.getNumer())
                .producer(product.getProducer().getName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .technique(product.getTechnique().getName())
                .attribute(product.getAttribute().getName())
                .build();
    }
}
