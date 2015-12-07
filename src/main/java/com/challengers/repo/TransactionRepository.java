package com.challengers.repo;

import com.challengers.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Malika(mxp134930) on 11/15/2015.
 */
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByUserIdAndTransactionDate(Long userId, LocalDate transactionDate);
    List<Transaction> findByUserId(Long userId);
}
