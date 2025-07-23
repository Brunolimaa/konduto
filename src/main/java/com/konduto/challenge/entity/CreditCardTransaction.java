package com.konduto.challenge.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * Entity representing a credit card transaction.
 * Contains the transaction ID and the credit card details.
 */
@Entity
@Table(name = "credit_card_transactions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditCardTransaction {
    @Id
    private String transactionId;
    private String creditCard;
}
