package org.example.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
public class PatchProductDto {

    String numer;


    String producer;

    Float price;

    Long quantity;

    String attribute;
}
