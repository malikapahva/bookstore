package com.challengers.services;

import com.challengers.dto.TransactionDto;
import com.challengers.entities.Book;
import com.challengers.entities.BookTransactionInfo;
import com.challengers.entities.Transaction;
import com.challengers.repo.BookRepository;
import com.challengers.repo.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
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
    public List<Transaction> getAllTransactions(Long userId){
        return transactionRepository.findByUserId(userId);
    }
}
