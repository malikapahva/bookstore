package com.challengers.services;

import com.challengers.dto.TransactionDto;
import com.challengers.entities.Book;
import com.challengers.entities.BookTransactionInfo;
import com.challengers.entities.Transaction;
import com.challengers.repo.BookRepository;
import com.challengers.repo.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

/**
 * Created by Malika(mxp134930) on 12/6/2015.
 */

@Service
public class TransactionService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Transactional(readOnly = false)
    public Transaction buyBook(TransactionDto transactionDto){
        Set<BookTransactionInfo> bookTransactionInfos = transactionDto.getBookTransactionInfo();
        for (BookTransactionInfo bookTransactionInfo : bookTransactionInfos) {
            int quantitySold = bookTransactionInfo.getQuantitySold();
            Book book = bookRepository.findOne(bookTransactionInfo.getBookId());
            book.setSold(book.getSold() + quantitySold);
            bookRepository.save(book);
        }
        Transaction transaction = new Transaction(transactionDto.getUserId(), bookTransactionInfos);
        return transactionRepository.save(transaction);
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "defaultCache", key = "T(com.challengers.services.KeyGenerator).generateKey(#userId, #transactionDate)")
    public List<Transaction> getAllTransactionsByDate(Long userId, LocalDate transactionDate){
        System.out.println("Not in Cache \n Fetching data from database for user : " + userId);
        return transactionRepository.findByUserIdAndTransactionDate(userId, transactionDate);
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "defaultCache", key = "#userId")
    public List<Transaction> getAllTransactions(Long userId){
        System.out.println("Not in Cache \n Fetching data from database for user : " + userId);
        return transactionRepository.findByUserId(userId);
    }
}
