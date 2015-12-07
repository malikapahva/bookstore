package com.challengers.dto;

import com.challengers.entities.BookTransactionInfo;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Malika(mxp134930) on 12/6/2015.
 */
public class TransactionDto {

    private Long userId;
    private Set<BookTransactionInfo> bookTransactionInfo = new HashSet<>();

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Set<BookTransactionInfo> getBookTransactionInfo() {
        return bookTransactionInfo;
    }

    public void setBookTransactionInfo(Set<BookTransactionInfo> bookTransactionInfo) {
        this.bookTransactionInfo = bookTransactionInfo;
    }
}
