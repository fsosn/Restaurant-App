 package com.myrestaurant.restaurantapp.transaction.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.access.prepost.PreAuthorize;
import com.myrestaurant.restaurantapp.transaction.model.Transaction;
import com.myrestaurant.restaurantapp.transaction.service.TransactionService;

import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/transaction")
@CrossOrigin("*")
@Validated
public class TransactionController {

    private final TransactionService transactionService;
    private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> processTransaction(@Valid @RequestBody Transaction request, @RequestHeader("Authorization") String authorizationHeader) {
        logger.info("Authorization Header: " + authorizationHeader);
        transactionService.processTransaction(request);
        return new ResponseEntity<>("Transaction processed successfully", HttpStatus.OK);
    }
}
