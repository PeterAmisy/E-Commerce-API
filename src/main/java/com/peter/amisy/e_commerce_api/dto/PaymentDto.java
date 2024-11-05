package com.peter.amisy.e_commerce_api.dto;

import com.peter.amisy.e_commerce_api.model.Payment;
import com.peter.amisy.e_commerce_api.model.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.peter.amisy.e_commerce_api.model.Payment}
 */
@Value
@Getter
@Setter
@AllArgsConstructor
@Builder
public class PaymentDto implements Serializable {
    @NotNull
    Integer id;
    LocalDateTime createdDate;
    LocalDateTime lastModifiedDate;
    @PositiveOrZero
    double amount;
    String paymentMethod;
    @NotBlank
    String status;
    String transactionId;

    public static PaymentDto fromEntity (Payment payment) {
        return PaymentDto.builder()
                .id(payment.getId())
                .createdDate(payment.getCreatedDate())
                .lastModifiedDate(payment.getLastModifiedDate())
                .amount(payment.getAmount())
                .paymentMethod(payment.getPaymentMethod())
                .status(payment.getStatus())
                .transactionId(payment.getTransactionId())
                .build();
    }

    public static Payment toEntity (PaymentDto payment) {
        return Payment.builder()
                .id(payment.getId())
                .createdDate(payment.getCreatedDate())
                .lastModifiedDate(payment.getLastModifiedDate())
                .amount(payment.getAmount())
                .paymentMethod(payment.getPaymentMethod())
                .status(payment.getStatus())
                .transactionId(payment.getTransactionId())
                .build();
    }
}