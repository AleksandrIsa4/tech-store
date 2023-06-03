package org.example.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
public class ProductRequestDto {

    @NotNull
    String numer;

    @NotNull
    String producer;

    @NotNull
    Float price;

    @NotNull
    Long quantity;

    @NotNull
    String attribute;
}
