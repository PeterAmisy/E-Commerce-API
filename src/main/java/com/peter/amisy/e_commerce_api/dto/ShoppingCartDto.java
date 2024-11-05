package com.peter.amisy.e_commerce_api.dto;

import com.peter.amisy.e_commerce_api.model.Product;
import com.peter.amisy.e_commerce_api.model.ShoppingCart;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for {@link com.peter.amisy.e_commerce_api.model.ShoppingCart}
 */
@Value
@Getter
@Setter
@AllArgsConstructor
@Builder
public class ShoppingCartDto implements Serializable {
    @NotNull
    Integer id;
    LocalDateTime createdDate;
    LocalDateTime lastModifiedDate;
    List<Product> products;
    @Digits(integer = 0, fraction = 0)
    @PositiveOrZero
    double totalPrice;

    public static ShoppingCartDto fromEntity (ShoppingCart shoppingCart) {
        return ShoppingCartDto.builder()
                .id(shoppingCart.getId())
                .createdDate(shoppingCart.getCreatedDate())
                .lastModifiedDate(shoppingCart.getLastModifiedDate())
                .products(shoppingCart.getProducts())
                .totalPrice(shoppingCart.getTotalPrice())
                .build();
    }

    public static ShoppingCart toEntity (ShoppingCartDto shoppingCart) {
        return ShoppingCart.builder()
                .id(shoppingCart.getId())
                .createdDate(shoppingCart.getCreatedDate())
                .lastModifiedDate(shoppingCart.getLastModifiedDate())
                .products(shoppingCart.getProducts())
                .totalPrice(shoppingCart.getTotalPrice())
                .build();
    }
}