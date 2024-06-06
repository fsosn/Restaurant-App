package com.myrestaurant.restaurantapp.transactionTests;

import com.myrestaurant.restaurantapp.transaction.model.Transaction;
import com.myrestaurant.restaurantapp.transaction.service.TransactionService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TransactionServiceTest {

    private final TransactionService transactionService = new TransactionService();

    @Test
    public void testProcessTransactionWithValidData() {
        Transaction transaction = new Transaction();
        transaction.setFirstName("John");
        transaction.setLastName("Doe");
        transaction.setEmail("john.doe@example.com");
        transaction.setPhoneNumber("123456789");
        transaction.setOrderType("pickup");
        transaction.setTotalCost(100.0);

        transactionService.processTransaction(transaction);
    }

    @Test
    public void testProcessTransactionWithInvalidDeliveryData() {
        Transaction transaction = new Transaction();
        transaction.setFirstName("John");
        transaction.setLastName("Doe");
        transaction.setEmail("john.doe@example.com");
        transaction.setPhoneNumber("123456789");
        transaction.setOrderType("delivery");
        transaction.setTotalCost(100.0);

        assertThrows(IllegalArgumentException.class, () -> {
            transactionService.processTransaction(transaction);
        }, "Street address is required for delivery");
    }

    @Test
    public void testProcessTransactionWithInvalidZipCode() {
        Transaction transaction = new Transaction();
        transaction.setFirstName("John");
        transaction.setLastName("Doe");
        transaction.setEmail("john.doe@example.com");
        transaction.setPhoneNumber("123456789");
        transaction.setOrderType("delivery");
        transaction.setStreetAddress("123 Main St");
        transaction.setCity("Springfield");
        transaction.setState("IL");
        transaction.setZipCode("1234");
        transaction.setTotalCost(100.0);

        assertThrows(IllegalArgumentException.class, () -> {
            transactionService.processTransaction(transaction);
        }, "ZIP code should be in format XX-XXX");
    }

    @Test
    public void testProcessTransactionWithMissingCity() {
        Transaction transaction = new Transaction();
        transaction.setFirstName("John");
        transaction.setLastName("Doe");
        transaction.setEmail("john.doe@example.com");
        transaction.setPhoneNumber("123456789");
        transaction.setOrderType("delivery");
        transaction.setStreetAddress("123 Main St");
        transaction.setState("IL");
        transaction.setZipCode("12-345");
        transaction.setTotalCost(100.0);

        assertThrows(IllegalArgumentException.class, () -> {
            transactionService.processTransaction(transaction);
        }, "City is required for delivery");
    }

    @Test
    public void testProcessTransactionWithMissingState() {
        Transaction transaction = new Transaction();
        transaction.setFirstName("John");
        transaction.setLastName("Doe");
        transaction.setEmail("john.doe@example.com");
        transaction.setPhoneNumber("123456789");
        transaction.setOrderType("delivery");
        transaction.setStreetAddress("123 Main St");
        transaction.setCity("Springfield");
        transaction.setZipCode("12-345");
        transaction.setTotalCost(100.0);

        assertThrows(IllegalArgumentException.class, () -> {
            transactionService.processTransaction(transaction);
        }, "State is required for delivery");
    }

    @Test
    public void testProcessTransactionWithMissingZipCode() {
        Transaction transaction = new Transaction();
        transaction.setFirstName("John");
        transaction.setLastName("Doe");
        transaction.setEmail("john.doe@example.com");
        transaction.setPhoneNumber("123456789");
        transaction.setOrderType("delivery");
        transaction.setStreetAddress("123 Main St");
        transaction.setCity("Springfield");
        transaction.setState("IL");
        transaction.setTotalCost(100.0);

        assertThrows(IllegalArgumentException.class, () -> {
            transactionService.processTransaction(transaction);
        }, "ZIP code should be in format XX-XXX");
    }

}
