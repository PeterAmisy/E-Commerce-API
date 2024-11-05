package com.peter.amisy.e_commerce_api.model;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Payment {

    private String id;
    private String userId;
    private double amount;
    private String paymentMethod;
    private String status;
    private String transactionId;
}