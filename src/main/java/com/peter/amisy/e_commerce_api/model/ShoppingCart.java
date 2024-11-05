package com.peter.amisy.e_commerce_api.model;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ShoppingCart {

    private String id;
    private List<Product> products;
    private double totalPrice;

    public void addProduct(Product product) {
        this.products.add(product);
        this.totalPrice += product.getPrice();
    }

    public void removeProduct(Product product) {
        if (this.products.remove(product)) {
            this.totalPrice -= product.getPrice();
        }
    }
}