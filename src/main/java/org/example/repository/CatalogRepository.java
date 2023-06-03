package org.example.repository;

import org.example.model.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CatalogRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByTechniqueName(String type, Pageable pageable);
}
