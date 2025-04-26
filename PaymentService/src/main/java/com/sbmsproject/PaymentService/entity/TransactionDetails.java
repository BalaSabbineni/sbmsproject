package com.sbmsproject.PaymentService.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Transaction_details")
public class TransactionDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long orderId;
    private String paymentMode;
    private String referenceNumber;
    private Instant paymentDate;
    private String paymentStatus;
    private long amount;
}
