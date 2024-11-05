package com.peter.amisy.e_commerce_api.model;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product {

    private String id;
    private String name;
    private String description;
    private double price;
    private int quantity;
}