package com.myrestaurant.restaurantapp.userTests;

import com.myrestaurant.restaurantapp.user.exception.UserAlreadyRegisteredException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserAlreadyRegisteredExceptionTest {

    @Test
    public void testUserAlreadyRegisteredException() {
        String email = "test@example.com";
        UserAlreadyRegisteredException exception = assertThrows(UserAlreadyRegisteredException.class, () -> {
            throw new UserAlreadyRegisteredException(email);
        });

        assertEquals("Provided email address (" + email + ") is already registered.", exception.getMessage());
    }
}

