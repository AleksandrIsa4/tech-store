package org.example.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.PatchProductDto;
import org.example.dto.ProductRequestDto;
import org.example.dto.ProductResponseDto;
import org.example.service.CatalogService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/catalog")
@Slf4j
public class CatalogController {
    private final CatalogService catalogService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ProductResponseDto postCatalog(@RequestBody @Valid ProductRequestDto dto, @RequestParam String type) {
        return catalogService.saveProduct(dto, type);
    }

    @PutMapping(value = "/{productId}")
    public ProductResponseDto putCatalog(@RequestBody @Valid ProductRequestDto dto, @RequestParam String type, @PathVariable long productId) {
        return catalogService.putProduct(dto, type, productId);
    }

    @PatchMapping(value = "/{productId}")
    public ProductResponseDto patchCatalog(@RequestBody @Valid PatchProductDto dto, @RequestParam String type, @PathVariable long productId) {
        return catalogService.patchProduct(dto, type, productId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{productId}")
    public void deleteCatalog(@PathVariable long productId) {
        catalogService.deleteProduct(productId);
    }

    @GetMapping(value = "/{productId}")
    public ProductResponseDto patchCatalog(@PathVariable long productId) {
        return catalogService.getIdProduct(productId);
    }

    @GetMapping
    public List<ProductResponseDto> patchCatalog(@RequestParam String type,
                                                 @RequestParam(name = "from", defaultValue = "0") int from,
                                                 @RequestParam(name = "size", defaultValue = "10") int size) {
        return catalogService.getTypeProduct(type, from, size);
    }
}
