package com.myrestaurant.restaurantapp.transaction.service;

import org.springframework.stereotype.Service;
import com.myrestaurant.restaurantapp.transaction.model.Transaction;

@Service
public class TransactionService {
    public void processTransaction(Transaction transaction) {
        if ("delivery".equals(transaction.getOrderType())) {
            if (transaction.getStreetAddress() == null || transaction.getStreetAddress().isEmpty()) {
                throw new IllegalArgumentException("Street address is required for delivery");
            }
            if (transaction.getCity() == null || transaction.getCity().isEmpty()) {
                throw new IllegalArgumentException("City is required for delivery");
            }
            if (transaction.getState() == null || transaction.getState().isEmpty()) {
                throw new IllegalArgumentException("State is required for delivery");
            }
            if (transaction.getZipCode() == null || transaction.getZipCode().isEmpty() || !transaction.getZipCode().matches("\\d{2}-\\d{3}")) {
                throw new IllegalArgumentException("ZIP code should be in format XX-XXX");
            }
        }
    }
}

