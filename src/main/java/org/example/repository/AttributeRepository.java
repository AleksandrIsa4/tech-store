package org.example.repository;

import org.example.model.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AttributeRepository extends JpaRepository<Attribute, Long> {

    Optional<Attribute> findByName(String type);
}
