package org.example.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
public class ProductResponseDto {

    Long id;

    String numer;

    String producer;

    Float price;

    Long quantity;

    String technique;

    String attribute;
}
