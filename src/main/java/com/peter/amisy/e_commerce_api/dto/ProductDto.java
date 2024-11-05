package com.peter.amisy.e_commerce_api.dto;


import com.peter.amisy.e_commerce_api.model.Product;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link Product}
 */
@Value
@Getter
@Setter
@AllArgsConstructor
@Builder
public class ProductDto implements Serializable {
    @NotNull
    Integer id;
    LocalDateTime createdDate;
    LocalDateTime lastModifiedDate;
    String name;
    String description;
    @Digits(integer = 0, fraction = 0)
    @PositiveOrZero
    double price;
    @PositiveOrZero
    int quantity;

    public static ProductDto fromEntity (Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .createdDate(product.getCreatedDate())
                .lastModifiedDate(product.getLastModifiedDate())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .build();
    }

    public static Product toEntity (ProductDto product) {
        return Product.builder()
                .id(product.getId())
                .createdDate(product.getCreatedDate())
                .lastModifiedDate(product.getLastModifiedDate())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .build();
    }
}