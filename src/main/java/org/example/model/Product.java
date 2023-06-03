package org.example.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Entity
@Table(name = "products", schema = "PUBLIC")
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Long id;

    @Column(name = "numer")
    String numer;

    @ManyToOne
    @JoinColumn(name = "producer_id")
    Producer producer;

    @Column(name = "price")
    Float price;

    @Column(name = "quantity")
    Long quantity;

    @ManyToOne
    @JoinColumn(name = "technique_id")
    Technique technique;

    // @ManyToOne(cascade = CascadeType.ALL)
    @ManyToOne
    @JoinColumn(name = "attribute_id")
    Attribute attribute;
}
