package com.myrestaurant.restaurantapp.transactionTests;

import com.myrestaurant.restaurantapp.transaction.model.Transaction;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TransactionModelTest {

    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testTransactionValidData() {
        Transaction transaction = new Transaction();
        transaction.setFirstName("John");
        transaction.setLastName("Doe");
        transaction.setEmail("john.doe@example.com");
        transaction.setPhoneNumber("123456789");
        transaction.setOrderType("pickup");
        transaction.setTotalCost(100.0);

        Set<ConstraintViolation<Transaction>> violations = validator.validate(transaction);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void testTransactionInvalidEmail() {
        Transaction transaction = new Transaction();
        transaction.setFirstName("John");
        transaction.setLastName("Doe");
        transaction.setEmail("john.doe");
        transaction.setPhoneNumber("123456789");
        transaction.setOrderType("pickup");
        transaction.setTotalCost(100.0);

        Set<ConstraintViolation<Transaction>> violations = validator.validate(transaction);
        assertEquals(1, violations.size());
        assertEquals("Email should be valid", violations.iterator().next().getMessage());
    }

    @Test
    public void testTransactionInvalidPhoneNumber() {
        Transaction transaction = new Transaction();
        transaction.setFirstName("John");
        transaction.setLastName("Doe");
        transaction.setEmail("john.doe@example.com");
        transaction.setPhoneNumber("12345");
        transaction.setOrderType("pickup");
        transaction.setTotalCost(100.0);

        Set<ConstraintViolation<Transaction>> violations = validator.validate(transaction);
        assertEquals(1, violations.size());
        assertEquals("Phone number should be 9 digits", violations.iterator().next().getMessage());
    }

    @Test
    public void testTransactionMissingFirstName() {
        Transaction transaction = new Transaction();
        transaction.setLastName("Doe");
        transaction.setEmail("john.doe@example.com");
        transaction.setPhoneNumber("123456789");
        transaction.setOrderType("pickup");
        transaction.setTotalCost(100.0);

        Set<ConstraintViolation<Transaction>> violations = validator.validate(transaction);
        assertEquals(1, violations.size());
        assertEquals("First name is required", violations.iterator().next().getMessage());
    }

    @Test
    public void testTransactionMissingLastName() {
        Transaction transaction = new Transaction();
        transaction.setFirstName("John");
        transaction.setEmail("john.doe@example.com");
        transaction.setPhoneNumber("123456789");
        transaction.setOrderType("pickup");
        transaction.setTotalCost(100.0);

        Set<ConstraintViolation<Transaction>> violations = validator.validate(transaction);
        assertEquals(1, violations.size());
        assertEquals("Last name is required", violations.iterator().next().getMessage());
    }

    @Test
    public void testTransactionMissingOrderType() {
        Transaction transaction = new Transaction();
        transaction.setFirstName("John");
        transaction.setLastName("Doe");
        transaction.setEmail("john.doe@example.com");
        transaction.setPhoneNumber("123456789");
        transaction.setTotalCost(100.0);

        Set<ConstraintViolation<Transaction>> violations = validator.validate(transaction);
        assertEquals(1, violations.size());
        assertEquals("Order type is required", violations.iterator().next().getMessage());
    }

    @Test
    public void testTransactionInvalidZipCode() {
        Transaction transaction = new Transaction();
        transaction.setFirstName("John");
        transaction.setLastName("Doe");
        transaction.setEmail("john.doe@example.com");
        transaction.setPhoneNumber("123456789");
        transaction.setOrderType("delivery");
        transaction.setTotalCost(100.0);
        transaction.setStreetAddress("123 Main St");
        transaction.setCity("City");
        transaction.setState("State");
        transaction.setZipCode("1234");

        Set<ConstraintViolation<Transaction>> violations = validator.validate(transaction);
        assertEquals(1, violations.size());
        assertEquals("ZIP code should be in format XX-XXX", violations.iterator().next().getMessage());
    }


}
