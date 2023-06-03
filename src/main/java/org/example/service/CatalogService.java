package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.PatchProductDto;
import org.example.dto.ProductRequestDto;
import org.example.dto.ProductResponseDto;
import org.example.exception.DataNotFoundException;
import org.example.mapper.MapperFull;
import org.example.model.Attribute;
import org.example.model.Producer;
import org.example.model.Product;
import org.example.model.Technique;
import org.example.model.enumeration.LaptopDiagonal;
import org.example.model.enumeration.PCForm;
import org.example.repository.AttributeRepository;
import org.example.repository.CatalogRepository;
import org.example.repository.ProducerRepository;
import org.example.repository.TechniqueRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CatalogService {

    private final CatalogRepository catalogRepository;
    private final AttributeRepository attributeRepository;
    private final ProducerRepository producerRepository;
    private final TechniqueRepository techniqueRepository;

    @Transactional
    public ProductResponseDto saveProduct(ProductRequestDto dto, String type) {
        Product product = checkParametr(dto, type);
        product = catalogRepository.save(product);
        return MapperFull.toDto(product);
    }

    private Product checkParametr(ProductRequestDto dto, String type) {
        Technique technique = techniqueRepository.findByName(type)
                .orElseThrow(() -> new DataNotFoundException("Заданного наименования нет"));
        if (technique.getName().equals("Настольные компьютеры")) {
            PCForm.from(dto.getAttribute());
        }
        if (technique.getName().equals("Ноутбуки")) {
            LaptopDiagonal.from(dto.getAttribute());
        }
        Producer producer = producerRepository.findByName(dto.getProducer())
                .orElseGet(() -> producerRepository.save(MapperFull.toEntityProducer(dto.getProducer())));
        Attribute attribute = attributeRepository.findByName(dto.getAttribute())
                .orElseGet(() -> attributeRepository.save(MapperFull.toEntityAttribute(dto.getAttribute())));
        Product product = MapperFull.toEntityProduct(dto, technique, producer, attribute);
        return product;
    }

    @Transactional
    public ProductResponseDto putProduct(ProductRequestDto dto, String type, long productId) {
        Product product = catalogRepository.findById(productId).orElseThrow(() -> new DataNotFoundException("Указанного товара нет"));
        Product updateProduct = checkParametr(dto, type);
        updateProduct.setId(product.getId());
        product = catalogRepository.save(updateProduct);
        return MapperFull.toDto(product);
    }

    @Transactional
    public ProductResponseDto patchProduct(PatchProductDto dto, String type, long productId) {
        Product product = catalogRepository.findById(productId).orElseThrow(() -> new DataNotFoundException("Указанного товара нет"));
        Product updateProduct = patchParametr(dto, type, product);
        updateProduct.setId(product.getId());
        product = catalogRepository.save(updateProduct);
        return MapperFull.toDto(product);
    }

    private Product patchParametr(PatchProductDto dto, String type, Product product) {
        Technique technique = techniqueRepository.findByName(type)
                .orElseThrow(() -> new DataNotFoundException("Заданного наименования нет"));
        if (technique.getName().equals("Настольные компьютеры")) {
            PCForm.from(dto.getAttribute());
        }
        if (technique.getName().equals("Ноутбуки")) {
            LaptopDiagonal.from(dto.getAttribute());
        }
        product.setTechnique(technique);
        if (dto.getAttribute() != null) {
            Attribute attribute = attributeRepository.findByName(dto.getAttribute())
                    .orElseGet(() -> attributeRepository.save(MapperFull.toEntityAttribute(dto.getAttribute())));
            product.setAttribute(attribute);
        }
        if (dto.getProducer() != null) {
            Producer producer = producerRepository.findByName(dto.getProducer())
                    .orElseGet(() -> producerRepository.save(MapperFull.toEntityProducer(dto.getProducer())));
            product.setProducer(producer);
        }
        if (dto.getNumer() != null) {
            product.setNumer(dto.getNumer());
        }
        if (dto.getPrice() != null) {
            product.setPrice(dto.getPrice());
        }
        if (dto.getQuantity() != null) {
            product.setQuantity(dto.getQuantity());
        }
        return product;
    }

    public void deleteProduct(long productId) {
        catalogRepository.deleteById(productId);
    }

    @Transactional(readOnly = true)
    public ProductResponseDto getIdProduct(long productId) {
        Product product = catalogRepository.findById(productId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "указанного товара нет"));
        return MapperFull.toDto(product);
    }

    @Transactional(readOnly = true)
    public List<ProductResponseDto> getTypeProduct(String type, int from, int size) {
        techniqueRepository.findByName(type)
                .orElseThrow(() -> new DataNotFoundException("Заданного наименования нет"));
        Pageable pageable = PageRequest.of(from, size);
        List<Product> products = catalogRepository.findAllByTechniqueName(type, pageable);
        return products.stream()
                .map(MapperFull::toDto)
                .collect(Collectors.toList());
    }
}
