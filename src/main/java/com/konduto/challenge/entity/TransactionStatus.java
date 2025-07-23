package com.konduto.challenge.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * Entity representing the status of a credit card transaction.
 * Contains the transaction ID, status, and a reference to the associated CreditCardTransaction.
 */
@Entity
@Table(name = "transaction_status")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionStatus {
    @Id
    private String transactionId;
    private String status;

    /**
     * The associated credit card transaction.
     * Maps the transaction ID to the CreditCardTransaction entity.
     */
    @OneToOne
    @JoinColumn(name = "transaction_id")
    @MapsId
    private CreditCardTransaction creditCardTransaction;
}