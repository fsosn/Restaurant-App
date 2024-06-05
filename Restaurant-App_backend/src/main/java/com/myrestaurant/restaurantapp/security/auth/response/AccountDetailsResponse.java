package com.myrestaurant.restaurantapp.security.auth.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountDetailsResponse {
    private String role;
    private Long userId;
    private String email;
}
