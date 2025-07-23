package com.konduto.challenge.repository;

import com.konduto.challenge.entity.CreditCardTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

/**
 * Repository interface for managing credit card transactions.
 * Provides methods to calculate the credit card score based on transaction status.
 */
@Repository
public interface CreditCardTransactionRepository extends JpaRepository<CreditCardTransaction, String> {

    @Query(value = "SELECT SUM(CASE ts.status " +
            "WHEN 'approved' THEN -1.0 " +
            "WHEN 'pending' THEN 0.0 " +
            "WHEN 'declined' THEN 5.0 " +
            "ELSE 0.0 END) " +
            "FROM credit_card_transactions cct " +
            "JOIN transaction_status ts ON cct.transaction_id = ts.transaction_id " +
            "WHERE cct.credit_card = :cpf",
            nativeQuery = true)
    BigDecimal calculateCcScore(String cpf);
}