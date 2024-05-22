package com.myrestaurant.restaurantapp.security.auth.controller;

import com.myrestaurant.restaurantapp.security.auth.request.AuthenticationRequest;
import com.myrestaurant.restaurantapp.security.auth.request.ChangePasswordRequest;
import com.myrestaurant.restaurantapp.security.auth.request.RegisterRequest;
import com.myrestaurant.restaurantapp.security.auth.response.AuthenticationResponse;
import com.myrestaurant.restaurantapp.security.auth.response.ChangePasswordResponse;
import com.myrestaurant.restaurantapp.security.auth.response.RegisterResponse;
import com.myrestaurant.restaurantapp.security.auth.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.auth.base}")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("${api.auth.register}")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("${api.auth.authenticate}")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request,
            HttpServletRequest httpServletRequest) {
        return ResponseEntity.ok(authenticationService.authenticate(request, httpServletRequest));
    }

    @PostMapping("${api.auth.change}")
    public ResponseEntity<ChangePasswordResponse> changePassword(@RequestBody ChangePasswordRequest request) {
        return ResponseEntity.ok(authenticationService.changePassword(request));
    }
}
