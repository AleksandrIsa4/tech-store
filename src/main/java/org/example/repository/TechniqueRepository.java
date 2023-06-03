package org.example.repository;

import org.example.model.Technique;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TechniqueRepository extends JpaRepository<Technique, Long> {

    Optional<Technique> findByName(String type);
}
