package com.myrestaurant.restaurantapp.transactionTests;

import com.myrestaurant.restaurantapp.transaction.controller.TransactionController;
import com.myrestaurant.restaurantapp.transaction.model.Transaction;
import com.myrestaurant.restaurantapp.transaction.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TransactionControllerTest {

    private MockMvc mockMvc;

    @Mock
    private TransactionService transactionService;

    @InjectMocks
    private TransactionController transactionController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(transactionController).build();
    }

    @Test
    public void testProcessTransaction() throws Exception {
        Transaction transaction = new Transaction();
        transaction.setFirstName("John");
        transaction.setLastName("Doe");
        transaction.setEmail("john.doe@example.com");
        transaction.setPhoneNumber("123456789");
        transaction.setOrderType("pickup");
        transaction.setTotalCost(100.0);

        doNothing().when(transactionService).processTransaction(transaction);

        mockMvc.perform(post("/api/transaction")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer token")
                        .content("{\"firstName\":\"John\",\"lastName\":\"Doe\",\"email\":\"john.doe@example.com\",\"phoneNumber\":\"123456789\",\"orderType\":\"pickup\",\"totalCost\":100.0}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testProcessTransactionInvalidData() throws Exception {
        mockMvc.perform(post("/api/transaction")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer token")
                        .content("{\"firstName\":\"\",\"lastName\":\"Doe\",\"email\":\"john.doe@example.com\",\"phoneNumber\":\"123456789\",\"orderType\":\"pickup\",\"totalCost\":100.0}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testProcessTransactionInvalidZipCode() throws Exception {
        mockMvc.perform(post("/api/transaction")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer token")
                        .content("{\"firstName\":\"John\",\"lastName\":\"Doe\",\"email\":\"john.doe@example.com\",\"phoneNumber\":\"123456789\",\"orderType\":\"delivery\",\"totalCost\":100.0,\"streetAddress\":\"123 Main St\",\"city\":\"City\",\"state\":\"State\",\"zipCode\":\"1234\"}"))
                .andExpect(status().isBadRequest());
    }
}
