package com.myrestaurant.restaurantapp.transaction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.myrestaurant.restaurantapp.transaction.model.Transaction;
import com.myrestaurant.restaurantapp.transaction.service.TransactionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/transaction")
@CrossOrigin("*")
@Validated
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<String> processTransaction(@Valid @RequestBody Transaction request) {
        transactionService.processTransaction(request);
        return new ResponseEntity<>("Transaction processed successfully", HttpStatus.OK);
    }
}