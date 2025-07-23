package com.konduto.challenge.repository;

import com.konduto.challenge.entity.TransactionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing transaction statuses.
 * Provides methods to interact with the transaction status data.
 */
@Repository
public interface TransactionStatusRepository extends JpaRepository<TransactionStatus, String> {
}