package com.challengers.controller;

import com.challengers.dto.TransactionDto;
import com.challengers.entities.Transaction;
import com.challengers.services.TransactionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

/**
 * Created by Malika(mxp134930) on 12/6/2015.
 */

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @RequestMapping(value = "/buybook", method = RequestMethod.POST)
    public ResponseEntity<?> addBook(@RequestBody TransactionDto transactionDto){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .buildAndExpand()
                    .toUri());
        try {
            Transaction transaction = transactionService.buyBook(transactionDto);
            return new ResponseEntity<>("Transaction Completed" + transaction.getTransactionId(), httpHeaders, HttpStatus.CREATED);
        }
        catch (Exception ex){
            return new ResponseEntity<>("Transaction Failed " + ex.getMessage(), httpHeaders, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping("/gettransactions/{userId}")
    public List<Transaction> getAllTransactions(@PathVariable Long userId) throws JsonProcessingException {
        return transactionService.getAllTransactions(userId);
    }
}
